import { NgModule, NO_ERRORS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './MyComponent/MyHomeComponnet/home/home.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BodyComponent } from './MyComponent/Body/body/body.component';
import { LoginModalComponent } from './MyComponent/LoginF/login-modal/login-modal.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { HeadersComponent } from './MyComponent/MyHomeComponnet/headers/headers/headers.component';
import { NgxUiLoaderConfig, NgxUiLoaderModule, SPINNER } from 'ngx-ui-loader';
import { SingUpStagaireComponent } from './MyComponent/signup/sing-up-stagaire/sing-up-stagaire.component';
import { ReactiveFormsModule } from '@angular/forms';
import { StagaireProfileComponent } from './MyComponent/stagaire-profile/stagaire-profile.component';
import { LoginCComponent } from './MyComponent/LoginF/login-c/login-c.component';
import { EncadreurDashBordComponent } from './MyComponent/MyHomeComponnet/encadreur-dash-bord/encadreur-dash-bord.component';
import { LoginAdminComponent } from './MyComponent/LoginF/login-admin/login-admin.component';
import { AdminDashBordComponent } from './MyComponent/MyHomeComponnet/admin-dash-bord/admin-dash-bord.component';
import { ErreurPageComponent } from './erreur-page/erreur-page.component';
import { JwtHelperService, JWT_OPTIONS } from '@auth0/angular-jwt';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import jwt_decode from "jwt-decode";
import { TokenInterceptorInterceptor } from './Auth/token-interceptor.interceptor';
import { TableStagaireComponent } from './MyComponent/table-stagaire/table-stagaire.component';
import { DeletePopupComponent } from './MyComponent/delete-popup/delete-popup.component';
import { UpdateModalComponent } from './MyComponent/update-modal/update-modal.component';

const ngxUiLoaderConfig: NgxUiLoaderConfig = {
  text:"Chargement...",
  textColor:"#FFFFFF",
  textPosition:"center-center",
  bgsColor:"##1F69A2",
  fgsColor:"##1F69A2",
  fgsType:SPINNER.circle,
  fgsSize:100,
  hasProgressBar:false
}

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BodyComponent,
    LoginModalComponent,
    HeadersComponent,
    SingUpStagaireComponent,
    StagaireProfileComponent,
    LoginCComponent,
    EncadreurDashBordComponent,
    LoginAdminComponent,
    AdminDashBordComponent,
    ErreurPageComponent,
    TableStagaireComponent,
    DeletePopupComponent,
    UpdateModalComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatSnackBarModule,
    MatDialogModule,
    NgxUiLoaderModule.forRoot(ngxUiLoaderConfig),
    ReactiveFormsModule

  ],
  providers: [HttpClientModule, {provide: HTTP_INTERCEPTORS, useClass:TokenInterceptorInterceptor,multi:true}],
  bootstrap: [AppComponent],
  schemas: [NO_ERRORS_SCHEMA],
})
export class AppModule { }
