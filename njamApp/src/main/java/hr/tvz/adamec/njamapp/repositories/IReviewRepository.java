package hr.tvz.adamec.njamapp.repositories;

import hr.tvz.adamec.njamapp.model.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewRepository {
    List<Review> getALlReviewsByRestaurantId(Long id);
}
