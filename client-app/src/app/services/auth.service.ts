import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import {
  LoginDTO,
  LoginResponseDTO,
  VerificationCodeDTO,
} from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = environment.apiUrl + '/api/v1/auth';
  private userEmail: string = '';

  constructor(private http: HttpClient) {}

  login(loginDTO: LoginDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginDTO);
  }

  verify(verificationCodeDTO: VerificationCodeDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/verify`, verificationCodeDTO);
  }

  getToken() {
    return false;
  }

  setUserEmail(email: string): void {
    this.userEmail = email;
  }

  getUserEmail(): string {
    return this.userEmail;
  }
}
