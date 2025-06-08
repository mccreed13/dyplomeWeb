package com.example.dyplomeweb.dto;

import lombok.Builder;

@Builder
public record CategoryDTO(Integer id, String name, Integer subcategory_id) {
}
