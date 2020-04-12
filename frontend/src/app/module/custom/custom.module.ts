import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {UserService} from "../../service/user.service";
import {TaskService} from "../../service/task.service";
import {ProjectService} from "../../service/project.service";
import {FirstPageComponent} from "../../component/first-page/first-page.component";
import {HomeComponent} from "../../component/home/home.component";
import {NotFoundPageComponent} from "../../component/not-found-page/not-found-page.component";
import {ProjectPageComponent} from "../../component/project-page/project-page.component";
import {TaskPageComponent} from "../../component/task-page/task-page.component";
import {
  BsDatepickerModule,
  BsLocaleService,
  CarouselModule,
  DatepickerModule,
  PaginationModule,
  TabsModule
} from "ngx-bootstrap";
import {TaskTableComponent} from "../../component/task-table/task-table.component";
import {ProjectTableComponent} from "../../component/project-table/project-table.component";
import {UserTableComponent} from "../../component/user-table/user-table.component";
import {AppRoutingModule} from "../../app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {RoleService} from "../../service/role.service";
import {PriorityService} from "../../service/priority.service";
import {StatusService} from "../../service/status.service";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";


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
    HttpClientModule,
    Ng4LoadingSpinnerModule,
    FormsModule,
    ReactiveFormsModule,
    BsDatepickerModule,
    DatepickerModule,
  ],
  exports: [
    FirstPageComponent,
    HomeComponent,
    NotFoundPageComponent,
    ProjectPageComponent,
    TaskPageComponent,
    TaskTableComponent,
    ProjectTableComponent,
    UserTableComponent,
    BsDatepickerModule,
  ],

  providers: [
    UserService,
    TaskService,
    ProjectService,
    RoleService,
    PriorityService,
    StatusService,
    BsLocaleService
  ]
})
export class CustomModule {
}
