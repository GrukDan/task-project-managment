import { Component, OnInit } from '@angular/core';
import {ProjectViewModel} from "../../model/view-model/project-view-model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-project-table',
  templateUrl: './project-table.component.html',
  styleUrls: ['./project-table.component.css']
})
export class ProjectTableComponent implements OnInit {

  private parameters: string[];
  private projects:ProjectViewModel[];

  constructor() {
    this.parameters = ["projectName","dateOfCompletion","readinessDegree"];
    this.projects = [];
  }

  ngOnInit() {
  }

  pageChanged($event: PageChangedEvent) {

  }

  sort(parameter: string) {
console.log(parameter);
  }
}
