import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { UserService } from '../service/user.service';

@Injectable()
export class SecurityInterceptor implements HttpInterceptor {
  constructor(private userService: UserService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.userService.token) {
      const changedReq = req.clone(
        { headers: req.headers.set('Authorization', this.userService.token) }
      );
      return next.handle(changedReq);
    }

    return next.handle(req);
  }
}
