import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {JwtResponse} from "../model/jwt-response";
import {User} from "../model/user";

const AUTH_API = '/api/users/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials:User): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(AUTH_API + 'sign-up', credentials, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'sign-up', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}
