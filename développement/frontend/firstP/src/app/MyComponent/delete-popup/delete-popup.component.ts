import { Component, EventEmitter, Inject, Output, ViewChild } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AdminSeviceService } from 'src/app/Services/admin-sevice.service';
import { constants } from 'src/app/shared/global/constants';
import { AdminDashBordComponent } from '../MyHomeComponnet/admin-dash-bord/admin-dash-bord.component';
import { TableStagaireComponent } from '../table-stagaire/table-stagaire.component';

@Component({
  selector: 'app-delete-popup',
  templateUrl: './delete-popup.component.html',
  styleUrls: ['./delete-popup.component.css']
})
export class DeletePopupComponent {
  @Output() deletionSuccess: EventEmitter<any> = new EventEmitter();

  [x: string]: any;

  constructor(
    public dialogRef: MatDialogRef<DeletePopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private adminService: AdminSeviceService,
    private router: Router
    ) { }

  stagaire_Id: any;
  message_1: String = "";
  message_2: String = "";


  deleteStagaire() {
    //const stagaireId = ;
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
    this.adminService.deleteStagaire(Identifiant).subscribe((response: any) => {
      this.message_1 = "Stagiaire supprimé avec succès !";
      this.message_2 = response?.message;
      this.dialogRef.close();

      console.log("Normalment y3awed yrefrechii");

      // emit the deletionSuccess event
      this.deletionSuccess.emit();
      console.log("data tba3thetttt");

    }, (error) => {
      if (error.error?.message) {
        this.message_2 = error.error?.message;
        this.message_1 = "Une erreur est survenue lors de la suppression du stagiaire ";
      }
      else {
        this.message_2 = constants.genericError;
      }
    })

  }
}






/*La ligne @Inject(MAT_DIALOG_DATA) public data: any) dans un composant de dialogue (modal) Angular permet de récupérer les données qui sont transmises au composant lors de l'ouverture du dialogue.
Lorsqu'un composant de dialogue est ouvert à partir d'un autre composant, il est possible de passer des données à ce composant. Pour ce faire, on utilise la méthode open() de la classe MatDialog pour ouvrir le composant de dialogue et on y passe un objet de données qui sera utilisé par le composant de dialogue.
Pour pouvoir récupérer ces données dans le composant de dialogue, on utilise la décoration @Inject(MAT_DIALOG_DATA) pour injecter l'objet de données dans le composant de dialogue. La variable data est déclarée en tant que membre public du composant, ce qui permet de l'utiliser dans le template HTML du composant de dialogue pour afficher ou modifier les données.
En résumé, @Inject(MAT_DIALOG_DATA) public data: any) permet de récupérer les données passées au composant de dialogue lors de son ouverture, en les injectant dans le membre data du composant.

*/
