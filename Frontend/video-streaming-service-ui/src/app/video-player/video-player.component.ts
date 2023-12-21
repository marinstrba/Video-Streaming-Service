import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-video-player',
  templateUrl: './video-player.component.html',
  styleUrl: './video-player.component.css'
})
export class VideoPlayerComponent implements OnInit {

	@Input()
	videoUrl!: string | '';
	constructor() {}

	ngOnInit(): void {

	}
}
