package lk.slt.marketplacer.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lk.slt.marketplacer.util.Extension;
import lk.slt.marketplacer.util.FolderType;
import lombok.Data;

@Data
public class CreateFileDto {
    @NotNull
    @Enumerated(EnumType.STRING)
    private FolderType type;
    private String userId;
    private String storeId;
    private String productId;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Extension extension;
}
