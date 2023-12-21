import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipsModule } from '@angular/material/chips';
import { ActivatedRoute } from '@angular/router';
import { VideoService } from '../video.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';


@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css']
})
export class SaveVideoDetailsComponent implements OnInit {

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
	 selectedFileName = '';
	 videoId ='';
	 fileSelected = false;
	 videoUrl!: string;


	   constructor(private activatedRoute: ActivatedRoute, private videoService: VideoService,
	   private matSnackBar: MatSnackBar) {
	   this.videoId = this.activatedRoute.snapshot.params['videoId'];
	   this.videoService.getVideo(this.videoId).subscribe(data => {
	   	this.videoUrl = data.videoUrl;
	   })
         this.saveVideoDetailsForm = new FormGroup({
     		title: this.title,
     		description: this.description,
     		videoStatus: this.videoStatus,
         })
       }

       ngOnInit(): void {}

	add(event: MatChipInputEvent): void {
      const input = event.input;
      const value = (event.value || '').trim();

      if (value) {
        this.tags.push(value);
      }

      // Reset the input value
      if (input) {
        input.value = '';
      }
    }

	 remove(value: string): void {
		const index = this.tags.indexOf(value);

		if (index >= 0) {
			this.tags.splice(index, 1);
		}
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

}
