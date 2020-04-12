export class ProjectViewModel{
  idproject:number;
  projectCode:string;
  projectName:string;
  projectCreator:number;
  dateOfCompletion:string;
  readinessDegree:number;
  projectCreatorName:string;
  projectCreatorSurname:string;
  description:string;

  public static clone(projectViewModel: ProjectViewModel):ProjectViewModel{
    let clone:ProjectViewModel = new ProjectViewModel();
    clone.idproject = projectViewModel.idproject;
    clone.projectCode = projectViewModel.projectCode;
    clone.projectName = projectViewModel.projectName;
    clone.projectCreator = projectViewModel.projectCreator;
    clone.dateOfCompletion = projectViewModel.dateOfCompletion;
    clone.readinessDegree = projectViewModel.readinessDegree;
    clone.projectCreatorName = projectViewModel.projectCreatorName;
    clone.projectCreatorSurname = projectViewModel.projectCreatorSurname;
    clone.description = projectViewModel.description;
    return clone;
  }
}
