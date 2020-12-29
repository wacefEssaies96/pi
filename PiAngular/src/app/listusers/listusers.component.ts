import { Component, OnInit } from '@angular/core';

import { UsersService } from '../users.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-listusers',
  templateUrl: './listusers.component.html',
  styleUrls: ['./listusers.component.css']
})
export class ListusersComponent implements OnInit {

  user = new User();
  users = {};
  
  constructor(private _service : UsersService, private _router : Router) { }

  ngOnInit() {
    this._service.listuser(this.user).subscribe(
      data =>{
        console.log("list ok",data),
        this.users=data,
        this._router.navigate(['/listusers'])
      } ,
      error => {
        console.log(" exception list problem ");
      } , 
    );
  }
  
  list(){
    this._service.listuser(this.user).subscribe(
      data =>{
        console.log("list ok",data),
        this.users=data,
        this._router.navigate(['/listusers'])
      } ,
      error => {
        console.log(" exception list problem ");
      } , 
    );

  }

}
