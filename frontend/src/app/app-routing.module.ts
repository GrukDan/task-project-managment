import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {TaskPageComponent} from "./component/task-page/task-page.component";
import {ProjectPageComponent} from "./component/project-page/project-page.component";
import {FirstPageComponent} from "./component/first-page/first-page.component";
import {NotFoundPageComponent} from "./component/not-found-page/not-found-page.component";
import {ProjectTableComponent} from "./component/project-table/project-table.component";
import {TaskTableComponent} from "./component/task-table/task-table.component";
import {UserTableComponent} from "./component/user-table/user-table.component";


const routes: Routes = [
  {path: "", component: FirstPageComponent},
  {path: "tasks", component: TaskPageComponent},
  {path: "projects/:id/:project_name", component: ProjectPageComponent},
  {path: "home/:id/:username", component: HomeComponent},
  {path: "projects/table", component: ProjectTableComponent},
  {path: "tasks/table", component: TaskTableComponent},
  {path: "users/table", component: UserTableComponent},
  {path: "**", component: NotFoundPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
