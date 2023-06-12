import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, catchError, map, of, tap } from 'rxjs';
import { Movie } from '../interfaces/carteleta-response';
import { MovieResponse } from '../interfaces/movie-response';
import { CreditsResponse } from '../interfaces/credits-response';


@Injectable({
  providedIn: 'root'
})
export class PeliculasService {
  public cargando : boolean = false;

  constructor(private http: HttpClient) {
   }

   get params(){
    return{
      api_key:'8a9f323efcc9446dd444dd37330c4fde',
      language:'es-ES',
   }}

   buscarPelicula(nombre: string):Observable<Movie[]>
   {
    const params = {...this.params, page:1, query: nombre}
    return this.http.get<Movie[]>(`https://api.themoviedb.org/3/search/movie`,{
      params
    }).pipe(
      map(resp => resp)
    )
   }


  getCartelera():Observable<Movie[]>|any{
    if(this.cargando){
      return;
    }
    return this.http.get<Movie[]>(`http://localhost:7070/api/peliculas`
    )
  }


  


getPeliculaDetalle(id:number){
  return this.http.get<Movie>(`http://localhost:7070/api/peliculas/${id}`)
  .pipe(
    catchError(err => of(null))
  )
}


}
