package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    List<SubCategory> findAllByCategoryId(Integer categoryId);
}
