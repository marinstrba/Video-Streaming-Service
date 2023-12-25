package com.video.streaming.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Document(value = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String emailAddress;
    private String sub;
    private Set<String> subscribedToUsers;
    private Set<String> subscribers;
    private Set<String> videoHistory = ConcurrentHashMap.newKeySet();
    private Set<String> likedVideos = ConcurrentHashMap.newKeySet();
    private Set<String> disLikedVideos = ConcurrentHashMap.newKeySet();

    public void addToLikeVideo(String videoId) {
        likedVideos.add(videoId);
    }

    public void removeFromLikedVideos(String videoId){
        likedVideos.remove(videoId);
    }
    public void addToDisLikedVideos(String videoId){
        disLikedVideos.add(videoId);
    }

    public void removeFromDisLikedVideos(String videoId){
        disLikedVideos.remove(videoId);
    }

    public void addToVideoHistory(String id) {
        videoHistory.add(id);
    }
}
