package hr.tvz.adamec.njamapp.services;

import hr.tvz.adamec.njamapp.model.Restaurant;
import hr.tvz.adamec.njamapp.model.RestaurantCommand;
import hr.tvz.adamec.njamapp.model.RestaurantDTO;
import java.util.List;
import java.util.Optional;

public interface IRestaurantService {
    List<RestaurantDTO> findAll();
    Optional<RestaurantDTO> findRestoranByID(Long id);
    Optional<Restaurant> fullRestaurantInfo(Long id);
    List<RestaurantDTO> findClosest(String adresa);
    List<RestaurantDTO> findBest(Double ocjena);
    List<RestaurantDTO> findCloesestAndBest(String adresa, Double ocjena);
    Optional<RestaurantDTO> findRestaurantByName(String name);
    Optional<RestaurantDTO> saveRestaurant(RestaurantCommand restaurant);
    Optional<RestaurantDTO> updateRestaurant(RestaurantCommand restaurant);
    void deleteRestaurant(Long id);
    Optional<RestaurantDTO> findBestRatedLastMonth();
    Optional<RestaurantDTO> findBestRatedLastWeek();
}
