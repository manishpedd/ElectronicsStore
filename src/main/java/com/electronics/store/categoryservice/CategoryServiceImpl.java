package com.electronics.store.categoryservice;

import com.electronics.store.config.AppConstants;
import com.electronics.store.dtos.CategoryDto;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.entities.Category;
import com.electronics.store.exceptions.ResourceNotFoundException;
import com.electronics.store.helper.Helper;
import com.electronics.store.repository.CategoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        log.info("Intiating dao call to save category data");
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category savedCategory = this.categoryRepo.save(category);
        log.info("Completed dao call to save category data");
        return this.modelMapper.map(savedCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        log.info("Intiating dao call to update user data" + categoryId);
        //Get
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));

        //Set category details
        category.setCategoryId(categoryDto.getCategoryId());
        category.setTitle(categoryDto.getTitle());
        category.setDescription(categoryDto.getDescription());
        category.setCoverImage(categoryDto.getCoverImage());

        //Update
        Category savedcategory = this.categoryRepo.save(category);
        log.info("Completed dao call to update user data" + categoryId);
        //Get
        return this.modelMapper.map(savedcategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getSingleCategory(Integer categoryId) {
        log.info("Intiating dao call to get single category data");
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));
        log.info("Completed dao call to get single category data");
        return this.modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public PageableResponse<CategoryDto> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        log.info("Intiating dao call to get all category data");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
        Page<Category> page = this.categoryRepo.findAll(pageable);
        PageableResponse<CategoryDto> pageableResponse = Helper.getPageableResponse(page, CategoryDto.class);
        log.info("Completed dao call to get all category data");
        return pageableResponse;
    }

    @Override
    public List<CategoryDto> searchCategory(String keyword) {
        log.info("Initiated dao call to search category on the basis of keyword");
        List<Category> categories = this.categoryRepo.findByTitleContaining(keyword);
        List<CategoryDto> categoryDtos = categories.stream().map((category) -> this.modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        log.info("Completed dao call to search category on the basis of keyword");
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        log.info("Intiating dao call to delete category data");
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.CATEGORY_NOT_FOUND));
        log.info("Completed dao call to delete category data" +categoryId);
        this.categoryRepo.delete(category);

    }
}
