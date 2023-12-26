import { Component, OnInit } from '@angular/core';
import { VideoService } from '../video.service';
import { VideoDTO } from '../video-dto';

@Component({
  selector: 'app-featured',
  templateUrl: './featured.component.html',
  styleUrl: './featured.component.css'
})
export class FeaturedComponent implements OnInit {

	featuredVideos: Array<VideoDTO> = [];
	constructor(private videoService: VideoService) {}

	ngOnInit(): void {
		this.videoService.getAllVideos().subscribe(videos => {
			this.featuredVideos = videos;
		});
	}

}
