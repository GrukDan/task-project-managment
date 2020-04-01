import { Component, OnInit } from '@angular/core';
import {TaskViewModel} from "../../model/view-model/task-view-model";

@Component({
  selector: 'app-project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {

  private taskViewModels:TaskViewModel[];

  constructor() {
    this.taskViewModels = [];

  }

  ngOnInit() {
  }

}
