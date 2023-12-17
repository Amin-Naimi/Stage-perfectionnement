import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environement } from '../Environements/environement';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EncadreurSeviceService {

  url = environement.apiUrl;
  constructor(private httpClient : HttpClient) { }

  loginEncadreur(data : any):Observable<any>{
    return this.httpClient.post(this.url+
      "/encadreur/login",data,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }


  checkToken():Observable<any>{
    return this.httpClient.get(this.url+"/encadreur/checkToken");
  }

  getNumber():Observable<any>{
    return this.httpClient.get(this.url+"/dashbord/number");
  }

  getRecived():Observable<any>{
    return this.httpClient.get(this.url+"/dashbord/recived");
  }

  getRecivedStagaire(): Observable<any> {
    return this.httpClient.get(`${this.url}/encadreur/get`);
  }
}
