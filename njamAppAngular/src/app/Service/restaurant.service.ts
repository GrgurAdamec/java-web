import { Component, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Restaurant } from '../Model/restaurant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { get } from 'http';
import { RestaurantExtended } from '../Model/restaurant-extended';

@Injectable({
  providedIn: 'root',
})
export class RestaurantService {
  // private restaurants: Restaurant[] = [
  //   {
  //     id: 1,
  //     name: 'Mac and Van Der Poel',
  //     address: '123 Main St',
  //     currentlyOpen: true,
  //     workload: 0.5,
  //   },
  //   {
  //     id: 2,
  //     name: 'Wout and Co.',
  //     address: '456 Elm St',
  //     currentlyOpen: false,
  //     workload: 0.8,
  //   },
  //   {
  //     id: 3,
  //     name: 'Tadej Pogača',
  //     address: '789 Oak St',
  //     currentlyOpen: true,
  //     workload: 0.3,
  //   },
  // ];

  // constructor() {}

  // getRestaurants(): Observable<Restaurant[]> {
  //   return new Observable((observer) => {
  //     observer.next(this.restaurants);
  //     observer.complete();
  //   });
  // }

  constructor(private http: HttpClient) {}

  private restaurantsUrl = 'http://localhost:8080/restaurants';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      charset: 'UTF-8',
    }),
  };

  getRestaurants(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(
      this.restaurantsUrl + '/all',
      this.httpOptions
    );
  }

  getRestaurantForEdit(id: number): Observable<RestaurantExtended> {
    return this.http.get<RestaurantExtended>(
      this.restaurantsUrl + '/getRestaurantForEdit/' + id,
      this.httpOptions
    );
  }

  getBestRatedLastMonth(): Observable<Restaurant> {
    return this.http.get<Restaurant>(
      this.restaurantsUrl + '/bestRated/lastMonth',
      this.httpOptions
    );
  }

  getBestRatedLastWeek(): Observable<Restaurant> {
    return this.http.get<Restaurant>(
      this.restaurantsUrl + '/bestRated/lastWeek',
      this.httpOptions
    );
  }

  updateRestaurant(
    id: number,
    restaurant: RestaurantExtended
  ): Observable<RestaurantExtended> {
    return this.http.put<RestaurantExtended>(
      this.restaurantsUrl + '/update/' + id,
      restaurant,
      this.httpOptions
    );
  }

  addRestaurant(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(
      this.restaurantsUrl + '/createNew',
      restaurant,
      this.httpOptions
    );
  }

  deleteRestaurant(id: number): Observable<Restaurant> {
    return this.http.delete<Restaurant>(
      this.restaurantsUrl + '/delete/' + id,
      this.httpOptions
    );
  }
}
