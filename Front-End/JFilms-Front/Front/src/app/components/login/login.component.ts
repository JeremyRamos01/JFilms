import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
    loginForm: FormGroup = new FormGroup({});
    loading = false;
    submittedUsername = false;
    submittedPassword = false;

    returnUrl: string | undefined;
    error = '';
    show=false;

    constructor(
        private formBuilder: FormBuilder,
        private router: Router,
        private loginService: LoginService
       
    ) { 
        
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });
    }   

    onRegister(){
        console.log("register");
        this.router.navigate(['/registrarse']);
    }

    onSubmit() {
        if(this.loginForm.value.password == "" || this.loginForm.value.password.length <4){
            this.submittedPassword = true;
        }
        if(this.loginForm.value.username == "" ){
            this.submittedUsername = true;
        }
        if (this.loginForm.invalid) {
            return;
        }else{
           this.loginService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe(x=>{
            if(this.loginService.obtenerDatosToken(x.token)?.name == this.loginForm.value.username){
                localStorage.setItem('username', this.loginForm.value.username);
                localStorage.setItem('token', x.token);
                this.router.navigate(['/home']);
            }else{
                this.loading = true;
                this.loading = false;
            }
           })    
        }
    
    }

}
