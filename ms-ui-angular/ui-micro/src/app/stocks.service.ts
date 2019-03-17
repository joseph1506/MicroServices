import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class StocksService {

  constructor(private http: HttpClient) {
  }

  retrieveQuotes(user) {
    return this.http.get('/api/stock-service/rest/stock/' + user);
  }

}
