import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsDropdownConfig} from 'ngx-bootstrap/dropdown';
import {Subscription} from "rxjs";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {UserService} from "../../service/user.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {User} from "../../model/user";
import {Task} from "../../model/task";
import {Project} from "../../model/project";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [{provide: BsDropdownConfig, useValue: {isAnimated: true, autoClose: true}}]
})
export class HeaderComponent implements OnInit {

  userForm: FormGroup;
  taskForm: FormGroup;
  projectForm: FormGroup;

  subscriptions: Subscription[] = [];

  user: User;
  task: Task;
  project: Project;

  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,
              private userService: UserService,
              private fb: FormBuilder) {
    this._createForm();
    this.user = new User();
  }


  ngOnInit() {
  }

  private _createForm() {
    this.userForm = this.fb.group({
      userName: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
          Validators.pattern(/^[0-9]+(?!.)/)]],
      userSurname: ['',
        [
          Validators.required,
          Validators.minLength(2),
          Validators.maxLength(20),
          Validators.pattern(/^[0-9]+(?!.)/)]],
      email: ['', [Validators.email]],
      // role: ['', [Validators.required]],
      // login: [
      //   '',
      //   [
      //     Validators.required,
      //     Validators.minLength(4),
      //     Validators.maxLength(12),
      //     Validators.pattern(/^[0-9]+(?!.)/)
      //   ]
      // ],
      // password: [
      //   '',
      //   [
      //     Validators.required,
      //     Validators.minLength(4),
      //     Validators.max(12),
      //     Validators.pattern(/^[0-9]+(?!.)/)
      //   ]
      // ]
    })


  }

  addUser():void{
    console.log(this.user);
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

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }
}
