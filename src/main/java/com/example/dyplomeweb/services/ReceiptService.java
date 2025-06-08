package com.example.dyplomeweb.services;

import com.example.dyplomeweb.dto.ReceiptDTO;
import com.example.dyplomeweb.entity.Receipt;
import com.example.dyplomeweb.repository.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReceiptService implements BaseService<ReceiptDTO, Receipt> {

    private final ReceiptRepository receiptRepository;

    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    @Override
    public List<ReceiptDTO> getAll() {
        return receiptRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReceiptDTO> getById(Integer id) {
        return receiptRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public ReceiptDTO save(ReceiptDTO receiptDTO) {
        Receipt receipt = convertToEntity(receiptDTO);
        Receipt savedReceipt = receiptRepository.save(receipt);
        return convertToDTO(savedReceipt);
    }

    @Override
    public ReceiptDTO update(Integer id, ReceiptDTO receiptDTO) {
        Receipt product = receiptRepository.findById(id).orElseThrow();
        product.setName(receiptDTO.name());
        product.setDescription(receiptDTO.description());
        product.setAuthor(receiptDTO.author());
        Receipt updatedProduct = receiptRepository.save(product);
        return convertToDTO(updatedProduct);
    }

    @Override
    public void delete(Integer id) {
        receiptRepository.deleteById(id);
    }

    @Override
    public ReceiptDTO convertToDTO(Receipt receipt) {
        return new ReceiptDTO(receipt.getId(), receipt.getName(), receipt.getDescription(), receipt.getAuthor());
    }

    @Override
    public Receipt convertToEntity(ReceiptDTO productDTO) {
        Receipt product = new Receipt();
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setAuthor(productDTO.author());
        return product;
    }
}