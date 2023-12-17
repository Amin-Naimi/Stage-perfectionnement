import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { EncadreurSeviceService } from 'src/app/Services/encadreur-sevice.service';
import { constants } from 'src/app/shared/global/constants';

@Component({
  selector: 'app-login-c',
  templateUrl: './login-c.component.html',
  styleUrls: ['./login-c.component.css']
})
export class LoginCComponent {

  responseMessage: any;
  showResponseMessage = false;


  constructor(private form: FormBuilder,
    private ngxService: NgxUiLoaderService,
    private EncadreurService: EncadreurSeviceService,
    private router: Router,
    public dialogRef: MatDialogRef<LoginCComponent>) { }


    loginEncadreurForm = this.form.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    })

    showAlert(message:string) {
      this.showResponseMessage = true;
      this.responseMessage = message;
      setTimeout(() => {
        this.showResponseMessage = false;
      }, 18000);
    }
    onSubmitEncadreur() {
      console.warn(this.loginEncadreurForm.value)
      this.ngxService.start();
      const formData = this.loginEncadreurForm.value;
      var data = {
        email: formData.email,
        password: formData.password,
      }; console.log(data);
      this.EncadreurService.loginEncadreur(data).subscribe((response: any) => {
        this.ngxService.stop();
        this.dialogRef.close();
        //Besoin d'utiliser le token pour consomer les autres API
        //Don il faut enregister le token
        localStorage.setItem('token', response.Token);
        console.log(response.Token);
        this.router.navigate(['Encadreur-Dashboard']);
      }, (error) => {
        if (error.error?.message) {
          this.responseMessage = error.error?.message;
          console.log(this.responseMessage);
        }
        else {
          this.responseMessage = constants.genericError;
          console.log(this.responseMessage);
        }//Affichage du messafge d'erreure
        this.ngxService.stop();
         this.showAlert(this.responseMessage);
      });
    }













}
