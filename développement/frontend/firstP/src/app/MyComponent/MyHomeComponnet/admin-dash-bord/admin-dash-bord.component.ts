import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { stagaire } from 'src/app/Modals/stagaire';
import { AdminSeviceService } from 'src/app/Services/admin-sevice.service';
import { constants } from 'src/app/shared/global/constants';
import { DeletePopupComponent } from '../../delete-popup/delete-popup.component';
import { TableStagaireComponent } from '../../table-stagaire/table-stagaire.component';


@Component({
  selector: 'app-admin-dash-bord',
  templateUrl: './admin-dash-bord.component.html',
  styleUrls: ['./admin-dash-bord.component.css']
})
export class AdminDashBordComponent implements OnInit {
@ViewChild(DeletePopupComponent) deletePopupComponent: DeletePopupComponent | undefined;


  responseMessage_1: any;
  data: any;
  Number: any;
  Accepted: any;
  refused: any;
  stagaire: stagaire[] = [];

  constructor(private adminservice: AdminSeviceService,
    private ngxService: NgxUiLoaderService,
    private router: Router,
  ) {
   // this.deletionSuccessHandler();

  }
  ngOnInit(): void {


  }






 /* deletionSuccessHandler() {
    // listen to the successful deletion event
    this.deletePopupComponent?.deletionSuccess.subscribe(() => {
      // refresh the data
      this.updateData();
    });
  }
  updateData() {
    this.dashbordDemandeData();
    this.dashbordData();
    console.log('Update successful');
    console.log(this.data);
    console.log(this.Number);
    console.log(this.Accepted);
    console.log(this.refused);
  }*/

}
