import { Component} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../video.service';
import { UserService } from '../user.service';

@Component({
  selector: 'app-video-detail',
  templateUrl: './video-detail.component.html',
  styleUrls: ['./video-detail.component.css']
})
export class VideoDetailComponent {

	videoId!: string;
	videoUrl!: string;
	videoTitle!: string;
	videoDescription!: string;
	tags: Array<string> = [];
	videoAvailable: boolean = false;
	likeCount: number = 0;
	disLikeCount: number = 0;
	viewCount: number = 0;
	showSubscribeButton: boolean = true;
	showUnSubscribeButton: boolean = false;



	constructor(private activatedRoute: ActivatedRoute,
				private videoService: VideoService,
				private userService: UserService) {
	   this.videoId = this.activatedRoute.snapshot.params['videoId'];
	   this.videoService.getVideo(this.videoId).subscribe(data => {
			this.videoUrl = data.videoUrl;
			this.videoTitle = data.title;
			this.videoDescription = data.description;
			this.tags = data.tags;
			this.videoAvailable = true;
			this.likeCount = data.likeCount;
			this.disLikeCount = data.disLikeCount;
			this.viewCount = data.viewCount;
	   })

	}

	likeVideo() {
		this.videoService.likeVideo(this.videoId).subscribe(data =>
		{
			this.likeCount = data.likeCount;
			this.disLikeCount = data.disLikeCount;
		}
		);
	}

	disLikeVideo() {
    		this.videoService.disLikeVideo(this.videoId).subscribe(data =>
    		{
    			this.likeCount = data.likeCount;
    			this.disLikeCount = data.disLikeCount;
    		}
    		);
    }

	subscribeToUser() {

		let userId = this.userService.getUserId();
		this.userService.subscribeToUser(userId).subscribe(data => {
			this.showUnSubscribeButton = true;
			this.showSubscribeButton = false;
		});

	}

	unSubscribeToUser() {

    		let userId = this.userService.getUserId();
    		this.userService.unSubscribeToUser(userId).subscribe(data => {
    			this.showUnSubscribeButton = true;
    			this.showSubscribeButton = false;
    		});

    	}

}
