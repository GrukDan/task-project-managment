import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Project} from "../model/project";
import {ProjectForTask} from "../model/view-model/project-for-task";
import {ProjectViewModel} from "../model/view-model/project-view-model";
import {ProjectPaginationModel} from "../model/pagnation-model/project-pagination-model";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) {
  }

  getAllProject(): Observable<ProjectViewModel[]> {
    return this.http.get<ProjectViewModel[]>('/api/projects/views');
  }

  getAllProjectForTask(): Observable<ProjectForTask[]> {
    return this.http.get<ProjectForTask[]>('/api/projects/for-task');
  }

  save(project: Project): Observable<boolean> {
    return this.http.put<boolean>('/api/projects', project);
  }

  getSortParameter(): Observable<string[]> {
    return this.http.get<string[]>('/api/projects/sort-parameter');
  }

  getSortedProject(parameter: string, page: number, size: number, direction: boolean, search: string = ''): Observable<ProjectPaginationModel> {
    console.log(parameter,page,size,direction);
    return this.http.get<ProjectPaginationModel>(
      '/api/projects/sort',
      {
        params: new HttpParams()
          .set('parameter',parameter)
          .set('page',page.toString())
          .set('size',size.toString())
          .set('direction',direction.toString())
          .set('search',search)
      })
  }
}
