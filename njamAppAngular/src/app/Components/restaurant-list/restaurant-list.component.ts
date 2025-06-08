import { Component } from '@angular/core';
import { RestaurantService } from '../../Service/restaurant.service';
import { Restaurant } from '../../Model/restaurant';
import { CommonModule } from '@angular/common';
import { RestaurantDetailsComponent } from '../restaurant-details/restaurant-details.component';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { RestaurantNewRestaurantFormComponent } from '../restaurant-new-restaurant-form/restaurant-new-restaurant-form.component';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-restaurant-list',
  standalone: true,
  imports: [
    CommonModule,
    RestaurantDetailsComponent,
    FormsModule,
    RestaurantNewRestaurantFormComponent,
    TranslateModule,
  ],
  templateUrl: './restaurant-list.component.html',
  styleUrl: './restaurant-list.component.css',
})
export class RestaurantListComponent {
  restaurantService: RestaurantService;
  restaurants: Restaurant[] = [];
  router: Router;
  displayRestaurants: Restaurant[] = [];
  selectedRestaurant: Restaurant | null = null;
  newRestaurantFormVisible: boolean = false;

  bestRatedRestaurantLastMonth: Restaurant | null = null;
  bestRatedRestaurantLastWeek: Restaurant | null = null;

  public filterText: string = '';
  sortedByLowestFirst: boolean = true;

  constructor(restaurantService: RestaurantService, router: Router) {
    this.restaurantService = restaurantService;
    this.restaurantService.getRestaurants().subscribe((data) => {
      this.restaurants = data;
    });

    this.displayRestaurants = this.restaurants;

    this.router = router;

    this.restaurantService.getBestRatedLastMonth().subscribe((data) => {
      this.bestRatedRestaurantLastMonth = data;
    });

    this.restaurantService.getBestRatedLastWeek().subscribe((data) => {
      this.bestRatedRestaurantLastWeek = data;
    });
  }

  selectRestaurant(restaurantId: number): void {
    this.router.navigate([`/restaurantdetails/${restaurantId}`]);
  }

  sortRestaurants(): void {
    if (!this.sortedByLowestFirst) {
      this.displayRestaurants.sort((a, b) => {
        if (a.workload < b.workload) return -1;
        if (a.workload > b.workload) return 1;
        return 0;
      });

      this.sortedByLowestFirst = true;
    } else {
      this.displayRestaurants.sort((a, b) => {
        if (a.workload > b.workload) return -1;
        if (a.workload < b.workload) return 1;
        return 0;
      });

      this.sortedByLowestFirst = false;
    }
  }

  filterRestaurants() {
    this.displayRestaurants = this.restaurants.filter((restaurant) =>
      restaurant.name.toLowerCase().includes(this.filterText.toLowerCase())
    );
  }

  toggleNewRestaurantForm() {
    this.newRestaurantFormVisible = !this.newRestaurantFormVisible;
  }

  ngOnInit(): void {}
}
