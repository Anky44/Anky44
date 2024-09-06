import { Component } from '@angular/core';
import { User } from '../models/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user: User;

  constructor() {
    this.user = new User();
  }

  register() {
    // Implement your registration logic here
  }
}