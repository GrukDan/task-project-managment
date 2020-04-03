import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getUserByAssignProject(assignProject:number):Observable<User[]>{
    return this.http.get<User[]>('/api/users',{
      params:new HttpParams()
        .set('assignProject',assignProject.toString())
    });
  }

}
