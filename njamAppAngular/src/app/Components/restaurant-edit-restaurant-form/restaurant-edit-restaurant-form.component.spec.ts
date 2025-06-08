import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RestaurantEditRestaurantFormComponent } from './restaurant-edit-restaurant-form.component';

describe('RestaurantEditRestaurantFormComponent', () => {
  let component: RestaurantEditRestaurantFormComponent;
  let fixture: ComponentFixture<RestaurantEditRestaurantFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RestaurantEditRestaurantFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RestaurantEditRestaurantFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
