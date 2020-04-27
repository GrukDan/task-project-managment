import {Component, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService, CarouselConfig} from "ngx-bootstrap";
import {UserService} from "../../service/user.service";
import {Subscription} from "rxjs";
import {Router} from "@angular/router";
import {User} from "../../model/user";
import {AuthService} from "../../auth/auth-service";
import {TokenStorageService} from "../../auth/token-storage.service";
import {Ng4LoadingSpinnerService} from "ng4-loading-spinner";

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css'],
  providers: [
    {provide: CarouselConfig, useValue: {interval: 25000, noPause: true, showIndicators: true}}
  ]
})
export class FirstPageComponent implements OnInit {

  modalRef: BsModalRef;
  config = {
    animated: true
  };
  templateShow: boolean;
  private subscriptions: Subscription[];
  private user: User;

  constructor(private modalService: BsModalService,
              private userService: UserService,
              private spinnerService: Ng4LoadingSpinnerService,
              private router: Router, private authService: AuthService,
              private tokenStorage: TokenStorageService) {
    this.templateShow = true;
    this.user = new User();
    this.subscriptions = [];
  }


  ngOnInit() {
    this.templateShow = !this.tokenStorage.isAuthorized()
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template, this.config);
  }

  authorization() {
    this.spinnerService.show()
    this.authService.login(this.user).subscribe(
      data => {
        this.spinnerService.hide()
        this.tokenStorage.saveToken(data.jwttoken);
        this.tokenStorage.saveUser(data);
        this.router.navigate(['/home',
          btoa(data.idUser),
          data.userName + data.userSurname ]);
        this.modalRef.hide()
      },
      err => {
        console.log(err.error.message)
      }
    );
  }
}
