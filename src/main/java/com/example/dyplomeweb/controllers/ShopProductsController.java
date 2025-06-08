package com.example.dyplomeweb.controllers;

import com.example.dyplomeweb.dto.ShopProductDTO;
import com.example.dyplomeweb.services.ShopProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shop_products")
@AllArgsConstructor
public class ShopProductsController {

    private final ShopProductService shopProductService;

    @GetMapping()
    public List<ShopProductDTO> getShopProducts() {
        return shopProductService.getAll();
    }
}
