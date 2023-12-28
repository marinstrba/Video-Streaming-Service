import { Component, OnInit } from '@angular/core';
import { VideoService } from '../video.service';
import { VideoDTO } from '../video-dto';

@Component({
  selector: 'app-liked-videos',
  templateUrl: './liked-videos.component.html',
  styleUrl: './liked-videos.component.css'
})
export class LikedVideosComponent implements OnInit {

	featuredVideos: Array<VideoDTO> = [];
	constructor(private videoService: VideoService) {}

	ngOnInit(): void {
		this.videoService.getAllVideos().subscribe(videos => {
			this.featuredVideos = videos;
		});
	}

}
