import { Component, inject } from '@angular/core';
import {
  FormGroup,
  FormBuilder,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css',
})
export class LoginPageComponent {
  private formBuilder = inject(FormBuilder);

  public errorMessage = '';
  public loginForm = this.formBuilder.group({
    username: [null, Validators.required],
    password: [null, Validators.required],
  });
  login() {}
}
