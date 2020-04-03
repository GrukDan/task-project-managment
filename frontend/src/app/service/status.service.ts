import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../model/role";
import {Status} from "../model/status";

@Injectable({
  providedIn: 'root'
})
export class StatusService {

  constructor(private http: HttpClient) { }

  getAllStatus(): Observable<Status[]> {
    return this.http.get<Status[]>('/api/status');
  }
}
