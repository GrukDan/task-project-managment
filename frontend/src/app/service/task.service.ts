import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/user";
import {Observable} from "rxjs";
import {UserViewModel} from "../model/view-model/user-view-model";
import {Task} from "../model/task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  save(task: Task): Observable<Task> {
    return this.http.put<Task>('/api/tasks', task);
  }
}
