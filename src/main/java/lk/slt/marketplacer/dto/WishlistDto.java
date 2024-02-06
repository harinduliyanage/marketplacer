package lk.slt.marketplacer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDto {
    private String id;
    private Set<ProductDto> products;
    //
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
