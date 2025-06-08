//
//
//
//
// Ovo nes ne radi, rute su u app.routes.ts

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { RestaurantListComponent } from '../Components/restaurant-list/restaurant-list.component';
import { RestaurantDetailsComponent } from '../Components/restaurant-details/restaurant-details.component';

const routes: Routes = [
  { path: '', component: RestaurantListComponent },
  { path: 'restaurants', component: RestaurantDetailsComponent },
];

@NgModule({
  declarations: [],
  imports: [CommonModule, RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
