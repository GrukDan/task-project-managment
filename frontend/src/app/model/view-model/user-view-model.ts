export class UserViewModel {
  iduser:number;

  role:number;
  roleName:string;

  userName:string;
  userSurname:string;
  email:string;

  assignProject:number;
  assignProjectName:string;


  public static clone(userViewModel:UserViewModel):UserViewModel{
    const clone:UserViewModel = new UserViewModel();
    clone.iduser = userViewModel.iduser;
    clone.role = userViewModel.role;
    clone.roleName = userViewModel.roleName;
    clone.userName = userViewModel.userName;
    clone.userSurname = userViewModel.userSurname;
    clone.email = userViewModel.email;
    clone.assignProjectName = userViewModel.assignProjectName;
    clone.assignProject = userViewModel.assignProject;
    return clone;
  }
}
