package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ProductDTO;
import com.example.dyplomeweb.dto.ProductPriceDTO;
import com.example.dyplomeweb.dto.ReceiptProductDTO;
import com.example.dyplomeweb.entity.Product;
import com.example.dyplomeweb.itemsWithPrice.ATBItem;
import com.example.dyplomeweb.itemsWithPrice.ItemWithPrice;
import com.example.dyplomeweb.itemsWithPrice.MetroItem;
import com.example.dyplomeweb.itemsWithPrice.SilpoItem;
import com.example.dyplomeweb.repository.ProductPriceRepository;
import com.example.dyplomeweb.response.ProductWithPriceByBrand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductWithPriceByBrandService {
    private ProductPriceService productPriceService;
    private final ProductService productService;
    private final ProductPriceRepository productPriceRepository;
//    private final ItemWithPriceRepository itemWithPriceRepository;


//    public ProductWithPriceByBrand getResponse(Map<String, ATBItem> atb, Map<String, SilpoItem> silpo, Map<String, MetroItem> metro, Set<ProductDTO> productsByCategory) {
//            Set<ProductDTO> productsByCategory = productService.getProductsByCategory(receiptProduct.category_id());
//            Map<Integer, List<List<Product>>> productsByCount = new HashMap<>();
//            productsByCount.put(1, new ArrayList<>());
//            productsByCount.put(2, new ArrayList<>());
//            boolean nextCategory = false;
//            for (ProductDTO product : productsByCategory) {
//                List<ProductPriceDTO> productPricesByProductId = productPriceService.getProductPricesByProductId(product.id());
//                if(productPricesByProductId.size() == 3){
//                    List<ItemWithPrice> allByGeneralProductId = itemWithPriceRepository.findAllByGeneralProductId(product.id());
//                    boolean isAtbInsert = false;
//                    boolean isMetroInsert = false;
//                    boolean isSilpoInsert = false;
//                    for (ItemWithPrice item : allByGeneralProductId){
//                        switch (item.getBrand()){
//                            case ATB :
//                                atb.put(receiptProduct.ingredientName(), (ATBItem) item);
//                                isAtbInsert = true;
//                                break;
//                            case METRO :
//                                metro.put(receiptProduct.ingredientName(), (MetroItem) item);
//                                isMetroInsert = true;
//                                break;
//                            case SILPO :
//                                silpo.put(receiptProduct.ingredientName(), (SilpoItem) item);
//                                isSilpoInsert = true;
//                                break;
//                        }
//                        if (isAtbInsert && isMetroInsert && isSilpoInsert){
//                            nextCategory = true;
//                        }else {
//                            atb.put(receiptProduct.ingredientName(), null);
//                            metro.put(receiptProduct.ingredientName(), null);
//                            silpo.put(receiptProduct.ingredientName(), null);
//                        }
//                    }
//                }else {
//                    productsByCount.get(productPricesByProductId.size()).add(productPricesByProductId.stream().map(ProductPriceDTO::product).collect(Collectors.toList()));
//                }
//                if(nextCategory){
//                    break;
//                }
//            }
//            if(!nextCategory){
//                List<List<Product>> listsWithTwo = productsByCount.get(0);
//                if(listsWithTwo.size() > 1){
//                    List<Product> priceDTOS = listsWithTwo.get(0);
//                    Brands notFoundBrand = null;
//                    boolean isAtbInsert = false;
//                    boolean isMetroInsert = false;
//                    boolean isSilpoInsert = false;
//                    for (Product item : priceDTOS) {
//                        List<ItemWithPrice> generalProduct = itemWithPriceRepository.findAllByGeneralProductId(item.getId());
//                        for (ItemWithPrice itemWithPrice : generalProduct) {
//                            switch (itemWithPrice.getBrand()){
//                                case ATB:
//                                    atb.put(receiptProduct.ingredientName(), (ATBItem) itemWithPrice);
//                                    isAtbInsert = true;
//                                    break;
//                                case METRO:
//                                    metro.put(receiptProduct.ingredientName(), (MetroItem) itemWithPrice);
//                                    isMetroInsert = true;
//                                    break;
//                                case SILPO:
//                                    silpo.put(receiptProduct.ingredientName(), (SilpoItem) itemWithPrice);
//                                    isSilpoInsert = true;
//                                    break;
//                            }
//                        }
//                        if (isMetroInsert && isSilpoInsert){
//                            notFoundBrand = Brands.ATB;
//                        }else if (isAtbInsert && isSilpoInsert){
//                            notFoundBrand = Brands.METRO;
//                        }else if (isAtbInsert && isMetroInsert){
//                            notFoundBrand = Brands.SILPO;
//                        }
//                        if(notFoundBrand == null){
//                            throw new IllegalArgumentException();
//                        }
//                        for (int i = 1; i < listsWithTwo.size() && !nextCategory; i++) {
//                            List<Product> products = listsWithTwo.get(i);
//                            for (int j = 0; j < products.size() && !nextCategory; j++) {
//                                Product product = products.get(j);
//                                if(itemWithPriceRepository.existsItemWithPriceByBrandAndGeneralProductId(notFoundBrand.name(), product.getId())){
//                                    ItemWithPrice itemFounded = itemWithPriceRepository.findAllByGeneralProductId(product.getId()).get(0);
//                                    switch (itemFounded.getBrand()){
//                                        case ATB:
//                                            atb.put(receiptProduct.ingredientName(), (ATBItem) itemFounded);
//                                            isAtbInsert = true;
//                                            break;
//                                        case METRO:
//                                            metro.put(receiptProduct.ingredientName(), (MetroItem) itemFounded);
//                                            isMetroInsert = true;
//                                            break;
//                                        case SILPO:
//                                            silpo.put(receiptProduct.ingredientName(), (SilpoItem) itemFounded);
//                                            isSilpoInsert = true;
//                                            break;
//                                    }
//                                    nextCategory = true;
//                                }
//                            }
//                        }
//
//                    }
//                }else {
//
//                }
//            }
//        }
//        return new ProductWithPriceByBrand(atb, silpo, metro);
//    }

    public ProductWithPriceByBrand getUpdatedResponse(Set<ReceiptProductDTO> receiptProducts) {
        Map<String, ATBItem> atb = new HashMap<>();
        Map<String, SilpoItem> silpo = new HashMap<>();
        Map<String, MetroItem> metro = new HashMap<>();
        for (ReceiptProductDTO receiptProduct : receiptProducts) {
            Set<ProductDTO> productsByCategory = productService.getProductsByCategory(receiptProduct.category_id());
            Map<Integer, List<List<Product>>> productsByCount = new HashMap<>();
            productsByCount.put(1, new ArrayList<>());
            productsByCount.put(2, new ArrayList<>());
            boolean nextCategory = false;
            for (ProductDTO productDTO : productsByCategory) {
                List<ProductPriceDTO> productPriceDTOsList = productPriceService.getProductPricesByProductId(productDTO.id())
                        .stream().filter(ProductPriceDTO::isAvailable).toList();
                if (productPriceDTOsList.size() == 3) {
                    List<ItemWithPrice> allByGeneralProductId =
                            productPriceRepository.findAllItemWithPricesByGeneralProductId(productDTO.id())
                                    .stream().filter(ItemWithPrice::getIsAvailable).collect(Collectors.toList());
                    nextCategory = insert3Products(allByGeneralProductId, receiptProduct.ingredientName(), atb, silpo, metro);
                } else {
                    if (!productPriceDTOsList.isEmpty()) {
                        productsByCount.get(productPriceDTOsList.size()).add(
                                productPriceDTOsList.stream()
                                        .filter(Objects::nonNull)
                                        .map(ProductPriceDTO::product)
                                        .collect(Collectors.toList()));
                    }
                }
                if (nextCategory) {
                    break;
                }
            }
            if (nextCategory) {
                break;
            } else {
                insert2Products(productsByCount, receiptProduct.ingredientName(), atb, silpo, metro);
            }
        }
        return new ProductWithPriceByBrand(null, atb, silpo, metro);
    }

    private boolean insert3Products(List<ItemWithPrice> allByGeneralProductId, String ingredientName,
                                    Map<String, ATBItem> atb, Map<String, SilpoItem> silpo, Map<String, MetroItem> metro) {
        boolean isAtbInsert = false;
        boolean isMetroInsert = false;
        boolean isSilpoInsert = false;
        for (ItemWithPrice item : allByGeneralProductId) {
            switch (item.getBrand()) {
                case ATB:
                    atb.put(ingredientName, (ATBItem) item);
                    isAtbInsert = true;
                    break;
                case METRO:
                    metro.put(ingredientName, (MetroItem) item);
                    isMetroInsert = true;
                    break;
                case SILPO:
                    silpo.put(ingredientName, (SilpoItem) item);
                    isSilpoInsert = true;
                    break;
            }
        }
        if (isAtbInsert && isMetroInsert && isSilpoInsert) {
            return true;
        } else {
            atb.put(ingredientName, null);
            metro.put(ingredientName, null);
            silpo.put(ingredientName, null);
            return false;
        }
    }

    private void insert2Products(Map<Integer, List<List<Product>>> productsByCount, String ingredientName,
                                 Map<String, ATBItem> atb, Map<String, SilpoItem> silpo, Map<String, MetroItem> metro) {
        boolean isAtbInsert = false;
        boolean isMetroInsert = false;
        boolean isSilpoInsert = false;

        List<List<Product>> lists = productsByCount.get(2);
        for (List<Product> list : lists) {
            for (Product product : list) {
                List<ItemWithPrice> allByGeneralProductId =
                        productPriceRepository.findAllItemWithPricesByGeneralProductId(product.getId())
                                .stream().filter(ItemWithPrice::getIsAvailable).toList();
                for (ItemWithPrice item : allByGeneralProductId) {
                    switch (item.getBrand()) {
                        case ATB:
                            if (!isAtbInsert){
                                atb.put(ingredientName, ATBItem.valueOf(item));
                                isAtbInsert = true;
                            }
                            break;
                        case METRO:
                            if(!isMetroInsert){
                                metro.put(ingredientName, MetroItem.valueOf(item));
                                isMetroInsert = true;
                            }
                            break;
                        case SILPO:
                            if(!isSilpoInsert){
                                silpo.put(ingredientName,  SilpoItem.valueOf(item));
                                isSilpoInsert = true;
                            }
                    }
                    if (isAtbInsert && isMetroInsert && isSilpoInsert) {
                        return;
                    }
                }
            }
        }

        lists = productsByCount.get(1);
        for (List<Product> list : lists) {
            for (Product product : list) {
                List<ItemWithPrice> allByGeneralProductId =
                        productPriceRepository.findAllItemWithPricesByGeneralProductId(product.getId())
                                .stream().filter(ItemWithPrice::getIsAvailable).toList();
                for (ItemWithPrice item : allByGeneralProductId) {
                    switch (item.getBrand()) {
                        case ATB:
                            if (!isAtbInsert){
                                atb.put(ingredientName, ATBItem.valueOf(item));
                                isAtbInsert = true;
                            }
                            break;
                        case METRO:
                            if(!isMetroInsert){
                                metro.put(ingredientName, MetroItem.valueOf(item));
                                isMetroInsert = true;
                            }
                            break;
                        case SILPO:
                            if(!isSilpoInsert){
                                silpo.put(ingredientName, SilpoItem.valueOf(item));
                                isSilpoInsert = true;
                            }
                    }
                    if (isAtbInsert && isMetroInsert && isSilpoInsert) {
                        return;
                    }
                }
            }
        }
    }

}
