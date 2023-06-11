import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { PeliculaComponent } from './pages/pelicula/pelicula.component';
import { BuscarComponent } from './pages/buscar/buscar.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ButacasComponent } from './components/butacas/butacas.component';
import { ReservaComponent } from './components/reserva/reserva.component';

const routes: Routes = [

  {
    path: 'loguear',
    component: LoginComponent
  },
  {
    path: 'registrarse',
    component: RegisterComponent
  },
  { 
    path: 'reservar',
    component: ReservaComponent
  },
  {
    path: 'pelicula/:id',
    component: PeliculaComponent
  },
  {
    path: 'butacas/:id',
    component: ButacasComponent
  },
  {
    path: 'buscar/:nombre',
    component: BuscarComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: '**',
    redirectTo: '/loguear'
  },
  

]

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
  exports:[
    RouterModule
  ]
})
export class AppRoutingModule { }
