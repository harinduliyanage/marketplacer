package lk.slt.marketplacer.dto;

import lk.slt.marketplacer.util.StoreStatus;
import lombok.Data;

@Data
public class UpdateStoreDto extends CreateStoreDto{
    private StoreStatus storeStatus;
}
