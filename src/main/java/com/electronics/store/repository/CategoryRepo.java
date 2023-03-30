package com.electronics.store.repository;

import com.electronics.store.entities.Category;
import com.electronics.store.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

    List<Category> findByTitleContaining(String keywords);
}
