package com.example.dyplomeweb.dto;

import com.example.dyplomeweb.enums.Brands;
import lombok.*;

@Builder
public record ShopProductDTO (
        Integer id,
        String name,
        Brands brand,
        String url
){}
