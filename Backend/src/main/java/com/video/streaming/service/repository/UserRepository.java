package com.video.streaming.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.video.streaming.service.model.User;


public interface UserRepository extends MongoRepository<User, String> {
}
