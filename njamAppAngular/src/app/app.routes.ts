import { Routes } from '@angular/router';
import { RestaurantDetailsComponent } from './Components/restaurant-details/restaurant-details.component';
import { RestaurantListComponent } from './Components/restaurant-list/restaurant-list.component';
import { RestaurantEditRestaurantFormComponent } from './Components/restaurant-edit-restaurant-form/restaurant-edit-restaurant-form.component';

export const routes: Routes = [
  { path: '', component: RestaurantListComponent },
  { path: 'restaurantdetails/:id', component: RestaurantDetailsComponent },
  { path: 'edit/:id', component: RestaurantEditRestaurantFormComponent },
];
