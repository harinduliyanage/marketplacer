package lk.slt.marketplacer.service;

import lk.slt.marketplacer.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author harindu.sul@gmail.com
 */
public interface ReviewService {

    public Review createReview(String userId, Review review);

    public Review getReview(String userId, String reviewId);

    public Page<Review> getUserReviews(String userId, Pageable pageable);

    public Page<Review> getReviews(String productId, Pageable pageable);

    public Review updateReview(String userId, String reviewId, Review review);

    public Review removeReview(String userId, String reviewId);
}
