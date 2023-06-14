import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserClass } from 'src/app/interfaces/user';
import { Usuario } from 'src/app/interfaces/usuario';
import { UsuarioService } from 'src/app/services/usuario.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent {

  uuid!: string; 
  username: string = localStorage.getItem('username')!;
  perfilForm: FormGroup = new FormGroup({});
  loading = false;
  submittedUsername = false;
  submittedEmail = false;
  submittedDni = false;
  submittedTarjeta = false;
  regularExpDni= new RegExp("/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/i");//revisar
  regularExpTarjeta = new RegExp("/^[0-9]{16}$/i;");//revisar
  returnUrl: string | undefined;
  error = '';
  constructor(private usuarioService: UsuarioService, private formBuilder: FormBuilder) 
  {
    
  }
  ngOnInit() {
    this.perfilForm = this.formBuilder.group({
      tarjeta: ["", Validators.required],
      re: ["", Validators.required],
        username: ["", Validators.required],
        email: ["", Validators.required, Validators.email],
    });
    this.cargarFormulario()
}

cargarFormulario() {
  this.usuarioService.getUsuario(this.username).subscribe(x=>{  
    this.uuid = x.uuid;
    this.perfilForm.reset({
      username: x.name,
      email: x.email,
      re: x.dni,
      tarjeta: x.tarjeta,
    })
  })
}


onSubmit() {
  console.log(this.perfilForm.value.re);
      const body:Usuario={
        uuid: this.uuid,
        name: this.perfilForm.value.username,
        email: this.perfilForm.value.email,
        dni: this.perfilForm.value.re as string,
        tarjeta: this.perfilForm.value.tarjeta,
        monto: 0,
        rol: [],
      }
      this.usuarioService.updateUsuario(body).subscribe(x=>{
        console.log(x);
      })
    }     
  }
    

