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
  produits = {};
  lengthproduits=0;
  findproduits=0;

  
  
  //constructor() {}
  constructor(private _service : ProduitsService, private _router : Router) { }
  
  ngOnInit() {
    this.produits = {};
    this.lengthproduits=0;
    this.findproduits=0;
    this._service.listproduit(this.user).subscribe(
      
      data =>{
        console.log("list ok",data),
        this.produits=data,
        this.lengthproduits=data.length,
        this.findproduits=1,
        this._router.navigate(['/listproduits'])
      } ,
      error => {
        this.produits = {},
        this.lengthproduits=0,
        console.log(" exception list problem ")
      } , 
    );
    
  }
  
  list(){
    this.produits = {};
    this.lengthproduits=0;
    this.findproduits=0;
    this._service.listproduitsearch(this.user).subscribe(
      data =>{
        console.log("list ok",data),
        this.produits=data,
        this.lengthproduits=data.length,
        this.findproduits=1,
        this._router.navigate(['/listproduits'])
      } ,
      error => {
        this.produits = {},
        this.lengthproduits=0,
        this.findproduits=1,
        console.log(" exception list problem ")
      } , 
    );

  }
  


}
