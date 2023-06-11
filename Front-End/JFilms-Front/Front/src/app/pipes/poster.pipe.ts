import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'poster'
})
export class PosterPipe implements PipeTransform {

  transform(poster: string): unknown {

    if(poster){
      return `https://image.tmdb.org/t/p/w500${poster}`;
    }else{
      return './assets/no-image.jpg';
    }

    return null;
  }

  //https://image.tmdb.org/t/p/w500{{item.poster_path}}
}
