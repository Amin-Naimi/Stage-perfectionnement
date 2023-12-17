import { Component, EventEmitter, Output } from '@angular/core';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { stagaire } from 'src/app/Modals/stagaire';
import { AdminSeviceService } from 'src/app/Services/admin-sevice.service';
import { constants } from 'src/app/shared/global/constants';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NgForOf } from '@angular/common';
import { LoginModalComponent } from '../LoginF/login-modal/login-modal.component';
import { DeletePopupComponent } from '../delete-popup/delete-popup.component';
import { Router } from '@angular/router';
import { UpdateModalComponent } from '../update-modal/update-modal.component';



@Component({
  selector: 'app-table-stagaire',
  templateUrl: './table-stagaire.component.html',
  styleUrls: ['./table-stagaire.component.css',]
})
export class TableStagaireComponent {

  //@Output() demandeSupprimee = new EventEmitter();

  stagaire: stagaire[] = [];
  responseMessage: any;
  dataSource:any;
  test:string="mina"
  identifiantStagaire:any;
  responseMessage_1: any;
  data: any;
  Number: any;
  Accepted: any;
  refused: any;

  constructor(private adminservice: AdminSeviceService,
    private ngxService: NgxUiLoaderService,
    private dialog: MatDialog,
    private router: Router
  ){ }
  ngOnInit(): void {
    this.ngxService.start();
    this.dashbordData();
    this.dashbordDemandeData();
    this.getStagaire();
  }

  getStagaire() {
    this.adminservice.getAllStagaire().subscribe((response: any) => {
      this.stagaire = response;
      console.log("tableau", this.stagaire);
      console.log("taille", this.stagaire.length);
      console.log("le type du tableau:", typeof(this.stagaire));
      for(let i =0; i < this.stagaire.length; i++){
       console.log("Identifiant", this.stagaire[i].stagaireId);
      }
    }, (error: any) => {
      console.log(error.error.message);
      if (error.error?.message) {
        this.responseMessage = error.error?.message
      }
      else {
        this.responseMessage = constants.genericError
      }
      console.log(this.responseMessage);
    })
  }

  handelDeleteAction(stagaireId : number) {
    if (!(this.dialog.openDialogs.length > 0)) {
      const screenWidth = window.innerWidth;
      const dialogConfig = new MatDialogConfig();
      dialogConfig.width = "400px";
      dialogConfig.height = "570";
      if (screenWidth >= 768) {
        dialogConfig.position = {
          'top': '-42%',
          'left': '45%',
        };
      }else{
        dialogConfig.position = {
          'top': '-42%',
          'left': '10%',
        };
      }
      dialogConfig.data = { StagiaireId: stagaireId,
        chaine: "valeur du test = ok"};
      console.log("dialogConfig stagaire" ,dialogConfig.data?.StagiaireId);
      console.log("type", typeof(dialogConfig.data?.StagiaireId));
      const dialogRef = this.dialog.open(DeletePopupComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.getStagaire();
        this.dashbordData();
        this.dashbordDemandeData();
        //this.demandeSupprimee.emit();
      });

    }

  }

  dashbordData() {
    this.adminservice.getDetails().subscribe((response: any) => {
      this.ngxService.stop();
      this.data = response;
      console.log("Data :", this.data);
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage_1 = error.error?.message
      }
      else {
        this.responseMessage_1 = constants.genericError
      }
      console.log(this.responseMessage_1);
    })

    this.adminservice.getAccepted().subscribe((response: any) => {
      this.Accepted = response;
      console.log("Nombre des Demande accepter:", this.Accepted);
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage_1 = error.error?.message
      }
      else {
        this.responseMessage_1 = constants.genericError
      }
      console.log(this.responseMessage_1);
    })

    this.adminservice.getRefused().subscribe((response: any) => {
      this.refused = response;
      console.log("Nombre des demndes refuser:", this.refused);
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage_1 = error.error?.message
      }
      else {
        this.responseMessage_1 = constants.genericError
      }
      console.log(this.responseMessage_1);
    })

  }

  logOut() {
    this.ngxService.start();
    localStorage.clear();
    this.router.navigate(['/home']);
    this.ngxService.stop();
  }

  dashbordDemandeData() {
    this.adminservice.getNumber().subscribe((response: any) => {
      this.Number = response;
      console.log("Nombre des Demande réçu:", this.Number);
    }, (error: any) => {
      console.log(error);
      if (error.error?.message) {
        this.responseMessage_1 = error.error?.message
      }
      else {
        this.responseMessage_1 = constants.genericError
      }
      console.log(this.responseMessage_1);
    })
  }

  handelSendAction(stagaireId : number) {
    if (!(this.dialog.openDialogs.length > 0)) {
      const screenWidth = window.innerWidth;
      const dialogConfig = new MatDialogConfig();
      dialogConfig.width = "400px";
      dialogConfig.height = "570";
      if (screenWidth >= 768) {
        dialogConfig.position = {
          'top': '-42%',
          'left': '45%',
        };
      }else{
        dialogConfig.position = {
          'top': '-42%',
          'left': '10%',
        };
      }
      dialogConfig.data = { StagiaireId: stagaireId,
        chaine: "valeur du test = ok"};
      console.log("dialogConfig stagaire" ,dialogConfig.data?.StagiaireId);
      console.log("type", typeof(dialogConfig.data?.StagiaireId));
      const dialogRef = this.dialog.open(UpdateModalComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.getStagaire();
        this.dashbordData();
        this.dashbordDemandeData();
        //this.demandeSupprimee.emit();
      });

    }

  }



}
