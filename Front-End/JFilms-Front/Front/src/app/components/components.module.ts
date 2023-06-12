import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';
import { SlideshowComponent } from './slideshow/slideshow.component';
import { PeliculasPosterGridComponent } from './peliculas-poster-grid/peliculas-poster-grid.component';
import {RatingModule} from 'ng-starrating'
import { PipesModule } from '../pipes/pipes.module';
import { CastSlideshowComponent } from './cast-slideshow/cast-slideshow.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { DialogModule } from 'primeng/dialog';
import { BrowserModule } from '@angular/platform-browser';
import { ButtonModule } from 'primeng/button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RegisterComponent } from './register/register.component';
import { ButacasComponent } from './butacas/butacas.component';
import { ReservaComponent } from './reserva/reserva.component';
import { MisEntradasComponent } from './mis-entradas/mis-entradas.component';
import { PerfilComponent } from './perfil/perfil.component';
import { NgxQRCodeModule } from '@techiediaries/ngx-qrcode';



@NgModule({
  declarations: [
    NavbarComponent,
    SlideshowComponent,
    PeliculasPosterGridComponent,
    CastSlideshowComponent,
    LoginComponent,
    RegisterComponent,
    ButacasComponent,
    ReservaComponent,
    MisEntradasComponent,
    PerfilComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    RatingModule,
    PipesModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserModule,
    BrowserAnimationsModule,
    ButtonModule,
    DialogModule,
    ProgressSpinnerModule,
    NgxQRCodeModule
  ],
  exports:[
    NavbarComponent,
    SlideshowComponent,
    PeliculasPosterGridComponent,
    CastSlideshowComponent,
    LoginComponent,
    RegisterComponent,
    ButacasComponent,
    ReservaComponent,
    MisEntradasComponent,
    PerfilComponent
  ]
})
export class ComponentsModule { }
