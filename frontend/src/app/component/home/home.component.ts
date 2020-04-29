import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {ProjectService} from "../../service/project.service";
import {RoleService} from "../../service/role.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {Subscription} from "rxjs";
import {TaskService} from "../../service/task.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserViewModel} from "../../model/view-model/user-view-model";
import {Role} from "../../model/role";
import {Project} from "../../model/project";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ValidationService} from "../../service/validation.service";
import {Task} from "../../model/task";
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {Color} from "ng2-charts";
import {TokenStorageService} from "../../auth/token-storage.service";

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
  private taskViewModels: TaskViewModel[];
  private edit: boolean;
  private userForm: FormGroup;

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

  constructor(private userService: UserService,
              private projectService: ProjectService,
              private roleService: RoleService,
              private taskService: TaskService,
              private spinnerService: Ng4LoadingSpinnerService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private validationService: ValidationService,
              private router:Router,
              private tokenStorage:TokenStorageService) {

    this.userViewModel = new UserViewModel();
    this.editUserViewModel = new UserViewModel();
    this.edit = false;
    this.taskViewModels = [];

    // subscribe to the parameters observable
    this.route.paramMap.subscribe(params => {
      this.idUser = Number(atob(params.get('id')));
      this.loadUserViewModel();
    });


  }

  ngOnInit() {
  }

  loadUserViewModel() {
    this.spinnerService.show()
    this.subscriptions.push(this.userService.getUserViewModel(this.idUser).subscribe(user => {
      this.userViewModel = user as UserViewModel;
      this.editUserViewModel = UserViewModel.clone(user);
      this.loadTaskViewModels(user.iduser);
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

  loadTaskViewModels(idUser:number) {
    this.spinnerService.show();
    this.subscriptions.push(this.taskService.getTaskViewModelsByTaskExecutor(idUser).subscribe(taskViewModels=>{
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

  saveUserViewModel(userViewModelForSaving: UserViewModel) {
    this.spinnerService.show();
    this.subscriptions.push(this.userService.saveUserViewModel(userViewModelForSaving).subscribe(userViewModel => {
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
      this.userForm.controls['role'].clearValidators();
      if(this.tokenStorage.isAdmin() && this.userViewModel.iduser!=this.tokenStorage.getIdUser()){
        this.userForm.controls['userName'].clearValidators();
        this.userForm.controls['userSurname'].clearValidators();
        this.userForm.controls['email'].clearValidators();
      }
      if(this.tokenStorage.isProjectManager() && this.userViewModel.iduser!=this.tokenStorage.getIdUser()){
        this.userForm.controls['userName'].clearValidators();
        this.userForm.controls['userSurname'].clearValidators();
        this.userForm.controls['email'].clearValidators();
        this.userForm.controls['role'].clearValidators();
      }
      if(!this.tokenStorage.isProjectManager()
        && this.tokenStorage.isAdmin()
      && this.tokenStorage.getIdUser() == this.userViewModel.iduser){
        this.userForm.controls['role'].clearValidators();
      }
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

  btoa(s: string) {
    return btoa(s);
  }

  delete(iduser: number) {
    this.router.navigate(['/'])
    this.subscriptions.push(this.userService.delete(iduser).subscribe())
  }
}
