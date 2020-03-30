import {Component, OnInit, TemplateRef} from '@angular/core';
import { BsDropdownConfig } from 'ngx-bootstrap/dropdown';
import {Subscription} from "rxjs";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  providers: [{ provide: BsDropdownConfig, useValue: { isAnimated: true, autoClose: true } }]
})
export class HeaderComponent implements OnInit {

  subscriptions: Subscription[] = [];
  modalRef: BsModalRef;
  config = {
    animated: true
  };

  constructor(private modalService: BsModalService,
              private userService: UserService) { }

  ngOnInit() {
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }
}
