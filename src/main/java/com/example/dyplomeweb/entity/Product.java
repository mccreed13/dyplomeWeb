package com.example.dyplomeweb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "general_name")
    private String name;
    private Integer category;
    private Integer units;
    private Float counts;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "shop_general_products",
            joinColumns = @JoinColumn(name = "general_product_id"),
            inverseJoinColumns = @JoinColumn(name = "shop_product_id")
    )
    private List<ShopProduct> shopProducts;
}
