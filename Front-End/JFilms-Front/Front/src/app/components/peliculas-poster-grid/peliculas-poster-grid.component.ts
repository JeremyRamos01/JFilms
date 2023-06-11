import { Component, Input, OnInit } from '@angular/core';
import { Movie } from 'src/app/interfaces/carteleta-response';
import { Router } from '@angular/router';

@Component({
  selector: 'app-peliculas-poster-grid',
  templateUrl: './peliculas-poster-grid.component.html',
  styleUrls: ['./peliculas-poster-grid.component.css']
})
export class PeliculasPosterGridComponent implements OnInit {

  @Input() movies: Movie[] = [];


  constructor(private rout: Router) {

  }


  ngOnInit(): void {
   console.log(this.movies);
  }

  onMovieClick(movie: Movie){
    this.rout.navigate(['/pelicula', movie.id])

  }

}
