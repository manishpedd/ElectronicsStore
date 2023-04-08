package com.electronics.store.repository;

import com.electronics.store.dtos.PageableResponse;
import com.electronics.store.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Page<Product> findByTitleContaining (String title, Pageable pageable);

    Page<Product> findByLiveTrue(Pageable pageable);

}
