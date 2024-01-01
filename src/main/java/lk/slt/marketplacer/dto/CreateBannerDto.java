package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
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
public class CreateBannerDto {
    @URL
    private String imageUrl;
    private String link;
    @NotNull
    @Enumerated(EnumType.STRING)
    private BannerType bannerType;
    private int index;
    private Boolean isFeatured;
}
