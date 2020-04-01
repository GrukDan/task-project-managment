import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService, CarouselConfig} from "ngx-bootstrap";

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css'],
  providers: [
    { provide: CarouselConfig, useValue: { interval: 2500, noPause: true, showIndicators: true } }
  ]
})
export class FirstPageComponent implements OnInit {

  modalRef: BsModalRef;
  config = {
    animated: true
  };
  templateShow: boolean;

  constructor(private modalService: BsModalService,) {
    this.templateShow = true;
  }


  ngOnInit() {
  }


  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  authorization() {

  }
}
