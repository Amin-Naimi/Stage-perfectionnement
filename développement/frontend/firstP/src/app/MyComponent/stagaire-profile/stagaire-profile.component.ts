import { Component, OnInit } from '@angular/core';
import * as jwtDecode from 'jwt-decode';
import { JwtHelperService } from '@auth0/angular-jwt';
import { stagaire } from 'src/app/Modals/stagaire';
import { StagaireService } from 'src/app/Services/stagaire.service';
import { constants } from 'src/app/shared/global/constants';
import { NgxUiLoaderService } from 'ngx-ui-loader';
import { Router } from '@angular/router';



@Component({
  selector: 'app-stagaire-profile',
  templateUrl: './stagaire-profile.component.html',
  styleUrls: ['./stagaire-profile.component.css']
})
export class StagaireProfileComponent implements OnInit  {
  private jwtHelper: JwtHelperService = new JwtHelperService();
  email : string ="";
  etat: string="";
  NumTl:number=0;
  spec: string = "";
  uni: string = "";
  Nom: string = "";
  prenom: string = "";

  stagaire: stagaire[] = [];
  responseMessage: any;

  constructor(
    private stagaireService: StagaireService,
    private ngxService: NgxUiLoaderService,
    private router: Router,
  ) {}
  ngOnInit(): void {
    this.getDecodedToken();
    this.getStagaire(this.email);
  }



  public getDecodedToken() {
    const token = localStorage.getItem('token');
    console.log("Mon jeton", token);
    if (token) {
      const token_decode =  this.jwtHelper.decodeToken(token);
      console.log("token_decode",token_decode);
       this.email = token_decode.sub;
    }
  }
  getStagaire(em :string) {
    em = this.email
    console.log("emmmm : ",em);
    this.stagaireService.getStagiaireByEmail(em).subscribe((response: any) => {
        console.log("response",response);
        this.etat = response.etat;
      console.log("etat", response.etat);
     this.NumTl = response.phoneNumber;
    this.spec =response.specialite;
    this.uni =response.university;
    this.prenom = response.stagaireLastName;
    this.Nom = response.Nom;

      //console.log("taille", this.stagaire.length);
      //console.log("le type du tableau:", typeof(this.stagaire));
      //for(let i =0; i < this.stagaire.length; i++){
       //console.log("Identifiant", this.stagaire[i].stagaireId);
      //}
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

  logOut() {
    this.ngxService.start();
    localStorage.clear();
    this.router.navigate(['/home']);
    this.ngxService.stop();
  }

}
