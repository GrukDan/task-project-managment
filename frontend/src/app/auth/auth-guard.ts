import {Injectable} from "@angular/core";
import {CanActivate, Router} from "@angular/router";
import {UserService} from "../service/user.service";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
import {AuthService} from "./auth-service";


@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
  constructor(public tokenStorage: TokenStorageService, public router: Router) {}
  canActivate(): boolean {
    if (this.tokenStorage.getToken()!=null) {
      return true;
    }
    return false;
  }
}
