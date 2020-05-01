import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {User} from "../model/user";
import {Observable} from "rxjs";
import {UserViewModel} from "../model/view-model/user-view-model";
import {Task} from "../model/task";
import {TaskViewModel} from "../model/view-model/task-view-model";
import {TaskPaginationModel} from "../model/pagnation-model/task-pagination-model";
import {ProjectViewModel} from "../model/view-model/project-view-model";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) {
  }

  save(task: Task): Observable<Task> {
    return this.http.put<Task>('/api/tasks', task);
  }

  delete(idTask: number): Observable<void> {
    return this.http.delete<void>(
      '/api/tasks',
      {
        params: new HttpParams()
          .set('idTask', idTask.toString())
      })
  }

  getTaskViewModelsByProject(project: number): Observable<TaskViewModel[]> {
    return this.http.get<TaskViewModel[]>(
      '/api/tasks/task-view-model',
      {
        params: new HttpParams()
          .set('project', project.toString())
      });
  }

  getTaskViewModelsBySearch(search: string): Observable<TaskViewModel[]> {
    return this.http.get<TaskViewModel[]>(
      '/api/tasks/search',
      {
        params: new HttpParams()
          .set('search', search)
      })
  }

  getSortParameter(): Observable<string[]> {
    return this.http.get<string[]>('/api/tasks/sort-parameter');
  }

  getSortedTaskViewModels(parameter: string, page: number, size: number, direction: boolean, search: string = ''): Observable<TaskPaginationModel> {
    return this.http.get<TaskPaginationModel>(
      '/api/tasks/sort',
      {
        params: new HttpParams()
          .set('parameter', parameter)
          .set('page', page.toString())
          .set('size', size.toString())
          .set('direction', direction.toString())
          .set('search', search)
      })
  }

  getTaskViewModelById(idTask: number): Observable<TaskViewModel> {
    return this.http.get<TaskViewModel>(
      '/api/tasks',
      {
        params: new HttpParams()
          .set('id', idTask.toString())
      });
  }

  saveTaskViewModel(taskViewModel: TaskViewModel): Observable<TaskViewModel> {
    return this.http.post<TaskViewModel>('/api/tasks/task-view-model', taskViewModel);
  }

  getTaskViewModelsByTaskExecutor(iduser: number): Observable<TaskViewModel[]> {
    return this.http.get<TaskViewModel[]>(
      '/api/tasks/task-view-model/executor',
      {
        params: new HttpParams()
          .set('executor', iduser.toString())
      })
  }
}
