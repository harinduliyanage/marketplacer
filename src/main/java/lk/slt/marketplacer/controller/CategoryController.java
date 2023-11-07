package lk.slt.marketplacer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lk.slt.marketplacer.dto.CreateCategoryDto;
import lk.slt.marketplacer.dto.ListResponseDto;
import lk.slt.marketplacer.dto.UpdateCategoryDto;
import lk.slt.marketplacer.dto.CategoryDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Tag(name = "category-services")
@RequestMapping("/api/v1/categories")
public interface CategoryController {
    @PostMapping( consumes = {"application/json"}, produces = {"application/json"})
    public CategoryDto createCategory(@RequestBody @Valid CreateCategoryDto createCategoryDto);

    @GetMapping( produces = {"application/json"})
    public ListResponseDto<CategoryDto> getCategories(@RequestParam(value = "parentCategoryId", required = false) String parentCategoryId, @ParameterObject Pageable pageable);

    @GetMapping(value = "/{categoryId}", produces = {"application/json"})
    public CategoryDto getCategory(@PathVariable("categoryId") String categoryId);

    @PatchMapping(value = "/{categoryId}", consumes = {"application/json"}, produces = {"application/json"})
    public CategoryDto updateCategory(@PathVariable("categoryId") String categoryId, @Valid @RequestBody UpdateCategoryDto updateCategoryDto);

    @DeleteMapping(value = "/{categoryId}", produces = {"application/json"})
    public CategoryDto removeCategory(@PathVariable("categoryId") String categoryId);
}
