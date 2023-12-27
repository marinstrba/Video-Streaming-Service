package com.video.streaming.service.service;

import com.video.streaming.service.DTO.CommentDTO;
import com.video.streaming.service.DTO.UploadVideoResponse;
import com.video.streaming.service.DTO.VideoDTO;
import com.video.streaming.service.model.Comment;
import com.video.streaming.service.model.Video;
import com.video.streaming.service.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideoService {
    private final S3Service s3Service;
    private final VideoRepository videoRepository;
    private final UserService userService;

    /**
     * Uploads a video file to S3 storage and saves its metadata in a repository.
     *
     * @param multipartFile The video file to be uploaded.
     * @return UploadVideoResponse containing the ID and URL of the saved video.
     */
    public UploadVideoResponse uploadVideo(MultipartFile multipartFile) {
        String videoUrl = s3Service.uploadFile(multipartFile);
        var video = new Video();
        video.setVideoUrl(videoUrl);
        var savedVideo = videoRepository.save(video);
        return new UploadVideoResponse(savedVideo.getId(), savedVideo.getVideoUrl());
    }

    /**
     * Uploads a thumbnail for a video and saves its metadata in a repository.
     *
     * @param multipartFile The picture to be uploaded.
     * @param videoId The ID of the video.
     * @return String which contains URL of the thumbnail image.
     */

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

        increaseVideoCount(videoDetails);
        userService.addVideoToHistory(videoDetails);

        return mapToVideoDTO(videoDetails);

    }

    private void increaseVideoCount(Video videoDetails) {
        videoDetails.incrementViewCount();
        videoRepository.save(videoDetails);
    }

    public VideoDTO likeVideo(String videoId) {
        //Get Video by ID
        Video likedVideo = getVideoById(videoId);
        VideoDTO videoDTO = new VideoDTO();

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

        return mapToVideoDTO(likedVideo);

    }

    public VideoDTO disLikeVideo(String videoId) {

        //Get Video by ID
        Video likedVideo = getVideoById(videoId);

        if (userService.ifDisLikedVideo(videoId)){
            likedVideo.decrementDisLikes();
            userService.removeFromDisLikedVideos(videoId);
        } else if (userService.ifLikedVideo(videoId)) {
            likedVideo.decrementLikes();
            userService.removeFromLikedVideos(videoId);
            likedVideo.incrementDisLikes();
            userService.addToDisLikedVideos(videoId);
        } else {
            likedVideo.incrementDisLikes();
            userService.addToDisLikedVideos(videoId);
        }

        videoRepository.save(likedVideo);

        return mapToVideoDTO(likedVideo);

    }

    private static VideoDTO mapToVideoDTO(Video videoById) {
        VideoDTO videoDTO = new VideoDTO();

        videoDTO.setVideoUrl(videoById.getVideoUrl());
        videoDTO.setThumbnailUrl(videoById.getThumbnailUrl());
        videoDTO.setId(videoById.getId());
        videoDTO.setTitle(videoById.getTitle());
        videoDTO.setDescription(videoById.getDescription());
        videoDTO.setTags(videoById.getTags());
        videoDTO.setVideoStatus(videoById.getVideoStatus());
        videoDTO.setLikeCount(videoById.getLikes().get());
        videoDTO.setDisLikeCount(videoById.getDisLikes().get());
        videoDTO.setViewCount(videoById.getViewCount().get());

        return videoDTO;
    }

    private Video getVideoById(String videoId)
    {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by Id."));
    }

    public void addComment(String videoId, CommentDTO commentDTO) {
        var video = getVideoById(videoId);
        Comment comment = new Comment();
        comment.setText(commentDTO.getCommentText());
        comment.setAuthorId(commentDTO.getAuthorId());
        video.addComment(comment);
        videoRepository.save(video);
    }

    public List<CommentDTO> getAllComments(String videoId) {
        var video = getVideoById(videoId);
        List<Comment> commentList = video.getCommentList();

        return commentList.stream()
                .map(this::mapToCommentDto)
                .toList();
    }

    private CommentDTO mapToCommentDto(Comment comment) {
        var commentDTO = new CommentDTO();
        commentDTO.setCommentText(comment.getText());
        commentDTO.setAuthorId(comment.getAuthorId());
        return commentDTO;
    }

    public List<VideoDTO> getAllVideos() {
        return videoRepository.findAll().stream().map(video -> mapToVideoDTO(video)).toList();
    }
}
