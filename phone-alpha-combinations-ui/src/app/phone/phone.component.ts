import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Component({
  selector: 'app-phone',
  templateUrl: './phone.component.html',
  styleUrls: ['./phone.component.css']
})
export class PhoneComponent implements OnInit {

  result: any;
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
  }

  find(phoneNumber){
    this.http.get('http://localhost:8200/finra/'+phoneNumber.value)
    .subscribe((result) => {
      this.result = result;
      console.log(this.result);
    })
  }

}
