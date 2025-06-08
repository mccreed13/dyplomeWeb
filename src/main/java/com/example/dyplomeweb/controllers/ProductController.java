package com.example.dyplomeweb.controllers;

import com.example.dyplomeweb.repository.ProductPriceRepository;
import com.example.dyplomeweb.services.ProductPriceService;
import com.example.dyplomeweb.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductPriceRepository productPriceRepository;
    private final ProductPriceService productPriceService;

    @GetMapping()
    public String getProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        model.addAttribute("module", "products");
        model.addAttribute("prices", productPriceService.getGapOfPrices());
        return "products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getById(id).orElse(null));
        model.addAttribute("prices", productPriceRepository.findAllItemWithPricesByGeneralProductId(id));
        return "productById";
    }
}
