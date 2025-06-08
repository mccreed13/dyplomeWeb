package com.example.dyplomeweb.dto;

import lombok.Builder;

@Builder
public record ReceiptProductDTO(Integer id, ReceiptDTO receipt, String ingredientName, Integer category_id) {}
