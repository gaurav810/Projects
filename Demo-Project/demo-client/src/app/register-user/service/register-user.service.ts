import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegisterUserService {

  constructor(private http: HttpClient) { }

  createUser(user: Object): Observable<Object> {
    return this.http.post("http://localhost:7707/register", user);
  }
}
