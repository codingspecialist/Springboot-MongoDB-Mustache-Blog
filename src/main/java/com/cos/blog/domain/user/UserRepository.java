package com.cos.blog.domain.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
	User findByUsernameAndPassword(String username, String password);
}
