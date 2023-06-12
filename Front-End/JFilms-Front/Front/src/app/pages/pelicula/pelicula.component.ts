import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieResponse } from 'src/app/interfaces/movie-response';
import { PeliculasService } from 'src/app/services/peliculas.service';
import {Location} from '@angular/common';
import { Cast } from 'src/app/interfaces/credits-response';
import { Salas } from 'src/app/interfaces/salas';
import { SalasService } from 'src/app/services/salas.service';

@Component({
  selector: 'app-pelicula',
  templateUrl: './pelicula.component.html',
  styleUrls: ['./pelicula.component.css']
})
export class PeliculaComponent implements OnInit {

  public movie : MovieResponse | any;
  public salas : Salas[] = [];
  public cast : Cast[] = [];

constructor(private activatedRoute: ActivatedRoute,
            private route: Router,
            private peliservice: PeliculasService,
            private salasService: SalasService,
            private location: Location){

}
  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    console.log(id);
    this.peliservice.getPeliculaDetalle(id).subscribe(movie =>{
      if(!movie){
        this.route.navigateByUrl('/home');
        return;

      }
      this.movie = movie;
    });

    this.salasService.getSalasByPelicula(id).subscribe(salas => {
      console.log(salas);
      this.salas = salas;
    });

  }

  onButacas(id: number){
    this.route.navigate(['/butacas', id, this.movie.title]);
  }

  onRegresar(){
    this.location.back();
  }


}
