package com.video.streaming.service.service;

import com.video.streaming.service.DTO.UploadVideoResponse;
import com.video.streaming.service.DTO.VideoDTO;
import com.video.streaming.service.model.Video;
import com.video.streaming.service.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    private final UserService userService;
    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);

        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    public String uploadThumbnail(MultipartFile multipartFile, String videoId) {
        var videoThumbnail = getVideoById(videoId);
        String thumbnailURL = s3Service.uploadFile(multipartFile);
        videoThumbnail.setThumbnailUrl(thumbnailURL);
        videoRepository.save(videoThumbnail);
        return thumbnailURL;
    }

    public VideoDTO editVideo(VideoDTO videoDTO)
    {
        var savedVideo = getVideoById(videoDTO.getId());

        savedVideo.setTitle(videoDTO.getTitle());
        savedVideo.setDescription(videoDTO.getDescription());
        savedVideo.setTags(videoDTO.getTags());
        savedVideo.setThumbnailUrl(videoDTO.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDTO.getVideoStatus());

        videoRepository.save(savedVideo);
        return videoDTO;

    }

    public VideoDTO getVideoDetails(String videoId)
    {
        Video videoDetails = getVideoById(videoId);
        VideoDTO videoDto = new VideoDTO();

        videoDto.setVideoUrl(videoDetails.getVideoUrl());
        videoDto.setThumbnailUrl(videoDetails.getThumbnailUrl());
        videoDto.setId(videoDetails.getId());
        videoDto.setTitle(videoDetails.getTitle());
        videoDto.setDescription(videoDetails.getDescription());
        videoDto.setTags(videoDetails.getTags());
        videoDto.setVideoStatus(videoDetails.getVideoStatus());

        return videoDto;

    }

    public VideoDTO likeVideo(String videoId) {
        //Get Video by ID
        Video likedVideo = getVideoById(videoId);
        VideoDTO videoDTO = new VideoDTO();

        // Increment like count
        // If user already liked the video, then decrement like count
        // like - 0, dislike - 0
        // like - 1, dislike - 0
        // like - 0, dislike - 0
        // like - 0, dislike - 1
        // like - 1, dislike - 0

        // If user already dislikes the video, then increment like count and decrement dislike count

        if (userService.ifLikedVideo(videoId)){
            likedVideo.decrementLikes();
            userService.removeFromLikedVideos(videoId);
        } else if (userService.ifDisLikedVideo(videoId)) {
            likedVideo.decrementDisLikes();
            userService.removeFromDisLikedVideos(videoId);
            likedVideo.incrementLikes();
            userService.addToLikedVideos(videoId);
        } else {
            likedVideo.incrementLikes();
            userService.addToLikedVideos(videoId);
        }

        videoRepository.save(likedVideo);

        videoDTO.setVideoUrl(likedVideo.getVideoUrl());
        videoDTO.setThumbnailUrl(likedVideo.getThumbnailUrl());
        videoDTO.setId(likedVideo.getId());
        videoDTO.setTitle(likedVideo.getTitle());
        videoDTO.setDescription(likedVideo.getDescription());
        videoDTO.setTags(likedVideo.getTags());
        videoDTO.setVideoStatus(likedVideo.getVideoStatus());
        videoDTO.setLikeCount(likedVideo.getLikes().get());
        videoDTO.setDisLikeCount(likedVideo.getDisLikes().get());

        return videoDTO;
    }

    private Video getVideoById(String videoId)
    {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by Id."));
    }
}
