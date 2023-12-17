import { Component } from '@angular/core';
import { EncadreurSeviceService } from 'src/app/Services/encadreur-sevice.service';
import { constants } from 'src/app/shared/global/constants';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { AdminSeviceService } from 'src/app/Services/admin-sevice.service';
import { stagaire } from 'src/app/Modals/stagaire';


@Component({
  selector: 'app-encadreur-dash-bord',
  templateUrl: './encadreur-dash-bord.component.html',
  styleUrls: ['./encadreur-dash-bord.component.css']
})
export class EncadreurDashBordComponent {

  responseMessage: any;
  data: any;
  stagaire: stagaire[] = [];

  constructor(private encadreurService : EncadreurSeviceService,
    private ngxService: NgxUiLoaderService,
  ) {
    this.ngxService.start();
    this.dashbordData();
    this.getStagaire();
  }
  dashbordData() {
    this.encadreurService.getRecived().subscribe((response: any) => {
      this.ngxService.stop();
      this.data = response;
      console.log("MY DATA",this.data);
    }, (error: any) => {
      this.ngxService.stop();
      console.log(error);
      if (error.error?.message) {
        this.responseMessage = error.error?.message
      }
      else {
        this.responseMessage = constants.genericError
      }
      console.log(this.responseMessage);
    })

  }


  getStagaire() {
    this.encadreurService.getRecived().subscribe((response: any) => {
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

}
