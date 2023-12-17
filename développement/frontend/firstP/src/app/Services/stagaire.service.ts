import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../Environements/environement';

@Injectable({
  providedIn: 'root'
})
export class StagaireService {
  url = environement.apiUrl;

  constructor(private httpClient : HttpClient) { }

  signUp(data : any):Observable<any>{
    return this.httpClient.post(this.url+
      "/stagaire/signUp",data,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }

  login(data  : any):Observable<any>{
    return this.httpClient.post(this.url+
      "/stagaire/login",data,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }

  checkToken():Observable<any>{
    return this.httpClient.get(this.url+"/stagaire/checkToken");
  }

 /* gettagiaireByEmail(email: string): Observable<any> {
    return this.httpClient.get(this.url+"/stagiaire/detail/"+email,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }*/

  getStagiaireByEmail(email: string): Observable<any> {
    return this.httpClient.get(this.url+"/stagaire/detail/"+email,{
      headers: new HttpHeaders().set('Content-Type', 'application/json'),});
  }

  deleteStagaire(id: number): Observable<any> {
    return this.httpClient.post(this.url+"/admin/deleteStagaire/"+id,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')});

  }


}
