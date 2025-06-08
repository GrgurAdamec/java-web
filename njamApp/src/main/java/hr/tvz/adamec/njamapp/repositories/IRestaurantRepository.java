package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Restaurant;
import hr.tvz.adamec.njamapp.model.Review;

import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository {
    List<Restaurant> findAll();
    Optional<Restaurant> findRestaurantById(Long id);
    Optional<Restaurant> findRestaurantByName(String name);
    Optional<Restaurant> saveRestaurant(Restaurant restaurant);
    Optional<Restaurant> updateRestaurant(Restaurant restaurant);
    void deleteRestaurant(Long id);
}
