import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantNewRestaurantFormComponent } from './restaurant-new-restaurant-form.component';

describe('RestaurantNewRestaurantFormComponent', () => {
  let component: RestaurantNewRestaurantFormComponent;
  let fixture: ComponentFixture<RestaurantNewRestaurantFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantNewRestaurantFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RestaurantNewRestaurantFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
