import { Component } from '@angular/core';
import { User } from '../models/user.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User;

  constructor() {
    this.user = new User();
  }

  login() {
    // Implement your login logic here
  }
}