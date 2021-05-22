package com.cos.blog.service;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.cos.blog.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final MongoTemplate mongoTemplate;
	
	public List<User> mFindUsername(){
		Query query = new Query(Criteria.where("username").is("hong"));
		return mongoTemplate.find(query, User.class);
	}
}
