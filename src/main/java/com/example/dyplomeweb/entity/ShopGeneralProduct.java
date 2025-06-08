package com.example.dyplomeweb.entity;

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
@Table(name="shop_general_products")
public class ShopGeneralProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "shop_product_id")
    private ShopProduct shopProduct;
    @OneToOne
    @JoinColumn(name = "general_product_id")
    private Product generalProduct;
}
