package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.Extension;
import lk.slt.marketplacer.util.FolderType;
import lombok.Data;

@Data
public class CreateFileDto {
    private FolderType type;
    private String userId;
    private String storeId;
    private String productId;
    private Extension extension;
}
