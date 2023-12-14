package lk.slt.marketplacer.dto.mapper;

import lk.slt.marketplacer.dto.CreateReviewDto;
import lk.slt.marketplacer.dto.ReviewDto;
import lk.slt.marketplacer.dto.UpdateReviewDto;
import lk.slt.marketplacer.model.Product;
import lk.slt.marketplacer.model.Review;
import lk.slt.marketplacer.service.ProductService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring", uses = ProductService.class)
public abstract class ReviewMapper {
    @Autowired
    UserMapper userMapper;

    public ReviewDto reviewToReviewDto(Review review){
        ReviewDto target = new ReviewDto();
        //
        target.setId(review.getId());
        target.setText(review.getText());
        target.setRating(review.getRating());
        target.setUser(userMapper.userToUserDto(review.getUser()));
        target.setProductId(review.getProduct().getId());
        //
        return  target;
    }
  
    public abstract  Review reviewDtoToReview(ReviewDto reviewDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(source = "productId", target = "product")
    public abstract  Review createReviewDtoToReview(CreateReviewDto createReviewDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "text", source = "text",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "rating", source = "rating",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "product", source = "productId",
            nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract  Review updateReviewDtoToReview(UpdateReviewDto updateReviewDto, @MappingTarget Review review);

    public abstract  List<ReviewDto> reviewListToReviewDtoList(List<Review> reviewList);
}
