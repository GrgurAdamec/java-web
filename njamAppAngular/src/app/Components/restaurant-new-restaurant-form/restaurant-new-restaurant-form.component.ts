import { Component } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RestaurantService } from '../../Service/restaurant.service';
import { TranslateModule } from '@ngx-translate/core';

@Component({
  selector: 'app-restaurant-new-restaurant-form',
  standalone: true,
  imports: [ReactiveFormsModule, TranslateModule],
  templateUrl: './restaurant-new-restaurant-form.component.html',
  styleUrl: './restaurant-new-restaurant-form.component.css',
})
export class RestaurantNewRestaurantFormComponent {
  restaurantForm: FormGroup;
  restaurantService: RestaurantService;

  constructor(restaurantService: RestaurantService) {
    this.restaurantForm = new FormGroup({
      id: new FormControl(null, Validators.required),
      name: new FormControl('', Validators.required),
      address: new FormControl('', Validators.required),
      telephoneNumber: new FormControl(null, [
        Validators.required,
        Validators.pattern('^[0-9]*$'),
      ]),
      email: new FormControl('', [Validators.required, Validators.email]),
      workingHours: new FormControl(null, Validators.required),
      currentlyOpen: new FormControl(null, Validators.required),
      averageDeliveryTime: new FormControl(null, Validators.required),
      averageRating: new FormControl(null, [
        Validators.required,
        Validators.min(0),
        Validators.max(5),
      ]),
      maxNumberOfOrders: new FormControl(null, Validators.required),
      michelinStar: new FormControl(null, Validators.required),
      description: new FormControl('', Validators.required),
    });

    this.restaurantService = restaurantService;
  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.restaurantForm.valid) {
      this.restaurantService.addRestaurant(this.restaurantForm.value).subscribe(
        (response) => {
          console.log('Restoran je uspešno dodat:', response);
        },
        (error) => {
          console.error('Greška prilikom dodavanja restorana:', error);
        }
      );

      console.log(this.restaurantForm.value);
    } else {
      console.log('Forma nije validna!');
    }
  }
}
