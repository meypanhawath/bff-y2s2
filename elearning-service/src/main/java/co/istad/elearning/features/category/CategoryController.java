package co.istad.elearning.features.category;

import co.istad.elearning.features.category.dto.CategoryResponse;
import co.istad.elearning.features.category.dto.CreateCategoryRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/categories")
@Slf4j
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public Page<CategoryResponse> getAllCategories(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                   @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return categoryService.getAllCategories(pageNumber, pageSize);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponse createNewCategory(@RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.saveCategory(createCategoryRequest);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryResponse patchCategoryById(@PathVariable Integer id,
                                              @RequestBody CreateCategoryRequest createCategoryRequest){
        return categoryService.patchCategoryById(id, createCategoryRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategoryById(@PathVariable Integer id){
        categoryService.deleteCategoryById(id);
    }
}


