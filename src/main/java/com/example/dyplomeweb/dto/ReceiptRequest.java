package com.example.dyplomeweb.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record ReceiptRequest(String author, String name, String description, List<ReceiptProductDTO> ingredients) {
}
