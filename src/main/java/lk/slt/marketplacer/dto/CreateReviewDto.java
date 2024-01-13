package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewDto {
    @NotEmpty
    private String text;
    @NotNull
    private Integer rating;
    @NotEmpty
    private String productId;
}
