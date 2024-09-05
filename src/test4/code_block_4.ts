// register.component.ts
import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { User } from '../user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  constructor(private authService: AuthService) { }

  onRegister(form: NgForm) {
    const user: User = {
      username: form.value.username,
      password: form.value.password
    };
    this.authService.registerUser(user).subscribe();
  }
}