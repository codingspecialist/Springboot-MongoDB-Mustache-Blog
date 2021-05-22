package com.cos.blog.domain.user;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsernameAndPassword(String username, String password);
	
	@Query("{boards : {$ne : []}}")
	List<User> mFindNotEmptyBoards();
}
