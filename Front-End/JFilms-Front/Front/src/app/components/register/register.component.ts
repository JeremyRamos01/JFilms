import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  loginForm: FormGroup = new FormGroup({});
  loading = false;
  submittedUsername = false;
  submittedPassword = false;
  submittedEmail = false;
  submittedDni = false;
  submittedTarjeta = false;
  regularExpDni= new RegExp("/^[0-9]{8}[TRWAGMYFPDXBNJZSQVHLCKE]$/i");//revisar
  regularExpTarjeta = new RegExp("/^[0-9]{16}$/i;");//revisar

  returnUrl: string | undefined;
  error = '';
  show=false;

  constructor(
      private formBuilder: FormBuilder,
      private route: ActivatedRoute,
      private router: Router,
      private loginService: LoginService
     
  ) { 
      
  }

  ngOnInit() {
      this.loginForm = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required],
          email: ['', Validators.required, Validators.email],
          dni: [, Validators.required],
          tarjeta: ['', Validators.required]
      });
  }

  onRegresar(){
        console.log("regresar");
        this.router.navigate(['/login']);
    }

  onSubmit() {
      if(this.loginForm.value.password == "" || this.loginForm.value.password.length <4){
          this.submittedPassword = true;
      }
      if(this.loginForm.value.username == ""  ){
          this.submittedUsername = true;
      }
        if(this.loginForm.value.email == "" ){
            this.submittedEmail = true;
        }
        if(this.loginForm.value.dni == "" || this.loginForm.value.dni < 8 ){
            this.submittedDni = true;
        }
        if(this.loginForm.value.tarjeta == ""  || this.loginForm.value.tarjeta < 16){
            this.submittedTarjeta = true;
        }

      if (this.loginForm.invalid) {
          return;
      }else{
         this.loginService.register(this.loginForm.value.username,this.loginForm.value.password,this.loginForm.value.email,this.loginForm.value.dni,this.loginForm.value.tarjeta).subscribe(x=>{
          if(this.loginService.obtenerDatosToken(x.token)?.name == this.loginForm.value.username){
              this.router.navigate(['/home']);
          }else{
              this.loading = true;
              this.loading = false;
          }
         })    
      }
  
  }

}
