import { Injectable, Inject } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpInterceptor, HttpHandler, HttpEvent, HttpRequest } from '@angular/common/http';
import { BASEURL } from '../../app.tokens';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private loggedInUser: string;

  public token: string; // store in webstorage in real applications...

  constructor(private http: HttpClient, @Inject(BASEURL) private baseurl: string, private router: Router) {}

  login(user: string, password: string): Observable<string> {
    return this.http.post(this.baseurl + 'login', { user, password }, { observe: 'response' }).pipe(
      map((r: any) => {
        this.token = r.headers.get('Authorization');
        console.log(r.headers.get('Authorization'));
        return this.token;
      })
    );
  }
}
