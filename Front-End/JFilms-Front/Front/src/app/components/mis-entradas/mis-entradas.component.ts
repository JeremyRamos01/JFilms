import { Component } from '@angular/core';
import { Location } from '@angular/common';
import { ReservaService } from 'src/app/services/reserva.service';
import { Reserva, ReservaDto } from 'src/app/interfaces/reserva';
import { NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels } from '@techiediaries/ngx-qrcode';
import { Entradas } from 'src/app/interfaces/entradas';

@Component({
  selector: 'app-mis-entradas',
  templateUrl: './mis-entradas.component.html',
  styleUrls: ['./mis-entradas.component.css']
})
export class MisEntradasComponent {

  entradas : ReservaDto[]=[];
  elementType = NgxQrcodeElementTypes.URL;
  correctionLevel = NgxQrcodeErrorCorrectionLevels.HIGH;
  value = 'https://www.youtube.com';

  tituloPelicula: string = "";
  fila: number = 0;
  columna: number = 0;
  fecha: string = "";
  precio: number = 9.99;



  loading: boolean = false;
  constructor(
     private location: Location,
     private reservaService: ReservaService,
  ) { 

    this.reservaService.getReservas(localStorage.getItem('uuid')!).subscribe(x=>{
      this.entradas = x;
      console.log(this.entradas);
    }
    );
  }

  onRegresar(){
     this.location.back();
  }

  generarQR(entrada: ReservaDto){
    this.tituloPelicula = entrada.tituloPelicula;
    this.fila = entrada.fila;
    this.columna = entrada.numeroButaca;
    this.fecha = entrada.fechaHoraReserva;

  


    this.loading = true;
    setTimeout( () => {
      this.loading = false;
    }, 10000 );
  }




}
