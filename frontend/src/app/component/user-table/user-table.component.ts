import { Component, OnInit } from '@angular/core';
import {UserViewModel} from "../../model/view-model/user-view-model";
import {PageChangedEvent} from "ngx-bootstrap";

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.css']
})
export class UserTableComponent implements OnInit {

  private parameters:string[];
  private userViewModels:UserViewModel[];

  constructor() {
    this.parameters = ["userName","userSurname","email","role"]
    this.userViewModels = [];
  }

  ngOnInit() {
  }

  sort(string: string) {

  }

  pageChanged($event: PageChangedEvent) {

  }
}
