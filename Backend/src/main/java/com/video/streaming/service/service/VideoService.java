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


    private Video getVideoById(String videoId)
    {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by Id."));

    }
}
