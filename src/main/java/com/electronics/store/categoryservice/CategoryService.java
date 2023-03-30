package com.electronics.store.categoryservice;

import com.electronics.store.dtos.CategoryDto;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.UserDto;

import java.util.List;

public interface CategoryService {


    //create category
    public CategoryDto createCategory(CategoryDto categoryDto);

    //Update category
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    //Get single category by id
    public CategoryDto getSingleCategory(Integer categoryId);

    //get all users
    public PageableResponse<CategoryDto> getAllCategories(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    //Search user
    List<CategoryDto> searchCategory(String keyword);

    //Delete user
    public void deleteCategory(Integer categoryId);

}
