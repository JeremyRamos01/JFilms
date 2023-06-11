import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Movie } from 'src/app/interfaces/carteleta-response';
import { PeliculasService } from 'src/app/services/peliculas.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent  implements OnInit {

public movies: Movie[] = [];
public moviesSlideShow: Movie[] = [];


constructor( private service :PeliculasService ){
  }

  ngOnInit(): void {
    this.service.getCartelera().subscribe((data:Movie[]) => {
      this.movies = data;
      this.moviesSlideShow = data;
    });
  }
}



