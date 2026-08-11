package co.istad.elearning.features.category;


import co.istad.elearning.features.category.dto.CategoryResponse;
import co.istad.elearning.features.category.dto.CreateCategoryRequest;
import co.istad.elearning.features.category.mapper.CategoryMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpRequest;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper,
                               CategoryRepository categoryRepository){
        this.categoryMapper = categoryMapper;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<CategoryResponse> getAllCategories(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(categoryMapper::mapToResponse);
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        return categoryMapper.mapToResponse(categoryRepository.findById(id)
                .stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id not found") ));
    }

    @Override
    public CategoryResponse saveCategory(CreateCategoryRequest createCategoryRequest) {
        Category newCategory = categoryMapper.mapToEntity(createCategoryRequest);
        return categoryMapper.mapToResponse(categoryRepository.save(newCategory));
    }

    @Override
    public CategoryResponse patchCategoryById(Integer id, CreateCategoryRequest createCategoryRequest) {

        if (!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Id not found"
            );
        }

        Category updateCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Id not found"
                ));

        if (createCategoryRequest.name() != null){
            updateCategory.setName(createCategoryRequest.name());
        }

        if (createCategoryRequest.icon() != null){
            updateCategory.setIcon(createCategoryRequest.icon());
        }


        return categoryMapper.mapToResponse(categoryRepository.save(updateCategory));
    }

    @Override
    public void deleteCategoryById(Integer id) {

        if (!categoryRepository.existsById(id)){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Id not found");
        }
        categoryRepository.deleteById(id);
    }
}
