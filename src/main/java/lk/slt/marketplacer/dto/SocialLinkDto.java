package lk.slt.marketplacer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SocialLinkDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String link;
    private String icon;
    //
    private Instant createdAt;
    private Instant lastUpdatedAt;
}
