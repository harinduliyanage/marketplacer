package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.BannerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BannerDto {
    private String id;
    private String imageUrl;
    private String link;
    private BannerType bannerType;
    private int index;
    private Boolean isFeatured;
    //
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
