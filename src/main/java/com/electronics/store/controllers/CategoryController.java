package com.electronics.store.controllers;

import com.electronics.store.categoryservice.CategoryService;
import com.electronics.store.config.AppConstants;
import com.electronics.store.dtos.CategoryDto;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.UserDto;
import com.electronics.store.payloads.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/createcategory")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto category = this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<CategoryDto>(category, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> createCategory(@PathVariable Integer categoryId) {
        CategoryDto singleCategory = this.categoryService.getSingleCategory(categoryId);
        return new ResponseEntity<CategoryDto>(singleCategory, HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        log.info("Initiated request to update single category");
        CategoryDto categoryDtos = categoryService.updateCategory(categoryDto, categoryId);
        log.info("Completed request to update single category");
        return new ResponseEntity<CategoryDto>(categoryDtos, HttpStatus.OK);

    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
        log.info("Intiated request to delete category");
        this.categoryService.deleteCategory(categoryId);
        log.info("Completed request to delete category");
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.Category_Deleted,true,new Date()),HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<CategoryDto>> searchCategory(@PathVariable String keyword) {
        List<CategoryDto> categoryDtos = this.categoryService.searchCategory(keyword);
        return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);

    }

    // Get all categories
    @GetMapping("/")
    public ResponseEntity<PageableResponse<CategoryDto>> getallCategories(
            @RequestParam(value="pageNumber", defaultValue ="0",required = false ) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue ="title",required = false ) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
    ) {
        log.info("Initiated request to get all categories");
        PageableResponse<CategoryDto> allCategories = this.categoryService.getAllCategories(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed request to get all categories");
        return new ResponseEntity<PageableResponse<CategoryDto>>(allCategories,HttpStatus.OK);//200
    }

}


