package co.istad.elearning.features.category;

import co.istad.elearning.features.category.dto.CreateCategoryRequest;
import co.istad.elearning.features.category.dto.CategoryResponse;
import org.springframework.data.domain.Page;


public interface CategoryService {

    Page<CategoryResponse> getAllCategories(Integer pageNumber, Integer pageSize);
    CategoryResponse getCategoryById(Integer id);
    CategoryResponse saveCategory(CreateCategoryRequest createCategoryRequest);
    CategoryResponse patchCategoryById(Integer id, CreateCategoryRequest createCategoryRequest);
    void deleteCategoryById(Integer id);

}
