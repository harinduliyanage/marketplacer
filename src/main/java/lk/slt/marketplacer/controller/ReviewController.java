package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.CreateReviewDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UpdateReviewDto;
import lk.slt.marketplacer.dto.ReviewDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author harindu.sul@gmail.com
 */
@Tag(name = "review-services")
@RequestMapping("/api/v1")
public interface ReviewController {
    @PostMapping(value = "/users/{userId}/reviews", consumes = {"application/json"}, produces = {"application/json"})
    public ReviewDto createReview(@PathVariable("userId") String userId, @Valid @RequestBody CreateReviewDto createReviewDto);

    @GetMapping(value = "/reviews", produces = {"application/json"})
    public ListResponseDto<ReviewDto> getReviews(@RequestParam(value = "productId", required = false) String productId, @ParameterObject Pageable pageable);

    @GetMapping(value = "/users/{userId}/reviews", produces = {"application/json"})
    public ListResponseDto<ReviewDto> getUserReviews(@PathVariable("userId") String userId, @ParameterObject Pageable pageable);

    @GetMapping(value = "/users/{userId}/reviews/{reviewId}", produces = {"application/json"})
    public ReviewDto getReview(@PathVariable("userId") String userId, @PathVariable("reviewId") String reviewId);

    @PatchMapping(value = "/users/{userId}/reviews/{reviewId}", consumes = {"application/json"}, produces = {"application/json"})
    public ReviewDto updateReview(@PathVariable("userId") String userId, @PathVariable("reviewId") String reviewId, @Valid @RequestBody UpdateReviewDto updateReviewDto);

    @DeleteMapping(value = "/users/{userId}/reviews/{reviewId}", produces = {"application/json"})
    public ReviewDto removeReview(@PathVariable("userId") String userId, @PathVariable("reviewId") String reviewId);
}
