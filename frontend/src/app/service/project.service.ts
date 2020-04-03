import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../model/project";
import {ProjectForTask} from "../model/view-model/project-for-task";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getAllProject():Observable<Project[]>{
    return this.http.get<Project[]>('/api/projects');
  }

  getAllProjectForTask():Observable<ProjectForTask[]>{
    return this.http.get<ProjectForTask[]>('/api/projects/for-task');
  }
}
