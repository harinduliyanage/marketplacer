package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lk.slt.marketplacer.util.BannerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBannerDto {
    private String imageUrl;
    @URL
    private String link;
    @Enumerated(EnumType.STRING)
    private BannerType bannerType;
    private int index;
    private Boolean isFeatured;
}
