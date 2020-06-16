import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  private baseUrl = "http://localhost:7070/person"

  constructor(private http: HttpClient) { }

  getPersonList(): Observable<any> {
  
    return this.http.get("http://localhost:7070/person");
  } 

  deletePerson(id: number): Observable<any> {

    return this.http.delete("http://localhost:7070/person/" + id, { responseType: 'text' });
  }

  getPersonDetailsById(id: number): Observable<any> {

    return this.http.get("http://localhost:7070/person/" + id);
  }

  createPerson(person: Object): Observable<Object> {
    return this.http.post("http://localhost:7070/person/", person);
  }

  updatePerson(person: Object, id: number): Observable<Object> {
    return this.http.put("http://localhost:7070/person/" + id, person);
  }
}
