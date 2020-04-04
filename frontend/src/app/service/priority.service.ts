import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../model/role";
import {Priority} from "../model/priority";

@Injectable({
  providedIn: 'root'
})
export class PriorityService {

  constructor(private http: HttpClient) { }

  getAllPriority(): Observable<Priority[]> {
    return this.http.get<Priority[]>('/api/priorities');
  }
}
