import { Component, EventEmitter, Inject, Output } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AdminSeviceService } from 'src/app/Services/admin-sevice.service';
import { constants } from 'src/app/shared/global/constants';

@Component({
  selector: 'app-update-modal',
  templateUrl: './update-modal.component.html',
  styleUrls: ['./update-modal.component.css']
})
export class UpdateModalComponent {

  @Output() deletionSuccess: EventEmitter<any> = new EventEmitter();

  [x: string]: any;

  constructor(
    public dialogRef: MatDialogRef<UpdateModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminSeviceService,
    private router: Router
    ) { }

  stagaire_Id: any;
  message_1: String = "";
  message_2: String = "";


  sendToEncadreur() {
    //const stagaireId = ;
    console.log("inside sendToEncadreur")
    console.log(this.data);
    console.log("Type Data", typeof (this.data));
    console.log(this.data.chaine);
    console.log("type 1 ", typeof (this.data.chaine));
    console.log(this.data.StagiaireId);
    console.log("type 2 ", typeof (this.data.StagiaireId));
    this.stagaire_Id = this.data.StagiaireId;
    console.log("Stagaire ID", this.stagaire_Id);
    const Identifiant = this.stagaire_Id;
    console.log("Identifiant", Identifiant);
    this.adminService.updateByAdmin(Identifiant).subscribe((response: any) => {
      this.message_1 = "Demande envoyer  !";
      this.message_2 = response?.message;
      console.log("Message_2:", this.message_2);

      this.dialogRef.close();

      console.log("Normalment y3awed yrefrechii");

      // emit the deletionSuccess event
      this.deletionSuccess.emit();
      console.log("data tba3thetttt");

    }, (error) => {
      if (error.error?.message) {
        this.message_2 = error.error?.message;
        this.message_1 = "Une erreur est survenue lors de l'envoie de la demande du stagiaire ";
      }
      else {
        this.message_2 = constants.genericError;
      }
    })

  }
}
