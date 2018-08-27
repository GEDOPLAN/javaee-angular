import { Component, OnInit } from '@angular/core';
import { UserService } from '../../common/services/user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'GED-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  constructor(private userService: UserService, private router: Router) {}

  public message: string = 'Benutzer = Passwort';

  login(username: string, password: string) {
    this.userService
      .login(username, password)
      .subscribe(r => this.router.navigateByUrl(''), error => (this.message = 'Login failed'));
  }
}
