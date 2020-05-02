import {Component, OnInit} from '@angular/core';
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {ProjectViewModel} from "../../model/view-model/project-view-model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ActivatedRoute, Router} from "@angular/router";
import {ValidationService} from "../../service/validation.service";
import {Subscription} from "rxjs";
import {ruLocale} from 'ngx-bootstrap/locale';
import {BsLocaleService, defineLocale} from "ngx-bootstrap";
import {Color, Label, MultiDataSet} from "ng2-charts";
import {TokenStorageService} from "../../auth/token-storage.service";

defineLocale('ru', ruLocale);

@Component({
  selector: 'app-project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {

  subscriptions: Subscription[] = []
  taskViewModels: TaskViewModel[];
  projectViewModel: ProjectViewModel;
  editProjectViewModel: ProjectViewModel;
  idProject: number;
  edit: boolean;
  projectForm: FormGroup;
  readinessDegree: number;

  locale = "ru";
  minDate: Date;
  date: Date;

  constructor(private projectService: ProjectService,
              private taskService: TaskService,
              private spinnerService: Ng4LoadingSpinnerService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private validationService: ValidationService,
              private localeService: BsLocaleService,
              private router: Router,
              public tokenStorage: TokenStorageService) {
    this.taskViewModels = [];
    this.projectViewModel = new ProjectViewModel();
    this.editProjectViewModel = new ProjectViewModel();
    this.minDate = new Date();
    this.readinessDegree = 0;
    this.localeService.use(this.locale);
    this.edit = false;
    this.route.paramMap.subscribe(params => {
      this.idProject = Number(atob(params.get('id')));
    });
  }

  public pieData = {
    pieChartLabels: [],
    pieChartData: [],
    pieChartType: 'pie',
  }

  public doughnutData = {
    doughnutChartLabels: [],
    doughnutChartData: [],
    doughnutChartType: 'doughnut',
  }

  public ChartColors: Color[] = [
    {
      backgroundColor: [
        'rgba(90,22,255,0.3)',
        'rgba(70,50,255,0.3)',
        'rgba(255,99,76,0.3)',
        'rgba(118,255,106,0.3)',
        'rgba(0,255,0,0.3)',
        'rgba(84,51,116,0.3)',
        'rgba(80,113,79,0.3)',
        'rgba(0,255,0,0.3)',
        'rgba(255,240,189,0.3)',
        'rgba(133,35,25,0.3)'
      ]
    }
  ];

  ngOnInit() {
    this.loadProject();
  }

  loadProject() {
    this.spinnerService.show()
    this.subscriptions.push(this.projectService.getProjectViewModelById(this.idProject).subscribe(
      projectViewModel => {
        this.projectViewModel = projectViewModel as ProjectViewModel;
        this.editProjectViewModel = ProjectViewModel.clone(projectViewModel);
        this.date = new Date(projectViewModel.dateOfCompletion);
        if (projectViewModel.readinessDegree) {
          this.readinessDegree = projectViewModel.readinessDegree;
        }
        this.loadTaskViewModels();
        this.spinnerService.hide();
      },
      err => alert("Произошла ошибка! Попробуйте позже...")))
  }

  loadTaskViewModels() {
    this.spinnerService.show()
    this.subscriptions.push(this.taskService.getTaskViewModelsByProject(this.idProject).subscribe(taskViewModels => {
      this.taskViewModels = taskViewModels as TaskViewModel[];
      this.groupByStatus();
      this.groupByPriority();
      this.spinnerService.hide();
    }))
  }

  groupByStatus() {
    this.taskViewModels.forEach(task => {
      if (this.doughnutData.doughnutChartLabels.includes(task.priorityName)) {
        this.doughnutData.doughnutChartData[this.doughnutData.doughnutChartLabels.indexOf(task.priorityName)]
          = this.doughnutData.doughnutChartData[this.doughnutData.doughnutChartLabels.indexOf(task.priorityName)] + 1;
      } else {
        this.doughnutData.doughnutChartLabels.push(task.priorityName);
        this.doughnutData.doughnutChartData[this.doughnutData.doughnutChartLabels.indexOf(task.priorityName)]
          = 1;
      }
    })
  }

  groupByPriority() {
    this.taskViewModels.forEach(task => {
      if (this.pieData.pieChartLabels.includes(task.statusName)) {
        this.pieData.pieChartData[this.pieData.pieChartLabels.indexOf(task.statusName)]
          = this.pieData.pieChartData[this.pieData.pieChartLabels.indexOf(task.statusName)] + 1;
      } else {
        this.pieData.pieChartLabels.push(task.statusName);
        this.pieData.pieChartData[this.pieData.pieChartLabels.indexOf(task.statusName)]
          = 1;
      }
    })
  }

  changeEdit() {
    this.edit = !this.edit;
  }

  private _createForm() {
    if (this.projectForm == null) {
      this.projectForm = this.validationService.getProjectFormGroup();
    }
  }

  get _projectName() {
    return this.projectForm.get('projectName')
  }

  get _projectDescription() {
    return this.projectForm.get('description')
  }

  get _dateOfCompletion() {
    return this.projectForm.get('dateOfCompletion')
  }

  saveProjectViewModel(projectViewModel: ProjectViewModel) {
    this.spinnerService.show()
    this.subscriptions.push(this.projectService.saveProjectViewModel(projectViewModel).subscribe(
      projectViewModel => {
        console.log(projectViewModel)
        this.projectViewModel = projectViewModel as ProjectViewModel;
        this.editProjectViewModel = ProjectViewModel.clone(projectViewModel);
        this.spinnerService.hide();
      },
      err => alert("Произошла ошибка! Попробуйте позже...")))
  }

  save() {
    this.changeEdit();
    this.editProjectViewModel.dateOfCompletion = this.date.toISOString();
    this.saveProjectViewModel(this.editProjectViewModel);
  }

  startEdit() {
    this._createForm();
    this.changeEdit()
  }

  btoa(s: string) {
    return btoa(s);
  }

  delete(idProject) {
    this.router.navigate(['/'])
    this.subscriptions.push(this.projectService.delete(idProject).subscribe(
      mess => {
      }
      , err => alert("Произошла ошибка! Попробуйте позже...")))
  }
}
