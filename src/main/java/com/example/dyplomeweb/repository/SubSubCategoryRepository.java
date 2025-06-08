package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.SubSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubSubCategoryRepository extends JpaRepository<SubSubCategory, Integer> {
    List<SubSubCategory> findAllBySubCategoryId(Integer subCategoryId);
}
