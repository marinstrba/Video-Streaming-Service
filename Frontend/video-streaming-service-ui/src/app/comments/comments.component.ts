import { Component, Input, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { CommentsService } from '../comments.service';
import { FormGroup, FormControl } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CommentDTO } from '../comment-dto';

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.css'
})
export class CommentsComponent implements OnInit {

	@Input()
	videoId: string = '';
	commentsForm: FormGroup;
	commentsDTO: CommentDTO[] = [];

	constructor (private userService: UserService,
				private commentsService: CommentsService,
				private matSnackBar: MatSnackBar) {
		this.commentsForm = new FormGroup({

			comment: new FormControl('comment'),

		});

	}

	 ngOnInit(): void {

		this.getComments();
	 }


	postComment() {
		const comment = this.commentsForm.get('comment')?.value;

		const commentDTO = {
			"commentText": comment,
			"authorId": this.userService.getUserId(),
		}

		this.commentsService.postComment(commentDTO, this.videoId)
							.subscribe(() => {
								this.matSnackBar.open("Comment posted successfully", "OK");

								this.commentsForm.get('comment')?.reset();
								this.getComments();
							})
	}

	getComments() {
		this.commentsService.getAllComments(this.videoId)
							.subscribe( data => {
								this.commentsDTO = data;
							});

	}

}
