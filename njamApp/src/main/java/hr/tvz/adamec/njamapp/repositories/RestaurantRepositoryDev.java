package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Restaurant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Profile("dev")
public class RestaurantRepositoryDev implements IRestaurantRepository{
    private List<Restaurant> restaurants;

    public RestaurantRepositoryDev() {
        this.restaurants = List.of(
                new Restaurant(1L, "Pizza dev", "123 Main St", 1234567890L, "contact@pizzapalace.com", 10, true, 30, 4.5, 100F, 2, "first restaurant"),
                new Restaurant(2L, "Burger dev", "456 Elm St", 9876543210L, "info@burgerbistro.com", 12, false, 20, 4.2, 70F, 3, "second restaurant"),
                new Restaurant(3L, "Taco dev", "789 Oak St", 1112223333L, "hello@tacotown.com", 8, true, 15, 3.8, 50F, 4, "third restaurant"),
                new Restaurant(4L, "Sushi dev", "321 Maple St", 2223334444L, "order@sushispot.com", 9, true, 25, 4.6, 90F, 5, "fourth restaurant"),
                new Restaurant(5L, "Pasta dev", "654 Pine St", 3334445555L, "support@pastaparadise.com", 11, false, 40, 4.4, 80F, 3, "fifth restaurant")
        );
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
        return Optional.empty();
    }

    @Override
    public Optional<Restaurant> saveRestaurant(Restaurant restaurant) {
        return null;
    }

    @Override
    public Optional<Restaurant> updateRestaurant(Restaurant restaurant) {
        return Optional.empty();
    }

    @Override
    public void deleteRestaurant(Long id) {

    }
}
