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
import {AuthGuardService} from "./auth/auth-guard";


const routes: Routes = [
  {
    path: "",
    component: FirstPageComponent},
  {
    path: "tasks/:id/:task_name",
    component: TaskPageComponent,
  canActivate:[AuthGuardService]
  },
  {
    path: "projects/:id/:project_name",
    component: ProjectPageComponent,
    canActivate:[AuthGuardService]},
  {
    path: "home/:id/:username",
    component: HomeComponent,
    canActivate:[AuthGuardService]},
  {
    path: "projects/table",
    component: ProjectTableComponent,
    canActivate:[AuthGuardService]},
  {
    path: "tasks/table",
    component: TaskTableComponent,
    canActivate:[AuthGuardService]},
  {
    path: "users/table",
    component: UserTableComponent,
    canActivate:[AuthGuardService]},
  {
    path: "**",
    component: NotFoundPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
