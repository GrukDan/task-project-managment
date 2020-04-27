import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {UserViewModel} from "../model/view-model/user-view-model";
import {UserPaginationModel} from "../model/pagnation-model/user-pagination-model";


@Injectable({
  providedIn: 'root'
})
export class UserService {


  constructor(private http: HttpClient) {
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

  delete(idUser: number): Observable<void> {
    return this.http.delete<void>('/api/users', {
      params: new HttpParams()
        .set('idUser', idUser.toString())})
  }

  saveUserViewModel(userViewModel: UserViewModel): Observable<UserViewModel> {
    return this.http.post<UserViewModel>('/api/users/user-view-model', userViewModel);
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
    return this.http.get<UserViewModel>('/api/users', {
      params: new HttpParams()
        .set('id', id.toString())});
  }

}
