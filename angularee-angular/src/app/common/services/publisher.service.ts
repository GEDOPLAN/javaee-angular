import { Injectable, Inject } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { BASEURL } from '../../app.tokens';

@Injectable({
  providedIn: 'root'
})
export class PublisherService {
  constructor(private http: HttpClient, @Inject(BASEURL) private baseurl: string) {}

  getListValue(): Observable<{ id: number; value: string }[]> {
    return this.http.get<{ id: number; value: string }[]>(`${this.baseurl}publisher/list`);
  }
}
