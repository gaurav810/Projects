import { Injectable } from '@angular/core';
import { HttpClient, HttpBackend } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private httpClient: HttpClient;

  constructor( handler: HttpBackend) { 
     this.httpClient = new HttpClient(handler);
  }

  loginUser(user: Object): Observable<Object> {
    return this.httpClient.post("http://localhost:7707/login", user);
  }

  public getToken(): string {
    return localStorage.getItem('token');
  }
}
