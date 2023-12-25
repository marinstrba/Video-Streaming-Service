package com.video.streaming.service.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


@Document(value = "Video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private AtomicInteger likes = new AtomicInteger(0);
    private AtomicInteger disLikes = new AtomicInteger(0);
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private AtomicInteger viewCount = new AtomicInteger(0);
    private String thumbnailUrl;
    private List<Comment> commentList;

    public void incrementLikes() {
        likes.incrementAndGet();
    }


    public void decrementLikes() {
        if (likes.get() > 0) {
            likes.decrementAndGet();
        }
    }

    public void incrementDisLikes() {
        disLikes.incrementAndGet();
    }

    public void decrementDisLikes() {
        if (disLikes.get() > 0) {
            disLikes.decrementAndGet();
        }
    }

    public void incrementViewCount() {
        viewCount.incrementAndGet();
    }
}
