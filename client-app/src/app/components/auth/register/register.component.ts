import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
  AbstractControl,
  ValidationErrors,
  ValidatorFn,
} from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { UserService } from '../../../services/user.service';
import { User } from '../../../model/user.model';

export const passwordMatchValidator: ValidatorFn = (
  control: AbstractControl
): ValidationErrors | null => {
  const password = control.get('password');
  const confirmPassword = control.get('confirmPassword');

  return password && confirmPassword && password.value !== confirmPassword.value
    ? { passwordMismatch: true }
    : null;
};

@Component({
  selector: 'app-register',
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
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent {
  registerForm: FormGroup;
  hidePassword = true;
  hideConfirmPassword = true;

  private userService: UserService = inject(UserService);

  constructor(private fb: FormBuilder, private router: Router) {
    this.registerForm = this.fb.group(
      {
        username: ['', [Validators.required]],
        mail: ['', [Validators.required, Validators.email]],
        password: ['', [Validators.required, Validators.minLength(6)]],
        confirmPassword: ['', [Validators.required]],
      },
      { validators: passwordMatchValidator }
    );
  }

  onSubmit(): void {
    if (this.registerForm.valid) {
      const formData = this.registerForm.value;
      const registrationPayload: User = {
        username: formData.username,
        mail: formData.mail,
        password: formData.password,
      };
      this.userService.register(registrationPayload).subscribe({
        next: (response) => {
          this.router.navigate(['/login']);
        },
        error: (error) => {
          console.log(error);
        },
      });
    }
  }

  get mail() {
    return this.registerForm.get('mail');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get confirmPassword() {
    return this.registerForm.get('confirmPassword');
  }
}
