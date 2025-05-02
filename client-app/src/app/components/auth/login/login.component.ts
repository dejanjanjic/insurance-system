import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { LoginDTO } from '../../../model/user.model';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  loginForm: FormGroup;
  hidePassword = true;
  isLoading = false;
  serverError: string | null = null;

  private authService: AuthService = inject(AuthService);

  constructor(private fb: FormBuilder, private router: Router) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit(): void {
    this.serverError = null;

    if (this.loginForm.valid) {
      this.isLoading = true;
      const formData = this.loginForm.value;
      const loginDTO: LoginDTO = {
        username: formData.username,
        password: formData.password,
      };

      this.authService.login(loginDTO).subscribe({
        next: (response) => {
          this.isLoading = false;
          console.log(response);
          this.authService.setUserEmail(response.mail);
          this.router.navigate(['/verify']);
        },
        error: (error) => {
          this.isLoading = false;
          if (error.status === 404) {
            if (
              error.error?.message &&
              error.error.message.includes("User doesn't exist")
            ) {
              this.serverError = "User doesn't exist.";
            } else if (
              error.error?.message &&
              error.error.message.includes('Wrong password')
            ) {
              this.serverError = 'Wrong password.';
            } else {
              this.serverError = error.error?.message || 'Login failed.';
            }
          } else {
            this.serverError =
              'An error occurred during login. Please try again.';
          }
          console.error('Login error:', error);
        },
      });
    }
  }
}
