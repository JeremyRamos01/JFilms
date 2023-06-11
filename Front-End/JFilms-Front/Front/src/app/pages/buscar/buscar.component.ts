import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Movie } from 'src/app/interfaces/carteleta-response';
import { PeliculasService } from 'src/app/services/peliculas.service';

@Component({
  selector: 'app-buscar',
  templateUrl: './buscar.component.html',
  styleUrls: ['./buscar.component.css']
})
export class BuscarComponent implements OnInit {

  public tituloPelicula: string ="";
  public movies: Movie[]=[];




constructor(private actRoute: ActivatedRoute, private _ps: PeliculasService){


}
  ngOnInit(): void {
    this.actRoute.params.subscribe(params => {
      this.tituloPelicula = params['nombre'];
      this._ps.buscarPelicula(params['nombre']).subscribe(peliculas => {
        this.movies=peliculas.filter(pelicula => pelicula.poster_path !== null)
      });
    } );

  }



}
