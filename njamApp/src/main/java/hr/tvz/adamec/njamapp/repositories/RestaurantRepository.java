package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Restaurant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.tags.ArgumentAware;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("dev")
public class RestaurantRepository implements IRestaurantRepository {
    private List<Restaurant> restaurants;

    public RestaurantRepository() {
        this.restaurants = new ArrayList<>();

        restaurants.add(new Restaurant(1L, "Pizza Palace", "123 Main St", 1234567890L, "contact@pizzapalace.com", 10, true, 30, 4.5, 100F, 3, "First restaurant"));
        restaurants.add(new Restaurant(2L, "Burger Bistro", "456 Elm St", 9876543210L, "info@burgerbistro.com", 12, false, 20, 4.2, 70F, 4, "Second restaurant"));
        restaurants.add(new Restaurant(3L, "Taco Town", "789 Oak St", 1112223333L, "hello@tacotown.com", 8, true, 15, 3.8, 50F, 5, "Third restaurant"));
        restaurants.add(new Restaurant(4L, "Sushi Spot", "321 Maple St", 2223334444L, "order@sushispot.com", 9, true, 25, 4.6, 90F, 5, "Fourth restaurant"));
        restaurants.add(new Restaurant(5L, "Pasta Paradise", "654 Pine St", 3334445555L, "support@pastaparadise.com", 11, false, 40, 4.4, 80F, 2, "Fifth restaurant"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Optional<Restaurant> findRestaurantById(Long id) {
        return restaurants.stream().filter(r -> r.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Restaurant> findRestaurantByName(String name) {
        return restaurants.stream().filter(r -> r.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<Restaurant> saveRestaurant(Restaurant restaurant) {
        boolean restaurantAlreadyExists = restaurants.stream().anyMatch(r -> r.getName().equals(restaurant.getName()) && r.getAddress().equals(restaurant.getAddress()));

        if(!restaurantAlreadyExists){
            restaurants.add(restaurant);
            return Optional.of(restaurant);
        }

        return Optional.empty();
    }

    @Override
    public Optional<Restaurant> updateRestaurant(Restaurant restaurant) {
        return restaurants.stream().filter(r -> r.getId().equals(restaurant.getId())).findFirst()
                .map(existing ->
                {existing.setName(restaurant.getName());
                existing.setAddress(restaurant.getAddress());
                existing.setTelephoneNumber(restaurant.getTelephoneNumber());
                existing.setEmail(restaurant.getEmail());
                existing.setWorkingHours(restaurant.getWorkingHours());
                existing.setCurrentlyOpen(restaurant.getCurrentlyOpen());
                existing.setAverageDeliveryTime(restaurant.getAverageDeliveryTime());
                existing.setAverageRating(restaurant.getAverageRating());
                existing.setMaxNumberOfOrders(restaurant.getMaxNumberOfOrders());
                existing.setMichelinStar(restaurant.getMichelinStar());
                existing.setDescription(restaurant.getDescription());
                    return existing;
                });
    }

    @Override
    public void deleteRestaurant(Long id) {
        this.restaurants = restaurants.stream().filter(r -> !r.getId().equals(id)).collect(Collectors.toList());
    }
}
