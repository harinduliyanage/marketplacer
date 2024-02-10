package lk.slt.marketplacer.service.impl;

import com.querydsl.core.types.dsl.BooleanExpression;
import lk.slt.marketplacer.exceptions.ReviewNotFoundException;
import lk.slt.marketplacer.model.*;
import lk.slt.marketplacer.repository.ReviewRepository;
import lk.slt.marketplacer.service.ReviewService;
import lk.slt.marketplacer.service.UserService;
import lk.slt.marketplacer.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
@Slf4j
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private UserService userService;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Review createReview(String userId, Review review) {
        User foundUser = userService.getUser(userId);
        review.setUser(foundUser);
        Review saveReview = reviewRepository.save(review);
        log.info("Review has been successfully created {}", saveReview);
        //
        return saveReview;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Review getReview(String userId, String reviewId) {
        User foundUser = userService.getUser(userId);
        //
        QReview qReview = QReview.review;
        BooleanExpression expression = qReview.user.eq(foundUser).and(qReview.id.eq(reviewId));
        Optional<Review> found = reviewRepository.findOne(expression);
        //
        if (found.isPresent()) {
            return found.get();
        } else {
            throw new ReviewNotFoundException(String.format(Constants.REVIEW_NOT_FOUND_MSG, userId, userId));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Review> getUserReviews(String userId, Pageable pageable) {
        User foundUser = userService.getUser(userId);
        QReview qReview = QReview.review;
        BooleanExpression expression = qReview.user.eq(foundUser);
        return reviewRepository.findAll(expression, pageable);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Page<Review> getReviews(String productId, Pageable pageable) {
        if(null == productId){
            return reviewRepository.findAll(pageable);
        }else {
            QReview qReview = QReview.review;
            BooleanExpression expression = qReview.product.id.eq(productId);
            return reviewRepository.findAll(expression, pageable);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Review updateReview(String userId, String reviewId, Review review) {
        review.setId(reviewId);
        //
        Review updatedReview = reviewRepository.save(review);
        log.info("review has been successfully updated {}", updatedReview);
        //
        return updatedReview;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Review removeReview(String userId, String reviewId) {
        Review foundReview = getReview(userId, reviewId);
        //
        reviewRepository.deleteById(reviewId);
        log.info("user has been successfully deleted {}", foundReview);
        //
        return foundReview;
    }
}
