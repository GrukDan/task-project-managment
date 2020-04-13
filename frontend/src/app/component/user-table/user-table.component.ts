import { Component, OnInit } from '@angular/core';
import {UserViewModel} from "../../model/view-model/user-view-model";
import {PageChangedEvent} from "ngx-bootstrap";
import {Subscription} from "rxjs";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";
import {UserService} from "../../service/user.service";
import {ProjectViewModel} from "../../model/view-model/project-view-model";

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  private parameters:string[];
  private userViewModels:UserViewModel[];
  private subscriptions: Subscription[] = [];

  private countOfPages: number;
  private parameter: string;
  private direction: boolean;
  private size: number;
  private page: number;

  constructor(private spinnerService: Ng4LoadingSpinnerService,
              private userService:UserService) {
    this.userViewModels = [];
    this.page = 0;
    this.size = 5;
    this.direction = true;
    this.countOfPages = 11;
  }

  ngOnInit() {
    this.loadSortParameters();
  }

  sort(parameter: string) {
    this.parameter = parameter;
    this.direction = !this.direction;
    this.loadUsers();
  }

  pageChanged($event: PageChangedEvent) {
    this.page = $event.page - 1;
    this.loadUsers();
  }

  loadSortParameters() {
    this.spinnerService.show();
    this.subscriptions.push(this.userService.getSortParameter().subscribe(parameters => {
      this.parameters = parameters as string[];
      this.parameter = this.parameters[0];
      this.loadUsers()
      this.spinnerService.hide();
    }))
  }

  loadUsers() {
    this.spinnerService.show();
    this.subscriptions.push(this.userService.getSortedUser(
      this.parameter,
      this.page,
      this.size,
      this.direction
    ).subscribe(sortedUsers => {
      this.userViewModels = sortedUsers.userViewModels as UserViewModel[];
      this.countOfPages = sortedUsers.countOfPages*10;
      this.spinnerService.hide();
    }))
  }

  btoa(s: string) {
    return btoa(s);
  }
}
