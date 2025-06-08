export interface RestaurantExtended {
  id: number;
  name: string;
  address: string;
  telephoneNumber: number;
  email: string;
  workingHours: number;
  currentlyOpen: boolean;
  averageDeliveryTime: number;
  averageRating: number;
  maxNumberOfOrders: number;
  michelinStar: number;
  description: string;
}
