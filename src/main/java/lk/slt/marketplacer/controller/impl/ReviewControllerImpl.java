package lk.slt.marketplacer.controller.impl;

import lk.slt.marketplacer.controller.ReviewController;
import lk.slt.marketplacer.dto.*;
import lk.slt.marketplacer.dto.mapper.ReviewMapper;
import lk.slt.marketplacer.model.Review;
import lk.slt.marketplacer.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewControllerImpl implements ReviewController {
    @Autowired
    ReviewMapper reviewMapper;
    @Autowired
    ReviewService reviewService;

    /**
     * @param userId
     * @param createReviewDto
     * @return
     */
    @Override
    public ReviewDto createReview(String userId, CreateReviewDto createReviewDto) {
        Review createdReview = reviewService.createReview(userId, reviewMapper.createReviewDtoToReview(createReviewDto));
        return reviewMapper.reviewToReviewDto(createdReview);
    }

    /**
     * @param productId
     * @param pageable
     * @return
     */
    @Override
    public ListResponseDto<ReviewDto> getReviews(String productId, Pageable pageable) {
        Page<Review> page = reviewService.getReviews(productId, pageable);
        return ListResponseDto.<ReviewDto>builder()
                .data(reviewMapper.reviewListToReviewDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    /**
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public ListResponseDto<ReviewDto> getUserReviews(String userId, Pageable pageable) {
        Page<Review> page = reviewService.getUserReviews(userId, pageable);
        return ListResponseDto.<ReviewDto>builder()
                .data(reviewMapper.reviewListToReviewDtoList(page.getContent()))
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalResults(page.getNumberOfElements())
                .totalPages(page.getTotalPages())
                .build();
    }

    /**
     * @param userId
     * @param reviewId
     * @return
     */
    @Override
    public ReviewDto getReview(String userId, String reviewId) {
        Review review = reviewService.getReview(userId, reviewId);
        return reviewMapper.reviewToReviewDto(review);
    }

    /**
     * @param userId
     * @param reviewId
     * @param updateReviewDto
     * @return
     */
    @Override
    public ReviewDto updateReview(String userId, String reviewId, UpdateReviewDto updateReviewDto) {
        Review foundReview = reviewService.getReview(userId, reviewId);
        Review review = reviewMapper.updateReviewDtoToReview(updateReviewDto, foundReview);
        Review updatedReview = reviewService.updateReview(userId, reviewId, review);
        //
        return reviewMapper.reviewToReviewDto(updatedReview);
    }

    /**
     * @param userId
     * @param reviewId
     * @return
     */
    @Override
    public ReviewDto removeReview(String userId, String reviewId) {
        Review deletedReview = reviewService.removeReview(userId, reviewId);
        return reviewMapper.reviewToReviewDto(deletedReview);
    }
}
