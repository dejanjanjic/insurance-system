import { Component, OnInit, OnDestroy, inject } from '@angular/core';
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
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { interval, Subscription } from 'rxjs';
import { take } from 'rxjs/operators';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-verify-code',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    RouterLink,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatProgressSpinnerModule,
  ],
  templateUrl: './verify-code.component.html',
  styleUrl: './verify-code.component.css',
})
export class VerifyCodeComponent implements OnInit {
  verifyForm: FormGroup;
  authService: AuthService = inject(AuthService);
  userEmail: string = '';
  isLoading: boolean = false;
  serverError: string | null = null;

  constructor(private fb: FormBuilder, private router: Router) {
    this.verifyForm = this.fb.group({
      code: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.userEmail = this.authService.getUserEmail();

    if (!this.userEmail) {
      this.router.navigate(['/login']);
    }
  }

  onSubmit(): void {
    this.serverError = null;

    if (this.verifyForm.valid && this.userEmail) {
      this.isLoading = true;
      const verificationCode = this.verifyForm.get('code')?.value;

      const verificationData = {
        mail: this.userEmail,
        verificationCode: verificationCode,
      };

      this.authService.verify(verificationData).subscribe({
        next: (response) => {
          this.isLoading = false;
          if (response && response.token) {
            localStorage.setItem('auth_token', response.token);

            // Navigate to dashboard or home page
            this.router.navigate(['/dashboard']);
          }
          this.serverError = 'dobro je';
        },
        error: (error) => {
          this.isLoading = false;
          if (error.status === 404) {
            if (
              error.error?.message &&
              error.error.message.includes("Activation codes don't match")
            ) {
              this.serverError = 'Verification code is incorrect.';
            } else if (
              error.error?.message &&
              error.error.message.includes("Email isn't associated")
            ) {
              this.serverError = 'Email not found.';
            } else {
              this.serverError = error.error?.message || 'Verification failed.';
            }
          } else {
            this.serverError =
              'An error occurred during verification. Please try again.';
          }
          console.error('Verification error:', error);
        },
      });
    }
  }
}
