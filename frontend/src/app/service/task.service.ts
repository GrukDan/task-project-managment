import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../model/user";
import {Observable} from "rxjs";
import {UserViewModel} from "../model/view-model/user-view-model";
import {Task} from "../model/task";
import {TaskViewModel} from "../model/view-model/task-view-model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) { }

  save(task: Task): Observable<Task> {
    return this.http.put<Task>('/api/tasks', task);
  }


  getTaskViewModelsByProject(project: number):Observable<TaskViewModel[]> {
    return this.http.get<TaskViewModel[]>('/api/tasks/task-view-model',{params:new HttpParams().set('project',project.toString())});
  }
}
