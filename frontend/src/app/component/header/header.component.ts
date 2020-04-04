import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {BsDropdownConfig} from 'ngx-bootstrap/dropdown';
import {Observable, Subscription} from "rxjs";
import {BsLocaleService, BsModalRef, BsModalService, defineLocale, listLocales} from "ngx-bootstrap";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/user";
import {Task} from "../../model/task";
import {Project} from "../../model/project";
import {ValidationService} from "../../service/validation.service";
import {RoleService} from "../../service/role.service";
import {Role} from "../../model/role";
import {Status} from "../../model/status";
import {Priority} from "../../model/priority";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import { ruLocale } from 'ngx-bootstrap/locale';
import {StatusService} from "../../service/status.service";
import {PriorityService} from "../../service/priority.service";
import {ProjectService} from "../../service/project.service";
import {ProjectForTask} from "../../model/view-model/project-for-task";
import {UserForTask} from "../../model/view-model/user-for-task";
defineLocale('ru', ruLocale);

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [{provide: BsDropdownConfig, useValue: {isAnimated: true, autoClose: true}}]
})
export class HeaderComponent implements OnInit, OnDestroy {

  userForm: FormGroup;
  taskForm: FormGroup;
  projectForm: FormGroup;

  subscriptions: Subscription[] = [];

  user: User;
  task: Task;
  project: Project;


  userForTask:UserForTask[];
  projectsForTask:ProjectForTask[];
  roles: Role[];
  statuses: Status[];
  priorities: Priority[];

  locale = "ru";
  minDate: Date;
  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,
              private spinnerService: Ng4LoadingSpinnerService,
              private userService: UserService,
              private fb: FormBuilder,
              private validationService: ValidationService,
              private roleService: RoleService,
              private statusService: StatusService,
              private priorityService:PriorityService,
              private localeService: BsLocaleService,
              private projectService:ProjectService) {
    this._createForm();
    this.minDate = new Date();
    this.localeService.use(this.locale);

    this.user = new User();
    this.project = new Project();
    this.task = new Task();


    this.userForTask = [];
    this.projectsForTask = [];
    this.roles = [];
    this.statuses = [];
    this.priorities = [];
  }


  ngOnInit() {
  }

  ngOnDestroy(){
    this.subscriptions.forEach(subscription =>{
      subscription.unsubscribe();
    })
  }

  private loadRole():void{
    if(this.roles.length==0) {
      this.spinnerService.show();
      this.subscriptions.push(this.roleService.getAllRole().subscribe(roles => {
        this.roles = roles as Role[];
        this.spinnerService.hide()
      }))
    }
  }

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

  private loadProjectForTask():void{
    if(this.projectsForTask.length==0) {
      this.spinnerService.show();
      this.subscriptions.push(this.projectService.getAllProjectForTask().subscribe(projectForTask => {
        this.projectsForTask = projectForTask as ProjectForTask[];
        this.spinnerService.hide()
      }))
    }
  }

  private _createForm() {
    this.userForm = this.validationService.getUserFormGroup();
    this.taskForm = this.validationService.getTaskFormGroup();
    this.projectForm = this.validationService.getProjectFormGroup();
    this.taskForm.controls['taskExecutor'].disabled;
    this.taskForm.controls['status'].clearValidators()
  }

  addUser(): void {
    this.subscriptions.push(this.userService.save(this.user).subscribe(user=>{
      this.modalRef.hide();
      }
    ))
  }

  addProject(): void {
    this.project.projectCreator = 1;
    this.project.readinessDegree = 0;
    this.subscriptions.push(this.projectService.save(this.project).subscribe(project=>{
      this.modalRef.hide();
    }))
  }


  addTask(): void {
    console.log(this.task);
  }

  get _userName() {
    return this.userForm.get('userName')
  }

  get _userSurname() {
    return this.userForm.get('userSurname')
  }

  get _email() {
    return this.userForm.get('email')
  }

  get _role() {
    return this.userForm.get('role')
  }

  get _login() {
    return this.userForm.get('login')
  }

  get _password() {
    return this.userForm.get('password')
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

  get _projectName() {
    return this.projectForm.get('projectName')
  }

  get _projectDescription() {
    return this.projectForm.get('description')
  }

  get _dateOfCompletion() {
    return this.projectForm.get('dateOfCompletion')
  }

  openUserModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
    this.loadRole();
  }

  openProjectModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  openTaskModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
    this.loadPriority();
    this.loadStatus();
    this.loadProjectForTask();
  }
}
