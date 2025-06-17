package com.example.dyplomeweb.controllers;

import com.example.dyplomeweb.dto.ProductDTO;
import com.example.dyplomeweb.dto.ShopProductDTO;
import com.example.dyplomeweb.services.*;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private static final Logger log = LogManager.getLogger(AdminController.class);
    private final ProductService productService;
    private final ShopProductService shopProductService;
    private final ShopGeneralProductService shopGeneralProductService;
    private final CategoryService categoryService;
    private final UnitsService unitsService;

    @GetMapping("")
    public String getHome(Model model) {
        return "redirect:/admin/generalProducts";
    }

    @GetMapping("/shopGeneralProducts")
    public String getShopProductsAndGeneralProducts(Model model) {
        model.addAttribute("shopProducts", shopProductService.getAllWhichNotMappedByGeneralProduct());
        model.addAttribute("generalProducts", productService.getAll());
        model.addAttribute("module", "shopGeneralProducts");
        return "admin/shopProductsAndGeneralProducts";
    }

    @GetMapping("/generalProducts")
    public String getGeneralProducts(Model model) {
        model.addAttribute("generalProducts", productService.getAll());
        model.addAttribute("units", unitsService.getAllInMap());
        model.addAttribute("module", "generalProducts");
        return "admin/generalProducts";
    }

    @GetMapping("/generalProducts/{id}")
    public String getGeneralProductsById(Model model, @PathVariable Integer id) {
        Optional<ProductDTO> optional = productService.getById(id);
        if (optional.isPresent()) {
            ProductDTO productDTO = optional.get();
            model.addAttribute("product", productDTO);
            String subsubCategoryName = "Без категорії";
            if(productDTO.category() != null){
                subsubCategoryName = categoryService.getNameSubSubCategoryById(productDTO.category());
            }
            model.addAttribute("categoryName", subsubCategoryName);
            for (var str:categoryService.getMapOfCategories().entrySet()){
                System.out.println(str.getKey().name());
                for (var str2 : str.getValue().entrySet()){
                    System.out.println("- "+str2.getKey().name());
                    for (var str3 : str2.getValue()){
                        System.out.println("-- " + str3.name());
                    }
                }
            }
            model.addAttribute("categories", categoryService.getMapOfCategories());
            model.addAttribute("units", unitsService.getAll());
            String unitsName = "Без одиниць";
            if(productDTO.units() != null) {
                unitsName = unitsService.getById(productDTO.units()).get().name();
            }
            model.addAttribute("unitsName", unitsName);
            return "admin/generalProductsById";
        }else {
            log.error("Product with id {} not found", id);
            return "redirect:/admin/generalProducts";
        }
    }

    @PostMapping("/generalProducts/{id}")
    public String updateGeneralProductsById(@PathVariable Integer id, ProductDTO productDTO) {
        productService.update(id, productDTO);
        return "redirect:/admin/generalProducts/"+id;
    }

    @PostMapping(params="action=createNewGeneral")
    public String createNewGeneral(@RequestParam(name = "shopPr" , required = false) List<Integer> shopProductIDList) {
        for (Integer shopProductID : shopProductIDList) {
            Optional<ShopProductDTO> byId = shopProductService.getById(shopProductID);
            if (byId.isPresent()) {
                ShopProductDTO shopProductDTO = byId.get();
                ProductDTO productDTO = ProductDTO.builder()
                        .name(shopProductDTO.name())
                        .build();
                ProductDTO productDTOSaved = productService.save(productDTO);
                System.out.println(productDTOSaved);
                shopGeneralProductService.save(shopProductDTO.id(), productDTOSaved.id());
            }else {
                log.error("No product found with id {}", shopProductID);
            }
        }
        return "redirect:/admin/generalProducts";
    }

    @PostMapping(params="action=mapShopGeneral")
    public String map(@RequestParam(name = "shopPr" , required = false) List<Integer> shopProductIDList,
                      @RequestParam(name = "generalPr") Integer generalProductID) {
        Optional<ProductDTO> generalProductById = productService.getById(generalProductID);
        if(generalProductById.isPresent()){
            for (Integer shopProductID : shopProductIDList) {
                Optional<ShopProductDTO> shopProductById = shopProductService.getById(shopProductID);
                if (shopProductById.isPresent()) {
                    shopGeneralProductService.save(shopProductID, generalProductID);
                    log.info("Shop general product saved with productId {} and id {}", generalProductID, shopProductID);
                }else {
                    log.error("Shop product not found by id {}", shopProductID);
                }
            }
        }else {
            log.error("General product not found by id {}", generalProductID);
        }
        return "redirect:/admin/shopGeneralProducts";
    }


}
