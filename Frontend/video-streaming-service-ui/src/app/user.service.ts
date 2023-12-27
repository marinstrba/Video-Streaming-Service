import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userId: string = '';

  constructor(private httpClient: HttpClient) { }

  subscribeToUser(userId: string): Observable<boolean> {
  	return this.httpClient.post<boolean>("https://localhost:8080/api/user/subscribe/" + userId, null);
  }

  unSubscribeToUser(userId: string): Observable<boolean>  {
  	  	return this.httpClient.post<boolean>("https://localhost:8080/api/user/unsubscribe/" + userId, null);
  }

  registerUser(): Observable<string> {
  	return this.httpClient.get<string>("https://localhost:8080/api/user/register").subscribe(data => {
  		this.userId = data;
  	});
  }

  getUserId(): string {
  	return this.userId;
  }
}
