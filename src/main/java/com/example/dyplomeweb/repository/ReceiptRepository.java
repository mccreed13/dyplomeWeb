package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
}