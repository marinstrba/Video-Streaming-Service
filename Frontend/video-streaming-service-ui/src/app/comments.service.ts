import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CommentDTO } from './comment-dto';

@Injectable({
  providedIn: 'root'
})
export class CommentsService {

  constructor(private httpClient: HttpClient) { }

	postComment(commentDTO: any, videoId: string): Observable<any> {
		return this.httpClient.post<any>("http://localhost:8080/api/videos/" + videoId + "/comment", commentDTO);
	}

	getAllComments(videoId: string): Observable<Array<CommentDTO>> {
		return this.httpClient.get<CommentDTO[]>("http://localhost:8080/api/videos/" + videoId + "/comment");
	}

}
