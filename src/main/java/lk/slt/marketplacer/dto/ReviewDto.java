package lk.slt.marketplacer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private String id;
    private String text;
    private Integer rating;
    private String productId;
    private UserDto user;
    //
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
