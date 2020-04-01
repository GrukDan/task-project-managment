import { Component, OnInit } from '@angular/core';
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-task-table',
  templateUrl: './task-table.component.html',
  styleUrls: ['./task-table.component.css']
})
export class TaskTableComponent implements OnInit {

  private taskViewModels:TaskViewModel[];
  private parameters: string[];

  constructor() {
    this.taskViewModels = [];
    this.parameters = ["taskCode","taskName","priority","status","dateOfCreation","dueDate","updated"];
  }

  ngOnInit() {
  }

  sort(string: string) {

  }

  pageChanged($event: PageChangedEvent) {

  }
}
