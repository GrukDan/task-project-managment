import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {TaskViewModel} from "../../model/view-model/task-view-model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BsLocaleService, defineLocale, ruLocale} from "ngx-bootstrap";
import {ProjectService} from "../../service/project.service";
import {TaskService} from "../../service/task.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {ActivatedRoute, Router} from "@angular/router";
import {ValidationService} from "../../service/validation.service";
import {ProjectViewModel} from "../../model/view-model/project-view-model";
import {UserService} from "../../service/user.service";
import {PriorityService} from "../../service/priority.service";
import {StatusService} from "../../service/status.service";
import {Status} from "../../model/status";
import {Priority} from "../../model/priority";
import {User} from "../../model/user";
import {Comment} from "../../model/comment";
import {CommentService} from "../../service/comment.service";
import {CommentViewModel} from "../../model/view-model/comment-view-model";
import {TokenStorageService} from "../../auth/token-storage.service";
import {log} from "util";

defineLocale('ru', ruLocale);

@Component({
  selector: 'app-task-page',
  templateUrl: './task-page.component.html',
  styleUrls: ['./task-page.component.css']
})
export class TaskPageComponent implements OnInit {

  private subscriptions: Subscription[] = []
  private taskViewModel: TaskViewModel;
  private editTaskViewModel: TaskViewModel;

  private comment: Comment;
  private commentViewModels: CommentViewModel[];
  private statuses: Status[];
  private priorities: Priority[];
  private executors: User[];

  private idTask: number;
  private edit: boolean;
  private size:number;
  private totalComments:number;
  private moreCommentsButton:boolean;
  private taskForm: FormGroup;

  locale = "ru";
  minDate: Date;
  dueDateInput: Date;

  constructor(private taskService: TaskService,
              private userService: UserService,
              private priorityService: PriorityService,
              private statusService: StatusService,
              private commentService: CommentService,
              private spinnerService: Ng4LoadingSpinnerService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private validationService: ValidationService,
              private localeService: BsLocaleService,
              private tokenStorage:TokenStorageService,
              private router:Router) {
    this.comment = new Comment();
    this.commentViewModels = [];
    this.statuses = [];
    this.priorities = [];
    this.executors = []
    this.taskViewModel = new TaskViewModel();
    this.editTaskViewModel = new TaskViewModel();
    this.edit = false;
    this.size = 5;
    this.totalComments = 0;
    this.moreCommentsButton = true;
    this.minDate = new Date();

    this.localeService.use(this.locale);

    this.route.paramMap.subscribe(params => {
      this.idTask = Number(atob(params.get('id')));
    });
  }

  ngOnInit() {
    this.loadTaskViewModel();
    this.loadComments();
  }

  loadTaskViewModel() {
    this.spinnerService.show()
    this.subscriptions.push(this.taskService.getTaskViewModelById(this.idTask).subscribe(taskViewModel => {
      this.taskViewModel = taskViewModel as TaskViewModel;
      this.editTaskViewModel = TaskViewModel.clone(taskViewModel);
      this.dueDateInput = new Date(taskViewModel.dueDate);
      this.spinnerService.hide();
    }))
  }

  loadComments() {
    this.spinnerService.show()
    this.subscriptions.push(this.commentService.getAll(this.size,this.idTask).subscribe(commentViewModels=>{
      this.commentViewModels= commentViewModels as CommentViewModel[];
      if(this.commentViewModels.length!=0){
        console.log(this.commentViewModels[0])
        this.totalComments = this.commentViewModels[0].totalComments;
      }else {
        this.moreCommentsButton = false;
      }
      this.spinnerService.hide();
    }))
  }

  private loadPriority(): void {
    if (this.priorities.length == 0) {
      this.spinnerService.show();
      this.subscriptions.push(this.priorityService.getAllPriority().subscribe(priorities => {
        this.priorities = priorities as Priority[];
        this.spinnerService.hide()
      }))
    }
  }

  private loadStatus(): void {
    if (this.statuses.length == 0) {
      this.spinnerService.show();
      this.subscriptions.push(this.statusService.getAllStatus().subscribe(statuses => {
        this.statuses = statuses as Status[];
        this.spinnerService.hide()
      }))
    }
  }

  private loadExecutors(idProject: number): void {
    if (this.executors.length == 0) {
      this.spinnerService.show();
      this.subscriptions.push(this.userService.getUserByAssignProject(idProject).subscribe(executors => {
        this.executors = executors as User[];
        this.spinnerService.hide()
      }))
    }
  }

  changeEdit() {
    this.edit = !this.edit;
    if (this.edit) {
      this.loadExecutors(this.editTaskViewModel.project);
      this.loadPriority();
      this.loadStatus()
    }
  }

  private _createForm() {
    if (this.taskForm == null) {
      this.taskForm = this.validationService.getTaskFormGroup();
      this.taskForm.controls['taskProject'].clearValidators();
    }
    if (this.tokenStorage.isTester()){
      this.taskForm.controls['priority'].clearValidators();
    }
    if(this.tokenStorage.isDeveloper() || this.tokenStorage.isTester()){
      this.taskForm.controls['taskName'].clearValidators();
      this.taskForm.controls['dueDate'].clearValidators();
    }else {
      this.taskForm.controls['status'].clearValidators();
      this.taskForm.controls['priority'].clearValidators();
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

  saveComment(comment: Comment) {
    if(comment.comment != "") {
      this.comment.timeOfCreation = Date.now().toString();
      this.comment.task = this.idTask;
      this.comment.user = this.tokenStorage.getUser()['idUser'];
      this.spinnerService.show()
      this.subscriptions.push(this.commentService.save(comment).subscribe(() => {
        this.loadComments();
        this.spinnerService.hide();
      }))
      this.comment = new Comment();
    }
  }

  save() {
    this.changeEdit();
    this.editTaskViewModel.dueDate = this.dueDateInput.toISOString();
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

  delete(idTask) {
    this.router.navigate(['/'])
    this.subscriptions.push(this.taskService.delete(idTask).subscribe())
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

  more() {
    if(this.size < this.totalComments) {
      this.size = this.size + 5;
    }else {
      this.moreCommentsButton = false;
    }
    this.loadComments()
  }
}
