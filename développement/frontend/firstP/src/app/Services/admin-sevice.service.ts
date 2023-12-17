import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environement } from '../Environements/environement';
import { stagaire } from '../Modals/stagaire';
import { EventEmitter } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class AdminSeviceService {
  url = environement.apiUrl;
  constructor(private httpClient : HttpClient) { }
  deleteRequestEvent = new EventEmitter<any>();

  loginAdmin(data : any):Observable<any>{
    return this.httpClient.post(this.url+
      "/admin/login",data,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')
    })
  }

  checkToken():Observable<any>{
    return this.httpClient.get(this.url+"/admin/checkToken");
  }

  getDetails():Observable<any>{
    return this.httpClient.get(this.url+"/dashbord/details");
  }
  getNumber():Observable<any>{
    return this.httpClient.get(this.url+"/dashbord/number");
  }

  getAccepted():Observable<any>{
    return this.httpClient.get(this.url+"/dashbord/accepted");
  }

  getRefused():Observable<any>{
    return this.httpClient.get(this.url+"/dashbord/refused");
  }

  getAllStagaire(): Observable<any> {
    return this.httpClient.get(`${this.url}/admin/get`);
  }

  updateByAdmin(id: number): Observable<any> {
    return this.httpClient.post(this.url +"/admin/updateByadmin/"+id,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }


  deleteStagaire(id: number): Observable<any> {
    return this.httpClient.post(this.url+"/admin/deleteStagaire/"+id,{
      headers: new HttpHeaders().set('Content-Type', 'application/json')});

  }










}
