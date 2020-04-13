export class TaskViewModel {
  idtask: number;
  taskName: string;
  dateOfCreation: string;
  dueDate: string;
  taskCode: string;

  status: number;
  statusName: string;

  priority: number;
  priorityName: string;

  taskCreator: number;
  taskCreatorName: string;
  taskCreatorSurname: string;

  taskExecutor: number;
  taskExecutorName: string;
  taskExecutorSurname: string;

  project: number;
  projectName: string;

  description:string;
  updated: string;

  static clone(taskViewModel: TaskViewModel) {
    let clone = new TaskViewModel();
    clone.idtask = taskViewModel.idtask;
    clone.taskName = taskViewModel.taskName;
    clone.dateOfCreation = taskViewModel.dateOfCreation;
    clone.dueDate = taskViewModel.dueDate;
    clone.taskCode = taskViewModel.taskCode;
    clone.status = taskViewModel.status;
    clone.statusName = taskViewModel.statusName;
    clone.priority = taskViewModel.priority;
    clone.priorityName = taskViewModel.priorityName;
    clone.taskCreator = taskViewModel.taskCreator;
    clone.taskCreatorName = taskViewModel.taskCreatorName;
    clone.taskCreatorSurname = taskViewModel.taskCreatorSurname;
    clone.taskExecutor = taskViewModel.taskExecutor;
    clone.taskExecutorName = taskViewModel.taskExecutorName;
    clone.taskExecutorSurname = taskViewModel.taskExecutorSurname;
    clone.project = taskViewModel.project;
    clone.projectName = taskViewModel.projectName;
    clone.description = taskViewModel.description;
    clone.updated = taskViewModel.updated;
    return clone;
  }
}

