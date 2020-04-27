import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {CommentViewModel} from "../model/view-model/comment-view-model";
import {Comment} from "../model/comment";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http:HttpClient) { }

  public save(comment:Comment):Observable<void>{
    return this.http.post<void>('/api/comments',comment);
  }

  public getAll(size:number,idTask:number):Observable<CommentViewModel[]>{
    return this.http.get<CommentViewModel[]>('/api/comments/all-comments',
      {
        params:new HttpParams()
          .set('size',size.toString())
          .set('idTask',idTask.toString())
      })
  }

}
