package com.example.dyplomeweb.itemsWithPrice;

import com.example.dyplomeweb.enums.Brands;
import lombok.*;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MetroItem extends ItemWithPrice {
    public MetroItem(String itemName, Float price, Float oldPrice, String url, Timestamp updatedAt, Boolean isAvailable) {
        super(itemName, url, Brands.METRO, price, oldPrice, updatedAt, isAvailable);
    }

    public MetroItem(String itemName, String url, Brands brand, Float price, Float oldPrice, Timestamp updatedAt, Boolean isAvailable) {
        super(itemName, url, Brands.METRO, price, oldPrice, updatedAt, isAvailable);
    }

    public static MetroItem valueOf(ItemWithPrice s) {
        return new MetroItem(s.getItemName(), s.getPrice(), s.getOldPrice(), s.getUrl(), s.getUpdatedAt(), s.getIsAvailable());
    }
}
