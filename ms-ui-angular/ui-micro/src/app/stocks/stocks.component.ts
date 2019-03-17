import {Component, OnInit} from '@angular/core';
import {StocksService} from '../stocks.service';

@Component({
  selector: 'app-stocks',
  templateUrl: './stocks.component.html',
  styleUrls: ['./stocks.component.css']
})
export class StocksComponent implements OnInit {
  user = null;
  quote = null;

  constructor(private stockService: StocksService) {
  }

  ngOnInit() {
  }

  addQuote() {
    console.log('User and Quote--->', this.user, this.quote);
  }

  retrieveQuotes() {
    console.log('User and Quote--->', this.user);
    this.stockService.retrieveQuotes(this.user).subscribe(result => {
      console.log(result);
    });

  }

}
