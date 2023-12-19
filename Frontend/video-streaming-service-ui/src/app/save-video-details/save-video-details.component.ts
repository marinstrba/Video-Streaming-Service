import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatChipInputEvent } from '@angular/material/chips';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipsModule } from '@angular/material/chips';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-save-video-details',
  templateUrl: './save-video-details.component.html',
  styleUrls: ['./save-video-details.component.css'] // Corrected to styleUrls
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


	   constructor(private activatedRoute: ActivatedRoute) {
	   this.videoId = this.activatedRoute.snapshot.params.videoId;
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
  }
}

onUpload()
{

}

}
