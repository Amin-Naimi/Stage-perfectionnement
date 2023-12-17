import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { StagaireService } from 'src/app/Services/stagaire.service';
import { constants } from 'src/app/shared/global/constants';
import { LoginModalComponent } from '../../LoginF/login-modal/login-modal.component';

@Component({
  selector: 'app-sing-up-stagaire',
  templateUrl: './sing-up-stagaire.component.html',
  styleUrls: ['./sing-up-stagaire.component.css']
})
export class SingUpStagaireComponent {


  Testalert:String ="responseMessage";
  //alertType :boolean =false;
  constructor(private form: FormBuilder,
    private stagaireService: StagaireService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private router: Router
    ){}



  showResponseMessage = false;
  responseMessage = '';
  testDiv ="";
  
  showAlert(message:string) {
    this.showResponseMessage = true;
    this.responseMessage = message;
    setTimeout(() => {
      this.showResponseMessage = false;
    }, 18000);
  }

  stagaireSignupForm = this.form.group({
    stagaireFirstName:['',Validators.required],
    stagaireLastName:['',Validators.required],
    stagaireCIN:['',Validators.required],
    email:['',Validators.required],
    password:['',Validators.required],
    university:['',Validators.required],
    specialite:['',Validators.required],
    phoneNumber:['',Validators.required],
    date_Debut_Stage:['',Validators.required],
    date_Fin_Stage:['',Validators.required],
    typeStage:['',Validators.required],

  })
 
  onSubmit(){
   console.warn(this.stagaireSignupForm.value)
   this.ngxService.start();
   const formData = this.stagaireSignupForm.value;
   var data = {
    stagaireFirstName:formData.stagaireFirstName,
    stagaireLastName:formData.stagaireLastName,
    stagaireCIN:formData.stagaireCIN,
    email:formData.email,
    password:formData.password,
    university:formData.university,
    specialite:formData.specialite,
    phoneNumber:formData.phoneNumber,
    date_Debut_Stage:formData.date_Debut_Stage,
    date_Fin_Stage:formData.date_Fin_Stage,
    typeStage:formData.typeStage,
   };console.log(data);
   
    this.stagaireService.signUp(data).subscribe((response:any)=>{
    this.ngxService.stop();
    this.responseMessage = response?.message;
    console.log(this.responseMessage);
    this.testDiv="SUCCESS";
    this.showAlert(this.responseMessage);
  
  },(error)=>{
     this.ngxService.stop();
     if(error.error?.message){
       this.responseMessage = error.error?.message;
       this.testDiv="ECHECC";
       console.log(this.responseMessage);
     }
     else{
       this.responseMessage = constants.genericError;
       console.log(this.responseMessage);
       this.testDiv="ECHECC";
     }//Affichage du messafge d'erreure
     this.showAlert(this.responseMessage);
     })
  }


  handleLoginAction() {
    if (!(this.dialog.openDialogs.length > 0)) {
      const screenWidth = window.innerWidth;
      const dialogConfig = new MatDialogConfig();
      dialogConfig.width = "400px";
      dialogConfig.height = "570";
      if (screenWidth >= 768) {
        dialogConfig.position = {
          'top': '-42%',
          'left': '38%',
        };
      }else{
        dialogConfig.position = {
          'top': '-42%',
          'left': '10%',
        };
      }
    this.dialog.open(LoginModalComponent, dialogConfig);

    }
  }




 


}
