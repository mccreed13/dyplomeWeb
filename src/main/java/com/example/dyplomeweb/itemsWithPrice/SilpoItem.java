package com.example.dyplomeweb.itemsWithPrice;

import com.example.dyplomeweb.enums.Brands;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SilpoItem extends ItemWithPrice{
    public SilpoItem(String itemName, Float price, Float oldPrice, String url, Timestamp updatedAt, Boolean isAvailable) {
        super(itemName, url, Brands.SILPO, price, oldPrice, updatedAt, isAvailable);
    }

    public static SilpoItem valueOf(ItemWithPrice s) {
        return new SilpoItem(s.getItemName(), s.getPrice(), s.getOldPrice(), s.getUrl(), s.getUpdatedAt(), s.getIsAvailable());
    }
}
