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
              private localeService: BsLocaleService) {
    this._createForm();
    this.minDate = new Date();
    this.localeService.use(this.locale);

    this.user = new User();
    this.project = new Project();
    this.task = new Task();

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
    this.spinnerService.show();
    this.subscriptions.push(this.roleService.getAllRole().subscribe(roles=>{
       this.roles = roles as Role[];
       this.spinnerService.hide()
    }))

  }

  private _createForm() {
    this.userForm = this.validationService.getUserFormGroup();
    this.taskForm = this.validationService.getTaskFormGroup();
    this.projectForm = this.validationService.getProjectFormGroup();
  }

  addUser(): void {
    console.log(this.user);
  }

  addProject(): void {
    console.log(this.project);
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

  get _taskDescription() {
    return this.userForm.get('description')
  }

  get _status() {
    return this.taskForm.get('status')
  }

  get _priority() {
    return this.taskForm.get('priority')
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

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
    this.loadRole();
  }
}
