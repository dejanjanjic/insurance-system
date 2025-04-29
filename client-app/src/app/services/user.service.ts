import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl = environment.apiUrl + '/api/v1/users';

  constructor(private http: HttpClient) {}

  register(user: User): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, user);
  }
}
