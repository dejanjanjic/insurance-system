import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { LoginDTO, LoginResponseDTO } from '../model/user.model';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private baseUrl = environment.apiUrl + '/api/v1/auth';

  constructor(private http: HttpClient) {}

  login(loginDTO: LoginDTO): Observable<any> {
    return this.http.post(`${this.baseUrl}/login`, loginDTO);
  }

  getToken() {
    return false;
  }
}
