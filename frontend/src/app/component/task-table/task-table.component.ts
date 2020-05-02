import {Component, OnInit} from '@angular/core';
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {PageChangedEvent} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {TaskService} from "../../service/task.service";
import {ProjectViewModel} from "../../model/view-model/project-view-model";

@Component({
  selector: 'app-task-table',
  templateUrl: './task-table.component.html',
  styleUrls: ['./task-table.component.css']
})
export class TaskTableComponent implements OnInit {

  taskViewModels: TaskViewModel[];
  parameters: string[];
  subscriptions: Subscription[] = [];

  countOfPages: number;
  parameter: string;
  direction: boolean;
  size: number;
  page: number;

  constructor(private spinnerService: Ng4LoadingSpinnerService,
              private taskService: TaskService) {
    this.taskViewModels = [];
    this.taskViewModels = [];
    this.page = 0;
    this.size = 5;
    this.direction = true;
    this.countOfPages = 11;
  }

  ngOnInit() {
    this.loadSortParameters();
  }

  sort(parameter: string) {
    this.parameter = parameter;
    this.direction = !this.direction;
    this.loadTaskViewModels();
  }

  pageChanged($event: PageChangedEvent) {
    this.page = $event.page - 1;
    this.loadTaskViewModels();
  }

  loadSortParameters() {
    this.spinnerService.show();
    this.subscriptions.push(this.taskService.getSortParameter().subscribe(parameters => {
      this.parameters = parameters as string[];
      this.parameter = this.parameters[0];
      this.loadTaskViewModels()
      this.spinnerService.hide();
    }))
  }

  loadTaskViewModels() {
    this.spinnerService.show();
    this.subscriptions.push(this.taskService.getSortedTaskViewModels(
      this.parameter,
      this.page,
      this.size,
      this.direction
    ).subscribe(sorted => {
      this.taskViewModels = sorted.taskViewModels as TaskViewModel[];
      this.countOfPages = sorted.countPages * 10;
      this.spinnerService.hide();
    }))
  }

  btoa(str: string): string {
    return btoa(str);
  }

  ngOnDestroy(): void {
  }
}
