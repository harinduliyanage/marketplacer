package lk.slt.marketplacer.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ListResponseDto <T> {
    private List<T> data;
    private Integer page;
    private Integer limit;
    private Integer totalPages;
    private Integer totalResults;
}
