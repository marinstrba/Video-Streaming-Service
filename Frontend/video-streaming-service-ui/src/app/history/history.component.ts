import { Component, OnInit } from '@angular/core';
import { VideoService } from '../video.service';
import { VideoDTO } from '../video-dto';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {

	watchedVideos: Array<VideoDTO> = [];

	constructor(private videoService: VideoService){}

	ngOnInit(): void {
		this.videoService.getVideoHistory().subscribe(data => {
			this.watchedVideos = data;
		})
	}

}
