package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.ReceiptProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptProductRepository extends JpaRepository<ReceiptProduct, Integer> {
    List<ReceiptProduct> findAllReceiptProductByReceiptId(Integer receipt_id);
}
