import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserService} from "../../service/user.service";
import {TaskService} from "../../service/task.service";
import {ProjectService} from "../../service/project.service";
import {FirstPageComponent} from "../../component/first-page/first-page.component";
import {HomeComponent} from "../../component/home/home.component";
import {NotFoundPageComponent} from "../../component/not-found-page/not-found-page.component";
import {ProjectPageComponent} from "../../component/project-page/project-page.component";
import {TaskPageComponent} from "../../component/task-page/task-page.component";
import {CarouselModule, PaginationModule, TabsModule} from "ngx-bootstrap";
import {TaskTableComponent} from "../../component/task-table/task-table.component";
import {ProjectTableComponent} from "../../component/project-table/project-table.component";
import {UserTableComponent} from "../../component/user-table/user-table.component";
import {AppRoutingModule} from "../../app-routing.module";



@NgModule({
  declarations: [
    FirstPageComponent,
    HomeComponent,
    NotFoundPageComponent,
    ProjectPageComponent,
    TaskPageComponent,
    TaskTableComponent,
    ProjectTableComponent,
    UserTableComponent
  ],
  imports: [
    CommonModule,
    CarouselModule,
    AppRoutingModule,
    PaginationModule,
    TabsModule,
  ],
  exports:[
    FirstPageComponent,
    HomeComponent,
    NotFoundPageComponent,
    ProjectPageComponent,
    TaskPageComponent,
    TaskTableComponent,
    ProjectTableComponent,
    UserTableComponent
  ],
  providers:[UserService,
  TaskService,
  ProjectService]
})
export class CustomModule { }
