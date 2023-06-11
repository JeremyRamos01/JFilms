import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import{ FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { ComponentsModule } from './components/components.module';
import { PagesModule } from './pages/pages.module';




@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    ComponentsModule,
    PagesModule,
    FormsModule,
    ReactiveFormsModule,
    ButtonModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
