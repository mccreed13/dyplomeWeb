package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ReceiptProductDTO;
import com.example.dyplomeweb.entity.ReceiptProduct;
import com.example.dyplomeweb.repository.ReceiptProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ReceiptProductService implements BaseService<ReceiptProductDTO, ReceiptProduct> {
    private final ReceiptProductRepository receiptProductRepository;
    private final ReceiptService receiptService;

    @Override
    public List<ReceiptProductDTO> getAll() {
        return receiptProductRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReceiptProductDTO> getById(Integer id) {
        return receiptProductRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ReceiptProductDTO save(ReceiptProductDTO productDTO) {
        ReceiptProduct receiptProduct = convertToEntity(productDTO);
        ReceiptProduct savedReceiptProduct = receiptProductRepository.save(receiptProduct);
        return convertToDTO(savedReceiptProduct);
    }

    @Override
    public ReceiptProductDTO update(Integer id, ReceiptProductDTO productDTO) {
        ReceiptProduct receiptProduct = receiptProductRepository.findById(id).orElseThrow();
        receiptProduct.setIngredientName(productDTO.ingredientName());
//        receiptProduct.setReceipt(receiptService.convertToEntity(productDTO.receipt()));
        receiptProduct.setReceiptId(productDTO.receipt().id());
        receiptProduct.setCategory_id(productDTO.category_id());
        ReceiptProduct updatedProduct = receiptProductRepository.save(receiptProduct);
        return convertToDTO(updatedProduct);
    }

    @Override
    public void delete(Integer id) {
        receiptProductRepository.deleteById(id);
    }

    public Set<ReceiptProductDTO> getAllReceiptProductByReceiptId(Integer receiptId) {
        return receiptProductRepository.findAllReceiptProductByReceiptId(receiptId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public ReceiptProductDTO convertToDTO(ReceiptProduct receipt) {
        return ReceiptProductDTO.builder()
                .id(receipt.getId())
                .receipt(receiptService.getById(receipt.getReceiptId()).get())
                .category_id(receipt.getCategory_id())
                .ingredientName(receipt.getIngredientName())
                .build();
    }

    @Override
    public ReceiptProduct convertToEntity(ReceiptProductDTO receiptProductDTO) {
        ReceiptProduct receiptProduct = new ReceiptProduct();
        receiptProduct.setReceiptId(receiptProductDTO.receipt().id());
        receiptProduct.setCategory_id(receiptProductDTO.category_id());
        receiptProduct.setIngredientName(receiptProductDTO.ingredientName());
        return receiptProduct;
    }
}
