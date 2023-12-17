import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { GuardService } from './Auth/guardService/guard.service';
import { ErreurPageComponent } from './erreur-page/erreur-page.component';
import { AdminDashBordComponent } from './MyComponent/MyHomeComponnet/admin-dash-bord/admin-dash-bord.component';
import { EncadreurDashBordComponent } from './MyComponent/MyHomeComponnet/encadreur-dash-bord/encadreur-dash-bord.component';
import { HomeComponent } from './MyComponent/MyHomeComponnet/home/home.component';
import { SingUpStagaireComponent } from './MyComponent/signup/sing-up-stagaire/sing-up-stagaire.component';
import { StagaireProfileComponent } from './MyComponent/stagaire-profile/stagaire-profile.component';
//import { RouteGuardService } from './Services/route-guard.service';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'Stagaire-Registre', component: SingUpStagaireComponent },

  {
    path: 'Stagaire-profile',
     component: StagaireProfileComponent,
     canActivate:[GuardService],
     data:{
      expextedRole:['Stagaire']
     }

  },
  {
    path: 'Encadreur-Dashboard', 
    component: EncadreurDashBordComponent,
    canActivate:[GuardService],
     data:{
      expextedRole:['Encadreur']
     }
  },
  {
    path: 'Admin-Dashboard',
     component: AdminDashBordComponent,
     canActivate:[GuardService],
     data:{
      expextedRole:['Administrateur']
     }
  },
  { path: 'error-page', component: ErreurPageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
