import { Component, inject } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-registration-page',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './registration-page.component.html',
  styleUrl: './registration-page.component.css',
})
export class RegistrationPageComponent {
  private formBuilder = inject(FormBuilder);

  public errorMessage = '';
  public registrationForm: FormGroup = this.formBuilder.group({
    email: [null, [Validators.required /*, Validators.email*/]],
    username: [null, Validators.required],
    password: [null, Validators.required],
  });
  register() {}
}
