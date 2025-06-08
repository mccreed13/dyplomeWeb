package com.example.dyplomeweb.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ProductDTO(Integer id, String name, Integer category, Integer units, Float counts, List<ShopProductDTO> shopProducts) {}
