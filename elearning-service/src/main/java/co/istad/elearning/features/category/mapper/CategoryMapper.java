package co.istad.elearning.features.category.mapper;


import co.istad.elearning.features.category.Category;
import co.istad.elearning.features.category.dto.CategoryResponse;
import co.istad.elearning.features.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse mapToResponse(Category category);
    Category mapToEntity(CreateCategoryRequest createCategoryRequest);

}

