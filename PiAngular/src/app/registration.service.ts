import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  //data = {};
  constructor( private _http : HttpClient ) { }

  // public loginUserFromRemote(user : User):boolean{
  //   this.data="{}";
  //   this.data=this._http.get<any>("http://localhost:8088/user/username");
  //   console.log(this.data);
  //   if(this.data== null){
  //     return  false;
  //   }
  //   else return  true;
  // }

  // public loginUserFromRemote(user : User):boolean{
  //   this.data={};
  //   this.data = this._http.post("http://localhost:8000/api/login",user);
  //   if(this.data != {}){
  //     console.log(" data ok");
  //   }
    

  //     if(this.data.status == 1){
  //       return true;
  //     }else{
  //       return false;
  //     }
    
  // }

  public loginUserFromRemote(user : User):Observable<any>{
    
    return this._http.post<any>("http://localhost:8000/api/login",{
      "username" : user.username,
      "password" : user.password
  });
  }

  // public registerUserFromRemote(user : User):Observable<any>{
  //   return this._http.post<any>("http://localhost:8088/save/user",user)
  // }
  
  public listproduit():Observable<any>{
    return this._http.post<any>("http://localhost:8000/api/list",{
      "username" : "ihebbenhelel@gmail.com",
      "password" : "admin"
  });
}

}
