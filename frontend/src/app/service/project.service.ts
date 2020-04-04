import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../model/project";
import {ProjectForTask} from "../model/view-model/project-for-task";
import {ProjectViewModel} from "../model/view-model/project-view-model";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getAllProject():Observable<ProjectViewModel[]>{
    return this.http.get<ProjectViewModel[]>('/api/projects/views');
  }

  getAllProjectForTask():Observable<ProjectForTask[]>{
    return this.http.get<ProjectForTask[]>('/api/projects/for-task');
  }

  save(project:Project):Observable<boolean>{
    return this.http.put<boolean>('/api/projects',project);
  }

}
