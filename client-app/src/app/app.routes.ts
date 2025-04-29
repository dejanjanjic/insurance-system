import { Routes } from '@angular/router';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';

export const routes: Routes = [
  {
    path: 'registration',
    component: RegistrationPageComponent,
  },
  {
    path: 'login',
    component: LoginPageComponent,
  },
];
