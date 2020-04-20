import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-phone',
  templateUrl: './phone.component.html',
  styleUrls: ['./phone.component.css']
})
export class PhoneComponent implements OnInit {

  result: any;
  phoneNumber: any
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  find(phoneNumber){
    this.phoneNumber=phoneNumber.value;
    this.http.get('http://localhost:8200/finra/'+this.phoneNumber)
    .subscribe((data) => {
      this.result = data;
      console.log(this.result);
    })
  }

  findByPage(pageNumber){
    this.http.get('http://localhost:8200/finra/'+this.phoneNumber+'?page='+pageNumber.value)
    .subscribe((data) => {
      this.result = data;
      console.log(this.result);
    })
  }

}
