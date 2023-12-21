import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { FileSystemFileEntry } from "ngx-file-drop";
import { UploadVideoResponse } from './upload-video/UploadVideoResponse';
import { VideoDTO } from './video-dto';

@Injectable({
  providedIn: 'root'
})
export class VideoService {

  constructor(private httpClient: HttpClient) { }

  uploadVideo(fileEntry: File): Observable<UploadVideoResponse>
  {
    const formData = new FormData()
    formData.append('file', fileEntry, fileEntry.name);
    return this.httpClient.post<UploadVideoResponse>("http://localhost:8080/api/videos/", formData)
  }

  uploadThumbnail(fileEntry: File, videoId: string): Observable<string>
    {
      const formData = new FormData();
      formData.append('file', fileEntry, fileEntry.name);
      formData.append('videoId', videoId);
      return this.httpClient.post<string>("http://localhost:8080/api/videos/thumbnail/", formData,{
      	responseType: 'text' as 'json'
      })
    }

    getVideo(videoId: String): Observable<VideoDTO> {
    	return this.httpClient.get<VideoDTO>("http://localhost:8080/api/videos/" + videoId);
    }


}
