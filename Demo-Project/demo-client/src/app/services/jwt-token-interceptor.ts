import { HttpInterceptor, HttpClient, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class JwtTokenInterceptor implements HttpInterceptor {
  
    constructor(private http: HttpClient){
    }
  
    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      req = req.clone({
        setHeaders:{
          'Authorization' : `Bearer ${localStorage.getItem("token")}`
        }
      });
      return next.handle(req);
    }
}   