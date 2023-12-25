package com.video.streaming.service.controller;

import com.video.streaming.service.DTO.UploadVideoResponse;
import com.video.streaming.service.DTO.VideoDTO;
import com.video.streaming.service.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos/")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    @PostMapping(value="/")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadVideoResponse uploadVideo(@RequestParam("file") MultipartFile file)
    {
        return videoService.uploadVideo(file);
    }
    @PostMapping(value="/thumbnail/")
    @ResponseStatus(HttpStatus.CREATED)
    public String uploadThumbnail(@RequestParam("file") MultipartFile file, @RequestParam("videoId") String videoId)
    {
        return videoService.uploadThumbnail(file, videoId);
    }
    @PutMapping(value="/")
    @ResponseStatus(HttpStatus.OK)
    public VideoDTO editVideoMetadata(@RequestBody VideoDTO videoDTO)
    {
        return videoService.editVideo(videoDTO);
    }

    @GetMapping(value="/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDTO getVideoDetails(@PathVariable String videoId)
    {
        return videoService.getVideoDetails(videoId);
    }

    @PostMapping(value = "/{videoId}/like")
    public VideoDTO likeVideo(@PathVariable String videoId){
        return videoService.likeVideo(videoId);
    }
}
