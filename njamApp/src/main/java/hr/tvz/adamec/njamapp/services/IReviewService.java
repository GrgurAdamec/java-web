package hr.tvz.adamec.njamapp.services;

import hr.tvz.adamec.njamapp.model.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewService {
    List<Review> getALlReviewsByRestaurantId(Long id);
}
