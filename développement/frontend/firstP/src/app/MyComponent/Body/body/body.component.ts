import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { StagaireService } from 'src/app/Services/stagaire.service';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { LoginCComponent } from '../../LoginF/login-c/login-c.component';
import { LoginAdminComponent } from '../../LoginF/login-admin/login-admin.component';

//import { LoginModalComponent } from './login-modal/login-modal.component';


@Component({
  selector: 'app-body',
  templateUrl: './body.component.html',
  styleUrls: ['./body.component.css']
})

export class BodyComponent {

  constructor(private form: FormBuilder,
    private stagaireService: StagaireService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog
    ){}


    handleLoginAction(userType: String) {
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
        if (userType === 'Encadreur'){
      this.dialog.open(LoginCComponent, dialogConfig);
        }else if (userType === 'admin') {
          this.dialog.open(LoginAdminComponent, dialogConfig);
        }
  
      }
    }

  

}
