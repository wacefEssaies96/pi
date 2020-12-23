import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new User();
  msg="";
  constructor(private _service : RegistrationService, private _router : Router) { }


  ngOnInit() {
  }
  registerUser(){
    this._router.navigate(['/login'])
    // this._service.registerUserFromRemote(this.user).subscribe(
    //   data =>{
    //     console.log("responce recieved"),
    //     this._router.navigate(['/login'])
    //   } ,
    //   error => {
    //     console.log(" exception "),
    //     this.msg=error.error;
    //     ;
    //   } , 
    // );
  }

  gotologin(){
    this._router.navigate(['/login'])
  }


}
