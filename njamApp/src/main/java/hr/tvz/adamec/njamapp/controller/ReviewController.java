package hr.tvz.adamec.njamapp.controller;


import hr.tvz.adamec.njamapp.model.RestaurantDTO;
import hr.tvz.adamec.njamapp.model.Review;
import hr.tvz.adamec.njamapp.services.IRestaurantService;
import hr.tvz.adamec.njamapp.services.IReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ReviewController {
    private IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/all/{id}")
    public List<Review> findAll(@PathVariable Long id){
        return reviewService.getALlReviewsByRestaurantId(id);
    }
}
