package com.example.dyplomeweb.entity;

import com.example.dyplomeweb.enums.Brands;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="shop_products")
public class ShopProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "shopbrand")
    @Enumerated(EnumType.STRING)
    private Brands brand;
    private String url;
}
