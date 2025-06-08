package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ProductDTO;
import com.example.dyplomeweb.dto.ProductPriceDTO;
import com.example.dyplomeweb.entity.ProductPrice;
import com.example.dyplomeweb.repository.ProductPriceRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductPriceService implements BaseService<ProductPriceDTO, ProductPrice> {

    private final ProductPriceRepository productPriceRepository;
    private final ProductService productService;

    public ProductPriceService(ProductPriceRepository productPriceRepository, ProductService productService) {
        this.productPriceRepository = productPriceRepository;
        this.productService = productService;
    }

    @Override
    public List<ProductPriceDTO> getAll() {
        return productPriceRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public Optional<ProductPriceDTO> getById(Integer id) {
        return productPriceRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ProductPriceDTO save(ProductPriceDTO productPriceDTO) {
        ProductPrice productPrice = new ProductPrice();
        ProductPrice savedProductPrice = productPriceRepository.save(productPrice);
        return convertToDTO(savedProductPrice);
    }

    @Override
    public ProductPriceDTO update(Integer id, ProductPriceDTO productPriceDTO) {
        ProductPrice productPrice = productPriceRepository.findById(id).orElseThrow();
        productPrice.setProduct(productPriceDTO.product());
        productPrice.setPrice(productPriceDTO.price());
        productPrice.setOldPrice(productPriceDTO.oldPrice());
        productPrice.setShopId(productPriceDTO.shopId());
        productPrice.setIsAvailable(productPriceDTO.isAvailable());
        productPrice.setUpdatedAt(productPriceDTO.updated_at());
        ProductPrice updatedProductPrice = productPriceRepository.save(productPrice);
        return convertToDTO(updatedProductPrice);
    }

    @Override
    public void delete(Integer id) {
        productPriceRepository.deleteById(id);
    }

    public List<ProductPriceDTO> getProductPricesByProductId(Integer productId) {
        return productPriceRepository.findAllByProductId(productId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public boolean getProductPricesIfPresentIn3ShopsByProductId(Integer productId) {
        List<ProductPrice> result = productPriceRepository.findAllByProductId(productId);
        result = result.stream()
                .filter(productPrice ->
                        productPrice.getIsAvailable()
                                .equals(Boolean.TRUE))
                .toList();
        if(result.size() == 3){
            return true;
        }
        return false;
    }

    public int howManyProductPricesPresentByProductId(Integer productId) {
        List<ProductPrice> result = productPriceRepository.findAllByProductId(productId);
        result = result.stream()
                .filter(productPrice ->
                        productPrice.getIsAvailable()
                                .equals(Boolean.TRUE))
                .toList();
        return result.size();
    }

    public Map<Integer, String> getGapOfPrices(){
        List<ProductDTO> allProducts = productService.getAll();
        Map<Integer, String> map = new HashMap<>();

        allProducts.forEach(productDTO -> {
            Integer id = productDTO.id();
            List<ProductPrice> allByProductId = productPriceRepository.findAllByProductId(id);
            if(!allByProductId.isEmpty()){
                Float max = allByProductId.stream().map(ProductPrice::getPrice).filter(Objects::nonNull).max(Float::compareTo).orElse(null);
                Float min = allByProductId.stream().map(ProductPrice::getPrice).filter(Objects::nonNull).min(Float::compareTo).orElse(null);
                String gap = getGapString(max, min);

                map.put(id, gap);
            }
        });
        return map;
    }

    private String getGapString(Float max, Float min) {
        String gap;
        if(max != null && min != null){
            if(max.equals(min)){
                gap = max + " грн";
            }else {
                gap = min + "-" + max + " грн";
            }
        }else {
            if(max == null && min == null){
                gap = "Немає пропозицій";
            }else if(max == null){
                gap = max + " грн";
            }else {
                gap = min + " грн";
            }
        }
        return gap;
    }

    @Override
    public ProductPriceDTO convertToDTO(ProductPrice productPrice) {
        return ProductPriceDTO.builder()
                .id(productPrice.getId())
                .price(productPrice.getPrice())
                .oldPrice(productPrice.getOldPrice())
                .isAvailable(productPrice.getIsAvailable())
                .shopId(productPrice.getShopId())
                .updated_at(productPrice.getUpdatedAt())
                .product(productPrice.getProduct())
                .build();
    }

    @Override
    public ProductPrice convertToEntity(ProductPriceDTO productPriceDTO) {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setProduct(productPriceDTO.product());
        productPrice.setPrice(productPriceDTO.price());
        productPrice.setOldPrice(productPriceDTO.oldPrice());
        productPrice.setShopId(productPriceDTO.shopId());
        productPrice.setIsAvailable(productPriceDTO.isAvailable());
        productPrice.setUpdatedAt(productPriceDTO.updated_at());
        return productPrice;
    }
}
