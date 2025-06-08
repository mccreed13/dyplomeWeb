package com.example.dyplomeweb.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="receipt_product")
public class ReceiptProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @JoinColumn(name = "receipt_id")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Receipt receipt;
    @Column(name = "receipt_id")
    private Integer receiptId;
    @Column(name = "ingredient_name")
    private String ingredientName;
    @Column(name = "category_of_product")
    private Integer category_id;
}
