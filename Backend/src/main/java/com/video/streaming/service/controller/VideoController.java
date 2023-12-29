package com.video.streaming.service.controller;

import com.video.streaming.service.DTO.CommentDTO;
import com.video.streaming.service.DTO.UploadVideoResponse;
import com.video.streaming.service.DTO.VideoDTO;
import com.video.streaming.service.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


/**
 * REST controller for handling video related requests.
 */
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
    @ResponseStatus(HttpStatus.OK)
    public VideoDTO likeVideo(@PathVariable String videoId){
        return videoService.likeVideo(videoId);
    }

    @PostMapping(value = "/{videoId}/dislike")
    @ResponseStatus(HttpStatus.OK)
    public VideoDTO disLikeVideo(@PathVariable String videoId) { return videoService.disLikeVideo(videoId); }

    @PostMapping(value = "/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public void addComment(@PathVariable String videoId, @RequestBody CommentDTO commentDTO){
        videoService.addComment(videoId, commentDTO);
    }

    @GetMapping(value = "/{videoId}/comment")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> getAllComments(@PathVariable String videoId) {
        return videoService.getAllComments(videoId);
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDTO> getAllVideos() {
        return videoService.getAllVideos();
    }

    @GetMapping(value = "/get-liked-videos")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDTO> getLikedVideos(){ return videoService.getLikedVideos(); }

    @GetMapping(value = "/get-video-history")
    @ResponseStatus(HttpStatus.OK)
    public List<VideoDTO> getVideoHistory(){ return videoService.getVideoHistory(); }


}
