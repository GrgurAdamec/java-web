package hr.tvz.adamec.njamapp.controller;

import hr.tvz.adamec.njamapp.model.Restaurant;
import hr.tvz.adamec.njamapp.model.RestaurantCommand;
import hr.tvz.adamec.njamapp.model.RestaurantDTO;
import hr.tvz.adamec.njamapp.repositories.RestaurantRepository;
import hr.tvz.adamec.njamapp.services.IRestaurantService;
import hr.tvz.adamec.njamapp.services.RestaurantService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.List.of;

@RestController
@RequestMapping("/restaurants")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RestaurantController {
    private IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/all")
    public List<RestaurantDTO> findAll(){
        return restaurantService.findAll();
    }

    @GetMapping("/byID/{id}")
    public ResponseEntity<?> findRestaurantByID(@PathVariable Long id){
        Optional<RestaurantDTO> restaurantDTO = restaurantService.findRestoranByID(id);

        if(restaurantDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(restaurantDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found.");
        }
    }

    @GetMapping("/getRestaurantForEdit/{id}")
    public ResponseEntity<?> fullRestaurantInfoForEdit(@PathVariable Long id){
        Optional<Restaurant> restaurant = restaurantService.fullRestaurantInfo(id);

        if(restaurant.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found.");
        }
    }

    @GetMapping("/byName/{name}")
    public ResponseEntity<?> findRestaurantByName(@PathVariable String name){
        Optional<RestaurantDTO> restaurantDTO = restaurantService.findRestaurantByName(name);

        if(restaurantDTO.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(restaurantDTO.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Restaurant not found.");
        }
    }

    @GetMapping("/closest/{address}/{rating}")
    public List<RestaurantDTO> findClosest(@PathVariable String address, @PathVariable Double rating){
        return restaurantService.findCloesestAndBest(address, rating);
    }

    @PostMapping("/createNew")
    public ResponseEntity<?> createRestaurant(@Valid @RequestBody RestaurantCommand restaurant) {
        Optional<RestaurantDTO> newRestaurantDTO = restaurantService.saveRestaurant(restaurant);

        if(newRestaurantDTO.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(newRestaurantDTO.get());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Restaurant already exists.");
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurant(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRestaurant(@PathVariable Long id, @Valid @RequestBody RestaurantCommand restaurant){
        Optional<RestaurantDTO> updated = restaurantService.updateRestaurant(restaurant);
        return updated
                .map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/bestRated/lastMonth")
    public Optional<RestaurantDTO> findBestRatedLastMonth(){
        return restaurantService.findBestRatedLastMonth();
    }

    @GetMapping("/bestRated/lastWeek")
    public Optional<RestaurantDTO> findBestRatedLastWeek(){
        return restaurantService.findBestRatedLastWeek();
    }
}
