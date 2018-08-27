import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BASEURL } from '../../app.tokens';

@Injectable({
  providedIn: 'root'
})
export class BookService {
  constructor(private http: HttpClient, @Inject(BASEURL) private baseurl: string) {}

  getAllBooks(): Observable<any[]> {
    return this.http.get<any[]>(this.baseurl + 'book');
  }

  getBook(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseurl}book/${id}`);
  }

  saveBook(book: any): Observable<any> {
    return this.http.put<any>(`${this.baseurl}book/${book.id}`, book);
  }
}
