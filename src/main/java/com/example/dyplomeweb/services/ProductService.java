package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ProductDTO;
import com.example.dyplomeweb.entity.Product;
import com.example.dyplomeweb.repository.ProductsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService implements BaseService<ProductDTO, Product> {
    private final ProductsRepository productsRepository;
    private final ShopProductService shopProductService;

    @Override
    public List<ProductDTO> getAll() {
        return productsRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getById(Integer id) {
        return productsRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productsRepository.save(product);
        return convertToDTO(savedProduct);
    }

    @Override
    public ProductDTO update(Integer id, ProductDTO productDTO) {
        Product product = productsRepository.findById(id).orElseThrow();
        product.setName(productDTO.name());
        product.setUnits(productDTO.units());
        product.setCounts(productDTO.counts());
        product.setCategory(productDTO.category());
        Product updatedProduct = productsRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    @Override
    public void delete(Integer id) {
        productsRepository.deleteById(id);
    }

    public Set<ProductDTO> getProductsByCategory(Integer category_id) {
        return productsRepository.findAllProductsByCategory(category_id).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        if(product.getShopProducts() != null) {
            return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .counts(product.getCounts())
                .units(product.getUnits())
                .shopProducts(product.getShopProducts().stream().map(shopProductService::convertToDTO).toList())
                .build();
        }
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .counts(product.getCounts())
                .units(product.getUnits())
                .build();
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.name());
        product.setCategory(productDTO.category());
        product.setCounts(productDTO.counts());
        product.setUnits(productDTO.units());
        if(productDTO.shopProducts() != null){
            product.setShopProducts(productDTO.shopProducts().stream().map(shopProductService::convertToEntity).toList());
        }
        return product;
    }
}
