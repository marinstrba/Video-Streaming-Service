package com.video.streaming.service.repository;

import com.video.streaming.service.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video,String> {
}

