package com.example.dyplomeweb.itemsWithPrice;

import com.example.dyplomeweb.enums.Brands;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
public class ItemWithPrice {
    private String itemName;
    private String url;
    @Enumerated(EnumType.STRING)
    private Brands brand;
    private Float price;
    private Float oldPrice;
    private Timestamp updatedAt;
    private Boolean isAvailable;

    public ItemWithPrice(String itemName, String url, String brand, Float price, Float oldPrice, Timestamp updatedAt, Boolean isAvailable) {
        this.itemName = itemName;
        this.url = url;
        this.brand = Brands.valueOf(brand);
        this.price = price;
        this.oldPrice = oldPrice;
        this.updatedAt = updatedAt;
        this.isAvailable = isAvailable;
    }

    public void setBrand(String brand) {
        this.brand = Brands.valueOf(brand);
    }
}
