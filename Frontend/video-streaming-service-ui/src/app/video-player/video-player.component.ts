import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrls: ['./video-player.component.css']
})
export class VideoPlayerComponent implements OnInit {

	@Input()
	videoUrl!: string | '';
	constructor() {}
	ngOnInit(): void {
				//console.log("This is video url: " + this.videoUrl);

	}
}
