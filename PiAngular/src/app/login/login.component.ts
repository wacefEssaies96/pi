import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistrationService } from '../registration.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = new User();
  msg="";
  constructor(private _service : RegistrationService, private _router : Router) { }

  ngOnInit() {
  }

  loginUser(){
    // if (this._service.loginUserFromRemote(this.user)){
    //   console.log("responce recieved")//,
    //   //this._router.navigate(['/loginsuccess'])
    // }
    // else{
    //   console.log(" exception "+this.user.username),
    //   this.msg=" Please enter valid values ";
    // }
    //users=this._service.loginUserFromRemote(this.user);

    this._service.loginUserFromRemote(this.user).subscribe(
      data =>{
        console.log("responce recieved",data)//,
        //this._router.navigate(['/loginsuccess'])
      } ,
      error => {
        console.log(" exception "+this.user.username+" pwd "+this.user.password);
        this.msg=" Please enter valid values ";
      } , 
    );
  }

  gotoregistration(){
    this._router.navigate(['/registration'])
  }


}
