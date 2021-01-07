import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-loginsuccess',
  templateUrl: './loginsuccess.component.html',
  styleUrls: ['./loginsuccess.component.css']
})
export class LoginsuccessComponent implements OnInit {

  title = 'My first AGM project';
  lat = 37.235995;
  lng = 9.8849556;
  zoom = 15;
  constructor() { }

  ngOnInit() {
  }

}
