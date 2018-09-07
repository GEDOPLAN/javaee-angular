import { Injectable, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import { LoginService, BASE_PATH } from '../../generated';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService extends LoginService {
  private loggedInUser: string;

  public token: string; // store in webstorage in real applications...

  constructor(private http: HttpClient, @Inject(BASE_PATH) private baseurl: string, private router: Router) {
    super(http, baseurl, null);
  }

  loginUser(user: string, password: string): Observable<string> {
    return super.login({ user, password }, 'response').pipe(
      map((r: any) => {
        this.token = r.headers.get('Authorization');
        console.log(r.headers.get('Authorization'));
        return this.token;
      })
    );
  }
}
