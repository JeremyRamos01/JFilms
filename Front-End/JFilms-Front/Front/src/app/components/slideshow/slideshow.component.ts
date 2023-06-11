import { AfterContentInit, AfterViewInit, Component, Input, OnInit } from '@angular/core';
import { Movie } from 'src/app/interfaces/carteleta-response';
import Swiper from 'swiper';

@Component({
  selector: 'app-slideshow',
  templateUrl: './slideshow.component.html',
  styleUrls: ['./slideshow.component.css']
})
export class SlideshowComponent implements OnInit, AfterViewInit {

  @Input() movies: Movie[] = [];

  public mySwiper: Swiper | undefined;

  constructor() {


   }
  ngAfterViewInit(): void {
    this.mySwiper = new Swiper('.swiper', {

      loop: true,

    });

  }

  ngOnInit(): void {
    console.log(this.movies);
  }

  onSlideNext(){
    this.mySwiper!.slideNext();

  }
  onSlidePrev(){
    this.mySwiper!.slidePrev();

  }



}
