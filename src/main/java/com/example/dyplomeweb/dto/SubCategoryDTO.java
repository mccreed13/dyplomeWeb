package com.example.dyplomeweb.dto;

import lombok.Builder;

@Builder
public record SubCategoryDTO (Integer id, String name, Integer categoryId) {}
