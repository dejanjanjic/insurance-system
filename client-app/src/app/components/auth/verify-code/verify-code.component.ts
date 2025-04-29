import { Component, OnInit, OnDestroy } from '@angular/core';
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
import { interval, Subscription } from 'rxjs';
import { take } from 'rxjs/operators';

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
  ],
  templateUrl: './verify-code.component.html',
  styleUrl: './verify-code.component.css',
})
export class VerifyCodeComponent implements OnInit, OnDestroy {
  verifyForm: FormGroup;
  timeRemaining = '2:00';
  canResend = false;
  private timerSubscription?: Subscription;

  constructor(private fb: FormBuilder, private router: Router) {
    this.verifyForm = this.fb.group({
      code: ['', [Validators.required]],
    });
  }

  ngOnInit(): void {
    this.startTimer(120);
  }

  ngOnDestroy(): void {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  startTimer(seconds: number): void {
    this.canResend = false;
    const timer$ = interval(1000).pipe(take(seconds + 1));

    this.timerSubscription = timer$.subscribe((elapsed) => {
      const remaining = seconds - elapsed;
      const minutes = Math.floor(remaining / 60);
      const secs = remaining % 60;

      this.timeRemaining = `${minutes}:${secs < 10 ? '0' + secs : secs}`;

      if (remaining === 0) {
        this.canResend = true;
      }
    });
  }

  resendCode(): void {
    if (this.canResend) {
      // Simulate resending code
      this.startTimer(120);
    }
  }

  onSubmit(): void {
    if (this.verifyForm.valid) {
      // For demonstration, navigate to dashboard
      this.router.navigate(['/dashboard']);
    }
  }
}
