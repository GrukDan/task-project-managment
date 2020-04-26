import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {UserViewModel} from "../model/view-model/user-view-model";
import {ProjectPaginationModel} from "../model/pagnation-model/project-pagination-model";
import {UserPaginationModel} from "../model/pagnation-model/user-pagination-model";
import {AuthorizationModel} from "../model/authorization-model";
import {MapType} from "@angular/compiler";
import {map} from "rxjs/operators";
import {JwtResponse} from "../model/jwt-response";

export const TOKEN_NAME: string = 'jwt_token';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'})

  constructor(private http: HttpClient) {
  }

  getToken(): string {
    return localStorage.getItem(TOKEN_NAME);
  }

  setToken(token: string): void {
    localStorage.setItem(TOKEN_NAME, token);
  }

  getUserByAssignProject(assignProject: number): Observable<User[]> {
    return this.http.get<User[]>('/api/users/project', {
      params: new HttpParams()
        .set('assignProject', assignProject.toString())
    });
  }


  save(user: User): Observable<UserViewModel> {
    return this.http.put<UserViewModel>('/api/users', user);
  }

  saveUserViewModel(userViewModel: UserViewModel): Observable<UserViewModel> {
    return this.http.post<UserViewModel>('/api/users/user-view-model', userViewModel);
  }

  authorization(user:User): Observable<User> {
    console.log(user)
    return this.http.post<User>('/api/users/sign-up', JSON.stringify(user),
      {headers:this.headers})
     this.http.post<JwtResponse>('/api/users/sign-up', JSON.stringify(user),
      {headers:this.headers})
      // .toPromise()
      // .then(res=>res.text())
  }


  getSortParameter(): Observable<string[]> {
    return this.http.get<string[]>('/api/users/sort-parameter');
  }

  getSortedUser(parameter: string, page: number, size: number, direction: boolean, search: string = ''): Observable<UserPaginationModel> {
    return this.http.get<UserPaginationModel>(
      '/api/users/sort',
      {
        params: new HttpParams()
          .set('parameter', parameter)
          .set('page', page.toString())
          .set('size', size.toString())
          .set('direction', direction.toString())
          .set('search', search)
      })
  }

  getUserViewModel(id: number): Observable<UserViewModel> {
    return this.http.get<UserViewModel>('/api/users', {params: new HttpParams().set('id', id.toString())});
  }

  isToken() {
    return false;
  }
}
