import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {ProjectService} from "../../service/project.service";
import {RoleService} from "../../service/role.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {TaskService} from "../../service/task.service";
import {ActivatedRoute} from "@angular/router";
import {UserViewModel} from "../../model/view-model/user-view-model";
import {Role} from "../../model/role";
import {Project} from "../../model/project";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";
import {Task} from "../../model/task";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private subscriptions: Subscription[] = [];
  private idUser: number;
  private userViewModel: UserViewModel;
  private editUserViewModel: UserViewModel;
  private roles: Role[];
  private projects: Project[];
  private tasks: Task[];
  private edit: boolean;
  private userForm: FormGroup;

  constructor(private userService: UserService,
              private projectService: ProjectService,
              private roleService: RoleService,
              private taskService: TaskService,
              private spinnerService: Ng4LoadingSpinnerService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private validationService: ValidationService,) {

    this.userViewModel = new UserViewModel();
    this.editUserViewModel = new UserViewModel();
    this.edit = false;

    // subscribe to the parameters observable
    this.route.paramMap.subscribe(params => {
      this.idUser = Number(atob(params.get('id')));
    });
  }

  ngOnInit() {
    this.loadUserViewModel();
  }

  loadUserViewModel() {
    this.spinnerService.show()
    this.subscriptions.push(this.userService.getUserViewModel(this.idUser).subscribe(user => {
      this.userViewModel = user as UserViewModel;
      this.editUserViewModel = UserViewModel.clone(user);
      this.spinnerService.hide();
    }))
  }

  loadRole() {
    if (this.roles == null) {
      this.spinnerService.show();
      this.subscriptions.push(this.roleService.getAllRole().subscribe(roles => {
        this.roles = roles as Role[];
        this.spinnerService.hide();
      }))
    }
  }

  loadProject() {
    if (this.projects == null) {
      this.spinnerService.show();
      this.subscriptions.push(this.projectService.getAllProject().subscribe(project => {
        this.projects = project as Project[];
        this.spinnerService.hide();
      }))
    }
  }

  loadTasks(){

  }

  saveUserViewModel(userViewModelForSaving:UserViewModel){
    this.spinnerService.show();
    this.subscriptions.push(this.userService.saveUserViewModel(userViewModelForSaving).subscribe(userViewModel=>{
      this.userViewModel = userViewModel as UserViewModel;
      this.editUserViewModel = UserViewModel.clone(userViewModel);
      this.spinnerService.hide();
    }))
  }

  changeEdit() {
    this.edit = !this.edit;
  }

  private _createForm() {
    if (this.userForm == null) {
      this.userForm = this.validationService.getUserFormGroup();
      this.userForm.controls['login'].clearValidators();
      this.userForm.controls['password'].clearValidators();
    }
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


  save() {
    this.changeEdit();
    this.saveUserViewModel(this.editUserViewModel);
  }

  startEdit() {
    this._createForm();
    this.changeEdit()
    this.loadProject();
    this.loadRole()
  }
}
