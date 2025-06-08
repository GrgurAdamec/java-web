package hr.tvz.adamec.njamapp.services;

import hr.tvz.adamec.njamapp.model.Review;
import hr.tvz.adamec.njamapp.repositories.IRestaurantRepository;
import hr.tvz.adamec.njamapp.repositories.IReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService implements IReviewService {
    private final IReviewRepository reviewRepository;

    public ReviewService(IReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getALlReviewsByRestaurantId(Long id) {
        return reviewRepository.getALlReviewsByRestaurantId(id);
    }
}
