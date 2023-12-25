package com.video.streaming.service.service;

import com.video.streaming.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import com.video.streaming.service.model.User;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getCurrentUser() {
        String sub = ((Jwt)(SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
        ).getClaim("sub");

        return userRepository.findBySub(sub)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find user by sub - " + sub));
    }

    public void addToLikedVideos(String videoId){
        User currentUser = getCurrentUser();
        currentUser.addToLikeVideo(videoId);
        userRepository.save(currentUser);
    }

    public boolean ifLikedVideo(String videoId) {
        return getCurrentUser().getLikedVideos().stream().anyMatch(id -> id.equals(videoId));
    }

    public boolean ifDisLikedVideo(String videoId) {
        return getCurrentUser().getDisLikedVideos().stream().anyMatch(id -> id.equals(videoId));
    }

    public void removeFromLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromLikedVideos(videoId);
        userRepository.save(currentUser);
    }

    public void removeFromDisLikedVideos(String videoId) {
        User currentUser = getCurrentUser();
        currentUser.removeFromDisLikedVideos(videoId);
        userRepository.save(currentUser);
    }
}
