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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Controller
@RequestMapping("/receipts")
@AllArgsConstructor
public class ReceiptsController {
    private static final Logger log = LoggerFactory.getLogger(ReceiptsController.class);
    private final ReceiptService receiptService;
    private final ReceiptProductService receiptProductService;
    private final ProductWithPriceByBrandService productWithPriceByBrandService;
    private final CategoryService categoryService;
    private static final String UPLOAD_DIRECTORY = "C:\\Users\\alapu\\IdeaProjects\\dyplomeWeb\\receipts";

    @GetMapping()
    public String getReceipts(Model model) {
        model.addAttribute("receipts", receiptService.getAll());
        model.addAttribute("module", "receipts");
        model.addAttribute("receiptsPhotos", receiptService.getAllPhotos());
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
    public String createReceipt(Model model, @ModelAttribute ReceiptRequest receiptRequest,
                                @RequestParam(name = "image" , required = false) MultipartFile photoFile) {
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
        if(photoFile != null) {
            createFile(photoFile, receiptDTOsaved.id().toString()
            );
        }
        return getReceipts(model);
    }

    private void createFile(MultipartFile file, String name){
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, name+".png");
        try {
            File fileIn = fileNameAndPath.toFile();
            file.transferTo(fileIn);
            File fileOut = fileNameAndPath.toFile();
            BufferedImage bufferedImage = ImageIO.read(fileIn);
            ImageIO.write(bufferedImage, "png", fileOut);

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public String getReceiptById(@PathVariable Integer id, Model model) {
        Optional<ReceiptDTO> receipt = receiptService.getById(id);
        if (receipt.isPresent()) {
            Set<ReceiptProductDTO> receiptProductDTOS = receiptProductService.getAllReceiptProductByReceiptId(id);
            ProductWithPriceByBrand updatedResponse = productWithPriceByBrandService.getUpdatedResponse(receiptProductDTOS);
            updatedResponse.setReceiptDTO(receipt.get());
            model.addAttribute("updatedResponse", updatedResponse);
            model.addAttribute("ingredients", receiptProductDTOS.stream().map(ReceiptProductDTO::ingredientName).toList());
            model.addAttribute("currentDate", LocalDate.now().toString());
            System.out.println(receiptProductDTOS.stream().map(ReceiptProductDTO::ingredientName).toList());
        }
        return "receiptById";
    }


}
