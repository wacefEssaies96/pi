import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { User } from './user';
  
  @Injectable({
    providedIn: 'root'
  })
  export class ProduitsService {
  
    constructor( private _http : HttpClient ) { }
  
    
    // public listproduit():Observable<any>{
    //   return this._http.get<any>("http://localhost:8000/api/list");
    // }
    
    public listproduit(user : User):Observable<any>{
      return this._http.post<any>("http://localhost:8000/api/list",{
        "username" : "ihebbenhelel@gmail.com",//user.username,
        "password" : "admin",//user.password,
    });
    }
  
    public listproduitsearch(user : User):Observable<any>{
      return this._http.post<any>("http://localhost:8000/api/product",{
        "username" : "ihebbenhelel@gmail.com",//user.username,
        "password" : "admin",//user.password,
        "filter" : user.filtre
    });
  
  
    }
  }
  
