package com.example.dyplomeweb.dto;

import com.example.dyplomeweb.enums.Brands;

public record ShopDTO(
        Integer id,
        Brands brand,
        String address)
{}
