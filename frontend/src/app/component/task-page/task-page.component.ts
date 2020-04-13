import { Component, OnInit } from '@angular/core';
import {Subscription} from "rxjs";
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BsLocaleService, defineLocale, ruLocale} from "ngx-bootstrap";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ActivatedRoute} from "@angular/router";
import {ValidationService} from "../../service/validation.service";
import {ProjectViewModel} from "../../model/view-model/project-view-model";
import {UserService} from "../../service/user.service";
import {PriorityService} from "../../service/priority.service";
import {StatusService} from "../../service/status.service";
import {Status} from "../../model/status";
import {Priority} from "../../model/priority";
import {User} from "../../model/user";

defineLocale('ru', ruLocale);

@Component({
  selector: 'app-task-page',
  templateUrl: './task-page.component.html',
  styleUrls: ['./task-page.component.css']
})
export class TaskPageComponent implements OnInit {

  private subscriptions: Subscription[] = []
  private taskViewModel:TaskViewModel;
  private editTaskViewModel:TaskViewModel;

  private comments:Comment[];
private statuses:Status[];
private priorities:Priority[];
private executors:User[];

  private idTask: number;
  private edit: boolean;
  private taskForm: FormGroup;

  locale = "ru";
  minDate: Date;
  dueDate:Date;

  constructor(private taskService: TaskService,
              private userService:UserService,
              private priorityService:PriorityService,
              private statusService:StatusService,
              private spinnerService: Ng4LoadingSpinnerService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private validationService: ValidationService,
              private localeService: BsLocaleService,) {
    this.comments = [];
    this.statuses = [];
    this.priorities = [];
    this.executors = []
    this.taskViewModel = new TaskViewModel();
    this.editTaskViewModel = new TaskViewModel();
    this.edit = false;
    this.minDate = new Date();

    this.localeService.use(this.locale);

    this.route.paramMap.subscribe(params => {
      this.idTask = Number(atob(params.get('id')));
    });
  }

  ngOnInit() {
    this.loadTaskViewModel();
  }

  loadTaskViewModel() {
    this.spinnerService.show()
    this.subscriptions.push(this.taskService.getTaskViewModelById(this.idTask).subscribe(taskViewModel => {
      this.taskViewModel = taskViewModel as TaskViewModel;
      this.editTaskViewModel = TaskViewModel.clone(taskViewModel);
      this.dueDate = new Date(taskViewModel.dueDate);
      this.spinnerService.hide();
    }))
  }

  // loadComments() {
  //   this.spinnerService.show()
  //   this.subscriptions.push(this..getTaskViewModelsByProject(this.idProject).subscribe(taskViewModels => {
  //     this.taskViewModels = taskViewModels as TaskViewModel[];
  //     this.spinnerService.hide();
  //   }))
  // }

  private loadPriority():void{
    if(this.priorities.length==0) {
      this.spinnerService.show();
      this.subscriptions.push(this.priorityService.getAllPriority().subscribe(priorities => {
        this.priorities = priorities as Priority[];
        this.spinnerService.hide()
      }))
    }
  }

  private loadStatus():void{
    if(this.statuses.length==0) {
      this.spinnerService.show();
      this.subscriptions.push(this.statusService.getAllStatus().subscribe(statuses => {
        this.statuses = statuses as Status[];
        this.spinnerService.hide()
      }))
    }
  }

  private loadExecutors(idProject:number):void{
    if(this.executors.length==0) {
      this.spinnerService.show();
      this.subscriptions.push(this.userService.getUserByAssignProject(idProject).subscribe(executors => {
        this.executors = executors as User[];
        this.spinnerService.hide()
      }))
    }
  }

  changeEdit() {
    this.edit = !this.edit;
    if(this.edit){
      this.loadExecutors(this.editTaskViewModel.project);
      this.loadPriority();
      this.loadStatus()
    }
  }

  private _createForm() {
    if (this.taskForm == null) {
      this.taskForm = this.validationService.getTaskFormGroup();
      this.taskForm.controls['taskProject'].clearValidators()
    }
  }

  saveTaskViewModel(taskViewModel: TaskViewModel) {
    this.spinnerService.show()
    this.subscriptions.push(this.taskService.saveTaskViewModel(taskViewModel).subscribe(taskViewModel => {
      this.taskViewModel = taskViewModel as TaskViewModel;
      this.editTaskViewModel = TaskViewModel.clone(taskViewModel);
      this.spinnerService.hide();
    }))
  }

  save() {
    this.changeEdit();
    this.editTaskViewModel.dueDate = this.dueDate.toISOString();
    this.editTaskViewModel.updated = Date.now().toString();
    this.saveTaskViewModel(this.editTaskViewModel);
  }

  startEdit() {
    this._createForm();
    this.changeEdit()
  }

  btoa(s: string) {
    return btoa(s);
  }

  delete() {
    console.log(this.taskViewModel)
  }

  get _taskName() {
    return this.taskForm.get('taskName')
  }

  get _taskProject() {
    return this.taskForm.get('taskProject')
  }

  get _taskDescription() {
    return this.taskForm.get('description')
  }

  get _status() {
    return this.taskForm.get('status')
  }

  get _priority() {
    return this.taskForm.get('priority')
  }

  get _dueDate() {
    return this.taskForm.get('dueDate')
  }
}
