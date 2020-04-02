import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {HeaderComponent} from "../../component/header/header.component";
import {AppRoutingModule} from "../../app-routing.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {BsDatepickerModule, BsDropdownModule, DatepickerModule} from "ngx-bootstrap";
import {Ng4LoadingSpinnerModule} from "ng4-loading-spinner";



@NgModule({
  declarations: [HeaderComponent],
  imports: [
    CommonModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    BsDropdownModule.forRoot(),
    ReactiveFormsModule,
    Ng4LoadingSpinnerModule,
    BsDatepickerModule,
    DatepickerModule,
  ],
  exports:[HeaderComponent]
})
export class HeaderModule { }
