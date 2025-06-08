package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductsRepository  extends JpaRepository<Product, Integer> {
    List<Product> findAllProductsByCategory(Integer category);
}
