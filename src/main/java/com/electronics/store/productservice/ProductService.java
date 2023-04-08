package com.electronics.store.productservice;

import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.ProductDto;
import com.electronics.store.dtos.UserDto;

import java.util.List;

public interface ProductService {

    //Create product
    public ProductDto createProduct(ProductDto productDto);

    //Update Product
    public ProductDto updateProduct(ProductDto productDto, Integer productId);

    //Get single Product by id
    public ProductDto getSingleProduct(Integer productId);

    //Get all products
    public PageableResponse<ProductDto> getAllProducts(Integer pageNumber,Integer PageSize,String sortBy,String sortDir);

    //Delete Product
    public void deleteProduct(Integer productId);

    //Get all Live
    PageableResponse<ProductDto> getAllLive(Integer pageNumber,Integer PageSize,String sortBy,String sortDir);

    //Get all
    PageableResponse<ProductDto> getAll(Integer pageNumber,Integer PageSize,String sortBy,String sortDir);

    //Search by title
    PageableResponse<ProductDto> searchBytitle(String title,Integer pageNumber,Integer PageSize,String sortBy,String sortDir);

}
