import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProjectViewModel} from "../../model/view-model/project-view-model";
import {PageChangedEvent} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ProjectService} from "../../service/project.service";

@Component({
  selector: 'app-project-table',
  templateUrl: './project-table.component.html',
  styleUrls: ['./project-table.component.css']
})
export class ProjectTableComponent implements OnInit, OnDestroy {

  private parameters: string[];
  private projects: ProjectViewModel[];
  private subscriptions: Subscription[] = [];

  private countOfPages: number;
  private parameter: string;
  private direction: boolean;
  private size: number;
  private page: number;

  constructor(private spinnerService: Ng4LoadingSpinnerService,
              private projectService: ProjectService,) {
    this.projects = [];
    this.page = 0;
    this.size = 5;
    this.direction = true;
    this.countOfPages = 11;
  }

  ngOnInit() {
    this.loadSortParameters();
  }

  pageChanged($event: PageChangedEvent) {
    this.page = $event.page - 1;
    this.loadProjects();
  }

  sort(parameter: string) {
    this.parameter = parameter;
    this.direction = !this.direction;
    this.loadProjects();
  }

  loadSortParameters() {
    this.spinnerService.show();
    this.subscriptions.push(this.projectService.getSortParameter().subscribe(parameters => {
      this.parameters = parameters as string[];
      this.parameter = this.parameters[0];
      this.loadProjects()
      this.spinnerService.hide();
    }))
  }

  loadProjects() {
    this.spinnerService.show();
    this.subscriptions.push(this.projectService.getSortedProject(
      this.parameter,
      this.page,
      this.size,
      this.direction
    ).subscribe(sortedProjects => {
      this.projects = sortedProjects.projectViewModels as ProjectViewModel[];
      this.countOfPages = sortedProjects.countOfPages * 2;
      // this.pagesCount = (this.taskPaginationModel.pagesCount/5) * 10;
      this.spinnerService.hide();
    }))
  }

  btoa(str: string): string {
    return btoa(str);
  }

  ngOnDestroy(): void {
  }
}
