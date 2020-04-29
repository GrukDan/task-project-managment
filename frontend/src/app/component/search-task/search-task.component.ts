import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {TaskService} from "../../service/task.service";
import {ActivatedRoute} from "@angular/router";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";

@Component({
  selector: 'app-search-task',
  templateUrl: './search-task.component.html',
  styleUrls: ['./search-task.component.css']
})
export class SearchTaskComponent implements OnInit {

  private subscriptions: Subscription[] = []
  private taskViewModels: TaskViewModel[];
  private search:string;

  constructor(private taskService: TaskService,
              private route: ActivatedRoute,
              private spinnerService: Ng4LoadingSpinnerService,) {
    this.route.paramMap.subscribe(params => {
      this.search = params.get('search');
      this.loadTaskViewModel(this.search)
    });
    this.taskViewModels = [];
  }

  ngOnInit() {
    this.loadTaskViewModel(this.search)
  }

  loadTaskViewModel(search:string) {
    this.spinnerService.show()
    this.subscriptions.push(this.taskService.getTaskViewModelsBySearch(search).subscribe(taskViewModels => {
      this.taskViewModels = taskViewModels as TaskViewModel[];
      this.spinnerService.hide();
    }))
  }

  btoa(str: string): string {
    return btoa(str);
  }
}
