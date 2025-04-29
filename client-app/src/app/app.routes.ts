import { Routes } from '@angular/router';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { VerifyCodeComponent } from './components/auth/verify-code/verify-code.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },
  {
    path: 'verify',
    component: VerifyCodeComponent,
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
];
