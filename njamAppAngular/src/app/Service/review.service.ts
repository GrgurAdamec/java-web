import { Component, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Restaurant } from '../Model/restaurant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { get } from 'http';
import { RestaurantExtended } from '../Model/restaurant-extended';

@Injectable({
  providedIn: 'root',
})
export class ReviewService {
  constructor(private http: HttpClient) {}

  private restaurantsUrl = 'http://localhost:8080/reviews/all';

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      charset: 'UTF-8',
    }),
  };

  getReviews(restaurantId: number): Observable<any> {
    return this.http.get<any>(
      `${this.restaurantsUrl}/${restaurantId}`,
      this.httpOptions
    );
  }
}
