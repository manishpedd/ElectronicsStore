package com.electronics.store.productservice;

import com.electronics.store.config.AppConstants;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.ProductDto;
import com.electronics.store.dtos.UserDto;
import com.electronics.store.entities.Product;
import com.electronics.store.exceptions.ResourceNotFoundException;
import com.electronics.store.helper.Helper;
import com.electronics.store.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        log.info("Intiating dao call to save product data");
        Product product = this.modelMapper.map(productDto, Product.class);
        product.setIsactive(AppConstants.YES);
        Product savedProduct = this.productRepo.save(product);
        log.info("Completed dao call to save product data");
        return this.modelMapper.map(savedProduct, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer productId) {
        log.info("Intiating dao call to update product data" + productId);
        //Get
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));

        //Set
        product.setDescription(productDto.getDescription());
        product.setLive(productDto.getLive());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getLive());
        product.setTitle(productDto.getTitle());
        product.setQuantity(productDto.getQuantity());
        product.setDiscountedPrice(productDto.getDiscountedPrice());
        productDto.setAddedDate(productDto.getAddedDate());

        //update
        Product savedproduct = this.productRepo.save(product);
        log.info("Completed dao call to update product data" + productId);
        return this.modelMapper.map(savedproduct, ProductDto.class);


    }

    @Override
    public ProductDto getSingleProduct(Integer productId) {
        log.info("Intiating dao call to get single product data");
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));
        log.info("Completed dao call to get single product data");
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> getAllProducts(Integer pageNumber, Integer PageSize, String sortBy, String sortDir) {
        log.info("Intiating dao call to get all products data");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, PageSize, sort);
        Page<Product> page = this.productRepo.findAll(pageable);
        log.info("Completed dao call to get all products data");
        return Helper.getPageableResponse(page, ProductDto.class);
    }


    @Override
    public void deleteProduct(Integer productId) {
        log.info("Intiating dao call to delete product data");
        Product product = this.productRepo.findById(productId).orElseThrow(() -> new ResourceNotFoundException(AppConstants.PRODUCT_NOT_FOUND));
        product.setIsactive(AppConstants.NO);
        log.info("Completed dao call to delete product data");
        this.productRepo.delete(product);
    }

    @Override
    public PageableResponse<ProductDto> getAllLive(Integer pageNumber, Integer PageSize, String sortBy, String sortDir) {
        log.info("Intiating dao call to getAllLive products data");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, PageSize, sort);
        Page<Product> page = this.productRepo.findByLiveTrue(pageable);
        log.info("Completed dao call to getAllLive products data");
        return Helper.getPageableResponse(page, ProductDto.class);

    }

    @Override
    public PageableResponse<ProductDto> getAll(Integer pageNumber, Integer PageSize, String sortBy, String sortDir) {
        log.info("Intiating dao call to get all products data");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, PageSize, sort);
        Page<Product> page = this.productRepo.findAll(pageable);
        log.info("Completed dao call to get all products data");
        return Helper.getPageableResponse(page, ProductDto.class);
    }

    @Override
    public PageableResponse<ProductDto> searchBytitle(String title, Integer pageNumber, Integer PageSize, String sortBy, String sortDir) {
        log.info("Intiating dao call to search single product data");
        Sort sort = (sortDir.equalsIgnoreCase("desc")) ? (Sort.by(sortBy).descending()) : (Sort.by(sortBy).ascending());
        Pageable pageable = PageRequest.of(pageNumber, PageSize, sort);
        Page<Product> page = this.productRepo.findByTitleContaining(title, pageable);
        log.info("Completed dao call to search single product data");
        return Helper.getPageableResponse(page, ProductDto.class);
    }

}
