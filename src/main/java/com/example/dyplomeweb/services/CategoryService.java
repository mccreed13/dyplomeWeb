package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.CategoryDTO;
import com.example.dyplomeweb.dto.SubCategoryDTO;
import com.example.dyplomeweb.dto.SubSubCategoryDTO;
import com.example.dyplomeweb.entity.Category;
import com.example.dyplomeweb.entity.SubCategory;
import com.example.dyplomeweb.entity.SubSubCategory;
import com.example.dyplomeweb.repository.CategoryRepository;
import com.example.dyplomeweb.repository.SubCategoryRepository;
import com.example.dyplomeweb.repository.SubSubCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CategoryService implements BaseService<CategoryDTO, Category> {
    private CategoryRepository categoryRepository;
    private SubCategoryRepository subCategoryRepository;
    private SubSubCategoryRepository subSubCategoryRepository;

    @Override
    public List<CategoryDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public String getNameSubSubCategoryById(Integer id){
        Optional<SubSubCategory> byId = subSubCategoryRepository.findById(id);
        if(byId.isPresent()){
            return byId.get().getName();
        }
        return "";
    }

    public Map<CategoryDTO, Map<SubCategoryDTO, List<SubSubCategoryDTO>>> getMapOfCategories(){
        Map<CategoryDTO, Map<SubCategoryDTO, List<SubSubCategoryDTO>>> categoryMap = new HashMap<>();
        List<CategoryDTO> categories = categoryRepository.findAll()
                .stream().map(this::convertToDTO).toList();;
        for (CategoryDTO category : categories) {
            Integer categoryId = category.id();
            List<SubCategoryDTO> subCategoryList = subCategoryRepository.findAllByCategoryId(categoryId)
                    .stream().map(this::convertToDTO).toList();;
            Map<SubCategoryDTO, List<SubSubCategoryDTO>> subCategoryMap = new HashMap<>();
            for (SubCategoryDTO subCategory : subCategoryList) {
                Integer subCategoryId = subCategory.id();
                List<SubSubCategoryDTO> subSubCategoryList = subSubCategoryRepository.findAllBySubCategoryId(subCategoryId)
                        .stream().map(this::convertToDTO).toList();
                subCategoryMap.put(subCategory, subSubCategoryList);
            }
            categoryMap.put(category, subCategoryMap);
        }
        return categoryMap;
    }

    @Override
    public Optional<CategoryDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public CategoryDTO update(Integer id, CategoryDTO categoryDTO) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public CategoryDTO convertToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    @Override
    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.name());
        return category;
    }


    public SubCategoryDTO convertToDTO(SubCategory category) {
        return SubCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .categoryId(category.getCategoryId())
                .build();
    }

    public SubCategory convertToEntity(SubCategoryDTO categoryDTO) {
        SubCategory category = new SubCategory();
        category.setName(categoryDTO.name());
        category.setCategoryId(categoryDTO.categoryId());
        return category;
    }

    public SubSubCategoryDTO convertToDTO(SubSubCategory category) {
        return SubSubCategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .subCategoryId(category.getSubCategoryId())
                .build();
    }

    public SubSubCategory convertToEntity(SubSubCategoryDTO categoryDTO) {
        SubSubCategory category = new SubSubCategory();
        category.setName(categoryDTO.name());
        category.setSubCategoryId(categoryDTO.subCategoryId());
        return category;
    }
}
