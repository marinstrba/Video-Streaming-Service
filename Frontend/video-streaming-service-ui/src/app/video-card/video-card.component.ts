import { Component, OnInit, Input } from '@angular/core';
import { VideoDTO } from '../video-dto';

@Component({
  selector: 'app-video-card',
  templateUrl: './video-card.component.html',
  styleUrl: './video-card.component.css'
})
export class VideoCardComponent implements OnInit {

	@Input()
	video!: VideoDTO;

	constructor() {}

	ngOnInit(): void {

	}

}
