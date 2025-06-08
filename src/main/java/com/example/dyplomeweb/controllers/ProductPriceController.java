package com.example.dyplomeweb.controllers;

import com.example.dyplomeweb.dto.ProductPriceDTO;
import com.example.dyplomeweb.services.ProductPriceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product_price")
@AllArgsConstructor
public class ProductPriceController {
    private final ProductPriceService productPriceService;
    @GetMapping()
    public List<ProductPriceDTO> getReceipts(){
        return productPriceService.getAll();
    }
}
