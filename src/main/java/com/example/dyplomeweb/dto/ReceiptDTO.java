package com.example.dyplomeweb.dto;

import lombok.Builder;

@Builder
public record ReceiptDTO(Integer id, String name, String description, String author) {}
