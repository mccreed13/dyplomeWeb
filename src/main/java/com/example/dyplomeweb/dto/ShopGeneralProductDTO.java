package com.example.dyplomeweb.dto;

import com.example.dyplomeweb.entity.Product;
import com.example.dyplomeweb.entity.ShopProduct;
import lombok.Builder;

import java.util.List;

@Builder
public record ShopGeneralProductDTO(
        Integer id,
        List<ShopProduct> shopProduct,
        Product generalProduct
) {}
