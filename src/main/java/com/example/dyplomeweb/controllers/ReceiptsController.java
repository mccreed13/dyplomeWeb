package com.example.dyplomeweb.controllers;

import com.example.dyplomeweb.dto.ReceiptDTO;
import com.example.dyplomeweb.dto.ReceiptProductDTO;

import com.example.dyplomeweb.dto.ReceiptRequest;
import com.example.dyplomeweb.response.ProductWithPriceByBrand;
import com.example.dyplomeweb.services.CategoryService;
import com.example.dyplomeweb.services.ProductWithPriceByBrandService;
import com.example.dyplomeweb.services.ReceiptProductService;
import com.example.dyplomeweb.services.ReceiptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/receipts")
@AllArgsConstructor
public class ReceiptsController {
    private final ReceiptService receiptService;
    private final ReceiptProductService receiptProductService;
    private final ProductWithPriceByBrandService productWithPriceByBrandService;
    private final CategoryService categoryService;

    @GetMapping()
    public String getReceipts(Model model) {
        model.addAttribute("receipts", receiptService.getAll());
        model.addAttribute("module", "receipts");

        return "receipts";
    }

    @GetMapping("/create")
    public String getCreateReceipt(Model model) {
        ArrayList<ReceiptProductDTO> receiptProductDTOS = new ArrayList<>();
        model.addAttribute("module", "receiptForm");
        model.addAttribute("receipt", ReceiptRequest.builder().ingredients(receiptProductDTOS).build());
        model.addAttribute("categories", categoryService.getMapOfCategories());
        return "receiptsCreate";
    }

    @PostMapping("/create")
    public String createReceipt(Model model, @ModelAttribute ReceiptRequest receiptRequest) {
        System.out.println(receiptRequest);
        ReceiptDTO receiptDTO = ReceiptDTO.builder()
                .author(receiptRequest.author())
                .name(receiptRequest.name())
                .description(receiptRequest.description())
                .build();
        ReceiptDTO receiptDTOsaved = receiptService.save(receiptDTO);
        receiptRequest.ingredients().forEach(ingredient -> {
            ReceiptProductDTO dto = ReceiptProductDTO.builder()
                    .receipt(receiptDTOsaved)
                    .ingredientName(ingredient.ingredientName())
                    .category_id(ingredient.category_id())
                    .build();
            if(ingredient.category_id() != 0){
                receiptProductService.save(dto);
            }
        });

        return getCreateReceipt(model);
    }

    @GetMapping("/{id}")
    public String getReceiptById(@PathVariable Integer id, Model model) {
        Optional<ReceiptDTO> receipt = receiptService.getById(id);
        if (receipt.isPresent()) {
            Set<ReceiptProductDTO> receiptProductDTOS = receiptProductService.getAllReceiptProductByReceiptId(id);
            System.out.println(receipt.get().description());
            ProductWithPriceByBrand updatedResponse = productWithPriceByBrandService.getUpdatedResponse(receiptProductDTOS);
            updatedResponse.setReceiptDTO(receipt.get());
            model.addAttribute("updatedResponse", updatedResponse);
            model.addAttribute("ingredients", receiptProductDTOS.stream().map(ReceiptProductDTO::ingredientName).toList());
            model.addAttribute("currentDate", LocalDate.now().toString());
        }
        return "receiptById";
    }


}
