package hr.tvz.adamec.njamapp.services;

import hr.tvz.adamec.njamapp.model.Restaurant;
import hr.tvz.adamec.njamapp.model.RestaurantCommand;
import hr.tvz.adamec.njamapp.model.RestaurantDTO;
import hr.tvz.adamec.njamapp.model.Review;
import hr.tvz.adamec.njamapp.repositories.IRestaurantRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements IRestaurantService{
    private final IRestaurantRepository restaurantRepository;
    private final IReviewService reviewService;

    public RestaurantService(IRestaurantRepository restaurantRepository, IReviewService reviewService) {
        this.restaurantRepository = restaurantRepository;
        this.reviewService = reviewService;
    }

    private RestaurantDTO convertRestaurantToDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();

        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddress(restaurant.getAddress());
        restaurantDTO.setCurrentlyOpen(restaurant.getCurrentlyOpen());
        restaurantDTO.setWorkload(10/restaurant.getMaxNumberOfOrders());

        return restaurantDTO;
    }

    private Restaurant convertRestaurantDTOToRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();

        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        restaurant.setCurrentlyOpen(restaurantDTO.getCurrentlyOpen());
        restaurant.setMaxNumberOfOrders(10/restaurantDTO.getWorkload());

        return restaurant;
    }

    private Restaurant convertRestaurantCommandToRestaurant(RestaurantCommand restaurantCommand) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantCommand.getId());
        restaurant.setName(restaurantCommand.getName());
        restaurant.setAddress(restaurantCommand.getAddress());
        restaurant.setTelephoneNumber(restaurantCommand.getTelephoneNumber());
        restaurant.setEmail(restaurantCommand.getEmail());
        restaurant.setWorkingHours(restaurantCommand.getWorkingHours());
        restaurant.setCurrentlyOpen(restaurantCommand.getCurrentlyOpen());
        restaurant.setAverageDeliveryTime(restaurantCommand.getAverageDeliveryTime());
        restaurant.setAverageRating(restaurantCommand.getAverageRating());
        restaurant.setMaxNumberOfOrders(restaurantCommand.getMaxNumberOfOrders());
        restaurant.setMichelinStar(restaurantCommand.getMichelinStar());
        restaurant.setDescription(restaurantCommand.getDescription());

        return restaurant;
    }

    @Override
    public List<RestaurantDTO> findAll() {
        return restaurantRepository.findAll().stream().map(this::convertRestaurantToDTO).toList();
    }

    @Override
    public Optional<RestaurantDTO> findRestoranByID(Long id) {
        return restaurantRepository.findRestaurantById(id).map(this::convertRestaurantToDTO);
    }

    @Override
    public Optional<Restaurant> fullRestaurantInfo(Long id) {
        return restaurantRepository.findRestaurantById(id);
    }

    @Override
    public List<RestaurantDTO> findClosest(String adresa) {
        return List.of();
    }

    @Override
    public List<RestaurantDTO> findBest(Double ocjena) {
        return List.of();
    }

    @Override
    public List<RestaurantDTO> findCloesestAndBest(String adresa, Double ocjena) {
        return restaurantRepository.findAll().stream().filter(r -> r.getAddress().equals(adresa) && r.getAverageRating() >= ocjena).map(this::convertRestaurantToDTO).toList();
    }

    @Override
    public Optional<RestaurantDTO> findRestaurantByName(String name) {
        return restaurantRepository.findRestaurantByName(name).map(this::convertRestaurantToDTO);
    }

    @Override
    public Optional<RestaurantDTO> saveRestaurant(RestaurantCommand restaurant) {
        Optional<Restaurant> newRestaurant = restaurantRepository.saveRestaurant(convertRestaurantCommandToRestaurant(restaurant));

        return newRestaurant.map(this::convertRestaurantToDTO);

    }

    @Override
    public Optional<RestaurantDTO> updateRestaurant(RestaurantCommand restaurant) {
        return restaurantRepository.updateRestaurant(convertRestaurantCommandToRestaurant(restaurant)).map(this::convertRestaurantToDTO);
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteRestaurant(id);
    }

    @Override
    public Optional<RestaurantDTO> findBestRatedLastMonth() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        Long highestRating = 0L;
        RestaurantDTO highestRestaurantDTO = new RestaurantDTO();

        for (Restaurant restaurant : restaurants) {
            List<Review> reviews = reviewService.getALlReviewsByRestaurantId(restaurant.getId());
            int counter = 0;
            Long currentRating = 0L;

            if(!reviews.isEmpty()) {
                for (Review review : reviews) {
                    if (review.getDate().isAfter(LocalDate.now().minusMonths(1))) {
                        counter++;
                        currentRating += review.getGrade();
                    }
                }

                if(currentRating > 0) {
                    currentRating /= counter;
                }

                if(currentRating > highestRating) {
                    highestRating = currentRating;
                    highestRestaurantDTO = convertRestaurantToDTO(restaurant);
                }
            }
        }

        return Optional.of(highestRestaurantDTO);
    }

    @Override
    public Optional<RestaurantDTO> findBestRatedLastWeek() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        Long highestRating = 0L;
        RestaurantDTO highestRestaurantDTO = new RestaurantDTO();

        for (Restaurant restaurant : restaurants) {
            List<Review> reviews = reviewService.getALlReviewsByRestaurantId(restaurant.getId());
            int counter = 0;
            Long currentRating = 0L;

            if(!reviews.isEmpty()) {
                for (Review review : reviews) {
                    if (review.getDate().isAfter(LocalDate.now().minusWeeks(1))) {
                        counter++;
                        currentRating += review.getGrade();
                    }
                }

                if(currentRating > 0) {
                    currentRating /= counter;
                }

                if(currentRating > highestRating) {
                    highestRating = currentRating;
                    highestRestaurantDTO = convertRestaurantToDTO(restaurant);
                }
            }
        }

        return Optional.of(highestRestaurantDTO);
    }
}
