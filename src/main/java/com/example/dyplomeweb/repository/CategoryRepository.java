package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
