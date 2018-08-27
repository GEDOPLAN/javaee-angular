import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BASEURL } from '../../app.tokens';

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private http: HttpClient, @Inject(BASEURL) private baseurl: string) {}

  getListValue(): Observable<{ id: number; value: string }[]> {
    return this.http.get<{ id: number; value: string }[]>(`${this.baseurl}author/list`);
  }

  getAuthor(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseurl}author/${id}`);
  }

  saveAuthor(author: any): Observable<any> {
    return this.http.put<any>(`${this.baseurl}author/${author.id}`, author);
  }
}
