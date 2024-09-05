// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user.model';

@Injectable()
export class AuthService {
  private registerUrl = 'http://<API_URL>/register';
  private loginUrl = 'http://<API_URL>/login';

  constructor(private http: HttpClient) { }

  registerUser(user: User){
    return this.http.post<any>(this.registerUrl, user);
  }

  loginUser(user: User){
    return this.http.post<any>(this.loginUrl, user);
  }
}