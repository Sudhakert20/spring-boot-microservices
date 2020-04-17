import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  
  data: any;
  constructor(private http:HttpClient) { }

  ngOnInit() {
   
  }

  retrieve(userName){
    this.http.get('http://127.0.0.1:8300/api/stock-service/stock/'+ userName.value + '/details')
    .subscribe((data) => 
    {
      this.data=data;
      console.log(this.data);
    })
  }

}
