package com.example.dyplomeweb.dto;

import lombok.Builder;

@Builder
public record SubSubCategoryDTO (Integer id, String name, Integer subCategoryId) {}
