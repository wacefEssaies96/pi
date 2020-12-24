import { Component, OnInit } from '@angular/core';
import { produits } from '../produits';

import { ProduitsService } from '../produits.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listproduit',
  templateUrl: './listproduit.component.html',
  styleUrls: ['./listproduit.component.css']
})
export class ListproduitComponent implements OnInit {


  produits = produits;
  
  constructor() {}
  // constructor() {private  _service : ProduitsService, private _router : Router }
  
  ngOnInit() {
  }
  
  // list(){

  //   this._service.listproduit().subscribe(
  //     data =>{
  //       console.log("list ok",data)//,
  //       this._router.navigate(['/listproduits'])
  //     } ,
  //     error => {
  //       console.log(" exception list problem ");
  //     } , 
  //   );

  // }
  


}
