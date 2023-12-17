import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StagaireService } from 'src/app/Services/stagaire.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { constants } from 'src/app/shared/global/constants';
import { error } from 'jquery';


@Component({
  selector: 'app-login-modal',
  templateUrl: './login-modal.component.html',
  styleUrls: ['./login-modal.component.css']
})
export class LoginModalComponent {
  responseMessage: any;
  showResponseMessage = false;


  constructor(private form: FormBuilder,
    private ngxService: NgxUiLoaderService,
    private stagaireService: StagaireService,
    private router: Router,
    public dialogRef: MatDialogRef<LoginModalComponent>) { }

  loginForm = this.form.group({
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
  onSubmit() {
    console.warn(this.loginForm.value)
    this.ngxService.start();
    const formData = this.loginForm.value;
    var data = {
      email: formData.email,
      password: formData.password,
    }; console.log(data);
    this.stagaireService.login(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.dialogRef.close();
      //Besoin d'utiliser le token pour consomer les autres API
      //Don il faut enregister le token
      localStorage.setItem('token', response.Token);
      console.log("jeton stagaire",response.Token);
      this.router.navigate(['Stagaire-profile']);
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


