import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class RestConnectionService {

  constructor(private http: HttpClient) {
  }

  getDataForLeagues(leagueCode: string) {
    // return this.http.get();
  }

}
