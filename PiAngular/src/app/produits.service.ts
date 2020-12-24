import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProduitsService {

  constructor( private _http : HttpClient ) { }

  
  // public listproduit():Observable<any>{
  //   return this._http.get<any>("http://localhost:8000/api/list");
  // }

  public listproduit():Observable<any>{
    return this._http.post<any>("http://localhost:8000/api/list",{
      "username" : "ihebbenhelel@gmail.com",
      "password" : "admin"
  });

  }
}
