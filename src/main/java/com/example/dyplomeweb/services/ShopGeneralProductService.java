package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ProductDTO;
import com.example.dyplomeweb.dto.ShopGeneralProductDTO;
import com.example.dyplomeweb.dto.ShopProductDTO;
import com.example.dyplomeweb.entity.Product;
import com.example.dyplomeweb.entity.ShopGeneralProduct;
import com.example.dyplomeweb.entity.ShopProduct;
import com.example.dyplomeweb.repository.ShopGeneralProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ShopGeneralProductService implements BaseService<ShopGeneralProductDTO, ShopGeneralProduct>{
    private final ShopProductService shopProductService;
    private final ProductService productService;
    private ShopGeneralProductRepository shopGeneralProductRepository;

    @Override
    public List<ShopGeneralProductDTO> getAll() {
        return List.of();
    }

    @Override
    public Optional<ShopGeneralProductDTO> getById(Integer id) {
        return Optional.empty();
    }

    @Override
    public ShopGeneralProductDTO save(ShopGeneralProductDTO shopGeneralProductDTO) {
        return null;
    }

    public ShopGeneralProductDTO save(ShopProductDTO shopProductDTO, ProductDTO productDTO) {
        ShopGeneralProduct shopGeneralProduct = new ShopGeneralProduct();
        ShopProduct shopProduct = shopProductService.convertToEntity(shopProductDTO);
        Product product = productService.convertToEntity(productDTO);
        shopGeneralProduct.setShopProduct(shopProduct);
        shopGeneralProduct.setGeneralProduct(product);
        ShopGeneralProduct shopGeneralProductSaved = shopGeneralProductRepository.save(shopGeneralProduct);
        return convertToDTO(shopGeneralProductSaved);
    }

    public void save(Integer shopProductDTO, Integer productDTO) {
        shopGeneralProductRepository.save(shopProductDTO, productDTO);
    }

    @Override
    public ShopGeneralProductDTO update(Integer id, ShopGeneralProductDTO shopGeneralProductDTO) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ShopGeneralProductDTO convertToDTO(ShopGeneralProduct shopGeneralProduct) {
        return null;
    }

    @Override
    public ShopGeneralProduct convertToEntity(ShopGeneralProductDTO shopGeneralProductDTO) {
        return null;
    }
}
