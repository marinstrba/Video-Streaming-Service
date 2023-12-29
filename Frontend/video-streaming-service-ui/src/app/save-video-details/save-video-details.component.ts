    import { ActivatedRoute } from '@angular/router';
    import { COMMA, ENTER } from '@angular/cdk/keycodes';
    import { Component } from '@angular/core';
    import { FormControl, FormGroup } from '@angular/forms';
    import { MatButtonModule } from '@angular/material/button';
    import { MatChipInputEvent } from '@angular/material/chips';
    import { MatChipsModule } from '@angular/material/chips';
    import { MatFormFieldModule } from '@angular/material/form-field';
    import { MatInputModule } from '@angular/material/input';
    import { MatSnackBar } from '@angular/material/snack-bar';
    import { VideoDTO } from '../video-dto';
    import { VideoService } from '../video.service';
    import { Router } from '@angular/router';



@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class SaveVideoDetailsComponent {

	// Class field variables

		saveVideoDetailsForm: FormGroup;
		title: FormControl = new FormControl('');
		description: FormControl = new FormControl('');
		videoStatus: FormControl = new FormControl('');
		selectable = true;
		removable = true;
		addOnBlur = true;
		readonly separatorKeysCodes = [ENTER, COMMA] as const;
		tags: string[] = [];
		selectedFile!: File;
		selectedFileName:string  = '';
		videoId:string = '';
		fileSelected = false;
		videoUrl!: string;
		thumbnailUrl!: string;

		// Assigning variables in constructor and injecting dependencies

	   constructor(private activatedRoute: ActivatedRoute, private videoService: VideoService,
	   private matSnackBar: MatSnackBar, private router: Router) {

	   // Getting the videoId from URL

	   this.videoId = this.activatedRoute.snapshot.params['videoId'];

	   // Fetching video data

	   this.videoService.getVideo(this.videoId).subscribe(data => {
			this.videoUrl = data.videoUrl;
			this.thumbnailUrl = data.thumbnailUrl;
	   })

	   // Creating form group

         this.saveVideoDetailsForm = new FormGroup({
     		title: this.title,
     		description: this.description,
     		videoStatus: this.videoStatus,
         })
       }

	// Part of MatChipsModule adding inputs in the final array
	add(event: MatChipInputEvent): void {
      const input = event.input;
      const value = (event.value || '').trim();

      if (value)
        this.tags.push(value);

      // Reset the input value
      if (input)
        input.value = '';
    }

	// Removing them
	 remove(value: string): void {
		const index = this.tags.indexOf(value);
		if (index >= 0)
			this.tags.splice(index, 1);
  	}

	onFileSelected(event: Event): void {
	  const input = event.target as HTMLInputElement | null;

	  if (input && input.files && input.files.length) {
		this.selectedFile = input.files[0];
		this.selectedFileName = this.selectedFile.name;
		this.fileSelected = true;
	  }
	}

	onUpload()
	{
		this.videoService.uploadThumbnail(this.selectedFile, this.videoId)
		.subscribe(data => {
			console.log(data);
			this.matSnackBar.open("Upload successful!", "OK");
		})
	}

	saveVideo()
	{
		const videoMetadata: VideoDTO = {
			"id": this.videoId,
			"title": this.saveVideoDetailsForm.get('title')?.value,
			"description": this.saveVideoDetailsForm.get('description')?.value,
			"tags": this.tags,
			"videoStatus": this.saveVideoDetailsForm.get('videoStatus')?.value,
			"videoUrl": this.videoUrl,
			"thumbnailUrl": this.thumbnailUrl,
			"likeCount": 0,
			"disLikeCount": 0,
			"viewCount": 0
		}

			this.videoService.saveVideo(videoMetadata).subscribe(data => {
				this.matSnackBar.open("Video Metadata updated successfully!", "OK");
				this.router.navigateByUrl('/feature');
			});
	}
}
