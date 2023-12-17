import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { AdminSeviceService } from 'src/app/Services/admin-sevice.service';
import { constants } from 'src/app/shared/global/constants';
import jwt_decode from "jwt-decode";

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent {
  responseMessage: any;
  showResponseMessage = false;

  constructor(private form: FormBuilder,
    private ngxService: NgxUiLoaderService,
    private router: Router,
    private AdminService: AdminSeviceService,
    public dialogRef: MatDialogRef<LoginAdminComponent>) { }

  /*  ngOnInit() {
      this.AdminService.checkToken().subscribe((response:any)=>
      console.log("ngOnINT",response));
    }*/

  loginAdminForm = this.form.group({
    email: ['', Validators.required],
    password: ['', Validators.required],
  })

  showAlert(message: string) {
    this.showResponseMessage = true;
    this.responseMessage = message;
    setTimeout(() => {
      this.showResponseMessage = false;
    }, 18000);
  }

  onSubmitAdmin() {
    console.warn(this.loginAdminForm.value)
    this.ngxService.start();
    var formData = this.loginAdminForm.value;
    var data = {
      email: formData.email,
      password: formData.password,
    }; console.log(data);
    this.AdminService.loginAdmin(data).subscribe((response: any) => {
      this.ngxService.stop();
      this.dialogRef.close();
      //Besoin d'utiliser le token pour consomer les autres API
      //Donc il faut enregister le tokenzy

      localStorage.setItem('token', response.Token);
      console.log("Le jeton :",response.Token);


      const tOKen = response.Token;
      const decodedToken = jwt_decode(tOKen);
      console.log("le décodage du jeton :",decodedToken);


      this.router.navigate(['Admin-Dashboard']);
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


