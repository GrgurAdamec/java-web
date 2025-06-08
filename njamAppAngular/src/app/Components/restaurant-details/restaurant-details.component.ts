import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restaurant } from '../../Model/restaurant';
import { RestaurantService } from '../../Service/restaurant.service';
import { ReviewService } from '../../Service/review.service';
import { Review } from '../../Model/review';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-restaurant-details',
  standalone: true,
  imports: [CommonModule, TranslateModule],
  templateUrl: './restaurant-details.component.html',
  styleUrl: './restaurant-details.component.css',
})
export class RestaurantDetailsComponent {
  @Input() restaurant: any;
  id: number;
  restaurantService: RestaurantService;
  reviewService: ReviewService;
  reviews: Review[] = [];
  router: Router;

  constructor(
    private route: ActivatedRoute,
    restaurantService: RestaurantService,
    reviewService: ReviewService,
    router: Router
  ) {
    this.restaurantService = restaurantService;
    this.reviewService = reviewService;
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.reviewService.getReviews(this.id).subscribe((data) => {
      this.reviews = data;
    });
    this.router = router;
  }

  deleteRestaurant() {
    if (this.id) {
      this.restaurantService.deleteRestaurant(this.id).subscribe(
        (response) => {
          console.log('Restaurant deleted successfully:', response);
        },
        (error) => {
          console.error('Error deleting restaurant:', error);
        }
      );

      this.router.navigate(['']);
    } else {
      console.error('Invalid restaurant ID:', this.id);
    }
  }

  editRestaurant() {
    if (this.id) {
      this.router.navigate([`/edit/${this.id}`]);
    } else {
      console.error('Invalid restaurant ID:', this.id);
    }
  }

  closeDetails() {
    this.router.navigate(['']);
  }

  ngOnInit() {
    this.id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.id) {
      this.restaurantService.getRestaurants().subscribe((r) => {
        this.restaurant = r.find((res) => res.id === this.id);
      });
    } else {
      console.error('Invalid restaurant ID:', this.id);
    }
  }
}
