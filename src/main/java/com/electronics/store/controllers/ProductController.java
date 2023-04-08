package com.electronics.store.controllers;

import com.electronics.store.config.AppConstants;
import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.dtos.ProductDto;
import com.electronics.store.payloads.ApiResponse;
import com.electronics.store.productservice.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;
    //Create

    @PostMapping("/createproduct")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        log.info("Initiated request to create product");
        ProductDto product = this.productService.createProduct(productDto);
        log.info("Completed request to create product");
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getSingleProduct(@PathVariable Integer productId) {
        log.info("Initiated request to get single product");
        ProductDto product = this.productService.getSingleProduct(productId);
        log.info("Completed request to get single product");
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }


    //Update
    @PutMapping("/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto, @PathVariable Integer productId) {
        log.info("Initiated request to update product");
        ProductDto product = this.productService.updateProduct(productDto, productId);
        log.info("Completed request to update product");
        return new ResponseEntity<ProductDto>(product, HttpStatus.OK);
    }

    //Delete
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Integer productId) {
        log.info("Initiated request to delete product");
        this.productService.deleteProduct(productId);
        log.info("Completed request to delete product");
        return new ResponseEntity<ApiResponse>(new ApiResponse(AppConstants.Category_Deleted, true, new Date()), HttpStatus.OK);
    }

    //Get All

    @GetMapping
    public ResponseEntity<PageableResponse<ProductDto>> getAll
            (
            @RequestParam(value="pageNumber", defaultValue ="0",required = false ) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue ="title",required = false ) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir

    ){
        log.info("Initiated request to getall products");
        PageableResponse<ProductDto> pageableResponse = this.productService.getAll(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed request to getall products");
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }


    //Get all Live products
    @GetMapping("/live")
    public ResponseEntity<PageableResponse<ProductDto>> getAllLive(
            @RequestParam(value="pageNumber", defaultValue ="0",required = false ) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue ="title",required = false ) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        log.info("Initiated request to getall live products");
        PageableResponse<ProductDto> allLive = this.productService.getAllLive(pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed request to getall live products");
        return new ResponseEntity<>(allLive,HttpStatus.OK);
    }

    // Search All
    @GetMapping("/search/{query}")
    public ResponseEntity<PageableResponse<ProductDto>> searchproductByTitle(
            @PathVariable String query,
            @RequestParam(value="pageNumber", defaultValue ="0",required = false ) Integer pageNumber,
            @RequestParam(value="pageSize",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value="sortBy", defaultValue ="title",required = false ) String sortBy,
            @RequestParam(value="sortDir",defaultValue = "asc",required = false) String sortDir
    ){
        log.info("Initiated request to search single product");
        PageableResponse<ProductDto> pageableResponse = this.productService.searchBytitle(query, pageNumber, pageSize, sortBy, sortDir);
        log.info("Completed request to search single product");
        return new ResponseEntity<>(pageableResponse,HttpStatus.OK);
    }


}
