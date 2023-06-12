import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {Location} from '@angular/common';
import { Butacas } from 'src/app/interfaces/butacas';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Usuario } from 'src/app/interfaces/usuario';
import { ReservaService } from 'src/app/services/reserva.service';
import { Reserva } from 'src/app/interfaces/reserva';

@Component({
  selector: 'app-reserva',
  templateUrl: './reserva.component.html',
  styleUrls: ['./reserva.component.css']
})
export class ReservaComponent {


  reservadas : Butacas[]=[];
  precioPorButaca : number = 9.99;
  precioTotal : number = 0;
  username : string = localStorage.getItem('username')!;
  usuario!: Usuario;
  loading: boolean = false;
  nombrePelicula: string = "";

  constructor(
    private location: Location,
    private usuarioService: UsuarioService,
    private reservaService: ReservaService,
    private route: ActivatedRoute,
    private router: Router,
    
  ) {
    this.nombrePelicula = String(this.route.snapshot.paramMap.get('nombre'));
    const listaReservadasOriginal = this.route.snapshot.queryParamMap.get('lista');
    this.reservadas = JSON.parse(listaReservadasOriginal!);
    this.precioTotal = this.reservadas.length * this.precioPorButaca;
    this.usuarioService.getUsuario(this.username).subscribe(x=>{
      this.usuario = x;
      localStorage.setItem('uuid', this.usuario.uuid);
    }
    );
    
   }

   onRegresar(){
    this.location.back();
  }
  reservar(){
    this.loading = true;
    setTimeout( () => {
      if(this.usuario.monto > this.precioTotal && this.usuario.monto > 0){
        this.usuario.monto = this.usuario.monto - this.precioTotal;
        this.usuarioService.updateUsuario(this.usuario).subscribe(x=>{
          this.usuario = x;
          this.loading = false;
        });
      }
    }, 3000 );
    this.reservadas.forEach(x=>{
      const reservaCreada: Reserva = {
        fila: x.fila,
        numeroButaca: x.numero,
        usuarioUuid: this.usuario.uuid,
        tituloPelicula: this.nombrePelicula

      }
    this.reservaService.postReserva(reservaCreada).subscribe(x=>{
      console.log(x);
    })});
    this.router.navigate(['/home']);
    
}

}
