import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, CanActivate, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../authService/auth.service';
import jwt_decode from "jwt-decode";


@Injectable({
  providedIn: 'root'
})
export class GuardService implements CanActivate {

  constructor(public auth: AuthService,
    public router: Router) { }
  canActivate(route: ActivatedRouteSnapshot): boolean {
    let expextedRoleArray = route.data;
    expextedRoleArray = expextedRoleArray['expextedRole'];

    const token: any = localStorage.getItem('token');

    var tokenPayload: any;

    try {
      tokenPayload = jwt_decode(token);
    } catch (err) {
      localStorage.clear();
      this.router.navigate(['/error-page'])
    }

    let expextedRole = '';
    for (let i = 0; i < expextedRoleArray['length']; i++) {
      if (expextedRoleArray[i] == tokenPayload.role) {
        expextedRole = tokenPayload.role;
      }
    }

    if (tokenPayload.role == "Administrateur") {
      if (this.auth.isAuthenticated() && tokenPayload.role == expextedRole) {
        return true;
      }
      console.log("Administrateur erreur");

      this.router.navigate(['/error-page']);
      return false;
    }

    else if (tokenPayload.role == "Encadreur") {
      if (this.auth.isAuthenticated() && tokenPayload.role == expextedRole) {
        return true;
      }
      console.log("Encadreur erreur");
      this.router.navigate(['/error-page']);
      return false;
    }
    else if (tokenPayload.role == "Stagaire") {
      if (this.auth.isAuthenticated() && tokenPayload.role == expextedRole) {
        return true;
      }
      console.log("Stagaire erreur");
      this.router.navigate(['/error-page']);
      return false;
    }
    else {
      this.router.navigate(['/error-page']);
      localStorage.clear();
      return false;
    }
  }




}
