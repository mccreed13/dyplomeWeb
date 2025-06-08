package com.example.dyplomeweb.dto;

import com.example.dyplomeweb.entity.Product;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record ProductPriceDTO (
    Integer id,
    Integer shopId,
    Product product,
    Float price,
    Float oldPrice,
    Timestamp updated_at,
    Boolean isAvailable
){}
