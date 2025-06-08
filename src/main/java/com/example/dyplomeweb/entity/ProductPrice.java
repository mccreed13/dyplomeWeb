package com.example.dyplomeweb.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name="product_price")
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "shop_id")
    private Integer shopId;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;
    private Float price;
    @Column(name = "old_price")
    private Float oldPrice;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "is_available")
    private Boolean isAvailable;
}
