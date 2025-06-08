import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../../Service/restaurant.service';
import { RestaurantExtended } from '../../Model/restaurant-extended';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-restaurant-edit-restaurant-form',
  standalone: true,
  imports: [ReactiveFormsModule, TranslateModule],
  templateUrl: './restaurant-edit-restaurant-form.component.html',
  styleUrl: './restaurant-edit-restaurant-form.component.css',
})
export class RestaurantEditRestaurantFormComponent {
  @Input() id: number | null = null;
  restaurantService: RestaurantService;
  router: Router;
  existingRestaurant: any;
  restaurantForm!: FormGroup;

  constructor(
    private route: ActivatedRoute,
    restaurantService: RestaurantService,
    router: Router
  ) {
    this.restaurantForm = new FormGroup({
      id: new FormControl({ value: null, disabled: true }),
      name: new FormControl(null, Validators.required),
      address: new FormControl(null, Validators.required),
      telephoneNumber: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      workingHours: new FormControl(null, Validators.required),
      currentlyOpen: new FormControl(null, Validators.required),
      averageDeliveryTime: new FormControl(null, Validators.required),
      averageRating: new FormControl(null, Validators.required),
      maxNumberOfOrders: new FormControl(null, Validators.required),
      michelinStar: new FormControl(null, Validators.required),
      description: new FormControl(null, Validators.required),
    });

    this.restaurantService = restaurantService;
    this.router = router;

    this.id = Number(this.route.snapshot.paramMap.get('id'));
    if (this.id) {
      this.restaurantService.getRestaurantForEdit(this.id).subscribe(
        (response) => {
          this.existingRestaurant = response;
          this.restaurantForm.patchValue(this.existingRestaurant);
          console.log('Restaurant details:', response);
        },
        (error) => {
          console.error('Error fetching restaurant details:', error);
        }
      );
    } else {
      console.error('Invalid restaurant ID:', this.id);
    }
  }

  onSubmit(): void {
    if (this.restaurantForm.valid) {
      console.log('Poslano:', this.restaurantForm.value);

      this.existingRestaurant = this.restaurantForm.value;
      this.existingRestaurant.id = this.id;

      this.restaurantService
        .updateRestaurant(this.existingRestaurant.id, this.existingRestaurant)
        .subscribe(
          (response) => {
            console.log('Restoran je uspešno izmenjen:', response);
            this.router.navigate(['']);
          },
          (error) => {
            console.error('Greška prilikom izmene restorana:', error);
          }
        );
    } else {
      console.warn('Forma nije validna');
    }
  }
}
