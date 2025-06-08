package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ShopProductDTO;
import com.example.dyplomeweb.entity.ShopProduct;
import com.example.dyplomeweb.repository.ShopProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShopProductService implements BaseService<ShopProductDTO, ShopProduct> {

    private final ShopProductRepository shopProductRepository;

    @Override
    public List<ShopProductDTO> getAll() {
        return shopProductRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ShopProductDTO> getAllWhichNotMappedByGeneralProduct() {
        return shopProductRepository.findAllWhichNotPresentedInShopGeneralProducts()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ShopProductDTO> getById(Integer id) {
        return shopProductRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ShopProductDTO save(ShopProductDTO shopProductDTO) {
        return null;
    }

    @Override
    public ShopProductDTO update(Integer id, ShopProductDTO shopProductDTO) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public ShopProductDTO convertToDTO(ShopProduct shopProduct) {
        return ShopProductDTO.builder()
                .id(shopProduct.getId())
                .name(shopProduct.getName())
                .brand(shopProduct.getBrand())
                .url(shopProduct.getUrl())
                .build();
    }

    @Override
    public ShopProduct convertToEntity(ShopProductDTO shopProductDTO) {
        ShopProduct shopProduct = new ShopProduct();
        shopProduct.setName(shopProductDTO.name());
        shopProduct.setBrand(shopProductDTO.brand());
        shopProduct.setUrl(shopProductDTO.url());
        return shopProduct;
    }
}
