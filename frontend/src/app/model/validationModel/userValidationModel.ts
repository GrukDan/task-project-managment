import {FormGroup} from "@angular/forms";

export class UserValidationModel {

  constructor(private userForm:FormGroup){ }

  get _userForm():FormGroup{
    return this._userForm;
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
}
