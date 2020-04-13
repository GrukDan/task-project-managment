import {Component, OnInit} from '@angular/core';
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {ProjectViewModel} from "../../model/view-model/project-view-model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ActivatedRoute} from "@angular/router";
import {ValidationService} from "../../service/validation.service";
import {Subscription} from "rxjs";
import {ruLocale} from 'ngx-bootstrap/locale';
import {BsLocaleService, defineLocale} from "ngx-bootstrap";

defineLocale('ru', ruLocale);

@Component({
  selector: 'app-project-page',
  templateUrl: './project-page.component.html',
  styleUrls: ['./project-page.component.css']
})
export class ProjectPageComponent implements OnInit {

  private subscriptions: Subscription[] = []
  private taskViewModels: TaskViewModel[];
  private projectViewModel: ProjectViewModel;
  private editProjectViewModel: ProjectViewModel;
  private idProject: number;
  private edit: boolean;
  private projectForm: FormGroup;

  locale = "ru";
  minDate: Date;
  date: Date;

  constructor(private projectService: ProjectService,
              private taskService: TaskService,
              private spinnerService: Ng4LoadingSpinnerService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private validationService: ValidationService,
              private localeService: BsLocaleService,) {
    this.taskViewModels = [];
    this.projectViewModel = new ProjectViewModel();
    this.editProjectViewModel = new ProjectViewModel();
    this.minDate = new Date();
    this.localeService.use(this.locale);
    this.edit = false;
// subscribe to the parameters observable
    this.route.paramMap.subscribe(params => {
      this.idProject = Number(atob(params.get('id')));
    });
  }

  ngOnInit() {
    this.loadProject();
  }

  loadProject() {
    this.spinnerService.show()
    this.subscriptions.push(this.projectService.getProjectViewModelById(this.idProject).subscribe(projectViewModel => {
      this.projectViewModel = projectViewModel as ProjectViewModel;
      this.editProjectViewModel = ProjectViewModel.clone(projectViewModel);
      this.date = new Date(projectViewModel.dateOfCompletion);
      this.spinnerService.hide();
    }))
  }

  loadTaskViewModels() {
    this.spinnerService.show()
    this.subscriptions.push(this.taskService.getTaskViewModelsByProject(this.idProject).subscribe(taskViewModels => {
      this.taskViewModels = taskViewModels as TaskViewModel[];
      this.spinnerService.hide();
    }))
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
    this.subscriptions.push(this.projectService.saveProjectViewModel(projectViewModel).subscribe(projectViewModel => {
      console.log(projectViewModel)
      this.projectViewModel = projectViewModel as ProjectViewModel;
      this.editProjectViewModel = ProjectViewModel.clone(projectViewModel);
      this.spinnerService.hide();
    }))
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


  delete() {
    console.log(this.projectViewModel)
  }
}
