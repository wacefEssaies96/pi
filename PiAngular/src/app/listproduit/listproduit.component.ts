import { Component, OnInit } from '@angular/core';
import { produits } from '../produits';

import { ProduitsService } from '../produits.service';
import { Router } from '@angular/router';
import { User } from '../user';

@Component({
  selector: 'app-listproduit',
  templateUrl: './listproduit.component.html',
  styleUrls: ['./listproduit.component.css']
})
export class ListproduitComponent implements OnInit  {


  user = new User();
  user1 = new User();
  produits = {};

  
  
  //constructor() {}
  constructor(private _service : ProduitsService, private _router : Router) { }
  
  ngOnInit() {
    this._service.listproduit(this.user1).subscribe(
      data =>{
        console.log("list ok",data),
        this.produits=data,
        this._router.navigate(['/listproduits'])
      } ,
      error => {
        console.log(" exception list problem ");
      } , 
    );
  }
  
  list(){
    this._service.listproduitsearch(this.user).subscribe(
      data =>{
        console.log("list ok",data),
        this.produits=data,
        this._router.navigate(['/listproduits'])
      } ,
      error => {
        console.log(" exception list problem ");
      } , 
    );

  }
  


}
