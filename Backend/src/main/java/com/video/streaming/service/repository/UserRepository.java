package com.video.streaming.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.video.streaming.service.model.User;

import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findBySub(String sub);
}
