import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Butacas } from 'src/app/interfaces/butacas';
import { ButacasService } from 'src/app/services/butacas.service';

@Component({
  selector: 'app-butacas',
  templateUrl: './butacas.component.html',
  styleUrls: ['./butacas.component.css']
})
export class ButacasComponent implements OnInit {

  asientos!: number;
  butacas: Butacas[] = [];
  isSeleccionada =false;
  idSala: number = 0;
  listaReservadas: Butacas[] = [];
  nombrePelicula: string = "";
  constructor(private butacasService: ButacasService, private router : ActivatedRoute, private route: Router) {
    this.butacasService.getButacasBySala(this.idSala).subscribe(
      butacas => {
        this.butacas = butacas;
      }
    );
   }

  ngOnInit(): void {
    this.asientos = 0;
    this.nombrePelicula = String(this.router.snapshot.paramMap.get('nombre'));
    this.idSala = Number(this.router.snapshot.paramMap.get('id'));
    /* haz que cada 5s se repita el metodo de abajo */
      this.butacasService.getButacasBySala(this.idSala).subscribe(
        butacas => {
          this.butacas = butacas;
        }
      );
  }

  aumentar(){
    this.asientos++;
    console.log(this.asientos);
  }

  disminuir(){
    this.asientos--;
    console.log(this.asientos);
  }

    seleccionada(butaca: Butacas){
      butaca.reservada = !butaca.reservada;
      this.butacasService.updateButaca(butaca).subscribe(
        butaca => {
          if(butaca.reservada){
            if(!this.listaReservadas.includes(butaca)){
            this.listaReservadas.push(butaca);
          }
          }
          else{
            this.listaReservadas.splice(this.listaReservadas.indexOf(butaca), 1);
          }
        }
      );
    }

    onReservar(){
      this.route.navigate(['/reservar', this.nombrePelicula], { queryParams: { lista: JSON.stringify(this.listaReservadas)}});
    }

    reservar(){
      if(this.listaReservadas.length > this.asientos){
        console.log("No se puede reservar mas butacas de las que hay");
        return;
      }else{
        this.onReservar();
        this.asientos = 0;
      };
    }
  
}
