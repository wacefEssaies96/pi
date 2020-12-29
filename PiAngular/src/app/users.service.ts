import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UsersService {


  constructor( private _http : HttpClient ) { }

  
  
  public listuser(user : User):Observable<any>{
    return this._http.post<any>("http://localhost:8000/api/users",{
      "username" : "ihebbenhelel@gmail.com",//user.username,
      "password" : "admin",//user.password,
  });
  }

  // public listusersearch(user : User):Observable<any>{
  //   return this._http.post<any>("http://localhost:8000/api/searchuser",{
  //     "username" : "ihebbenhelel@gmail.com",//user.username,
  //     "password" : "admin",//user.password,
  // });
  //}
}
