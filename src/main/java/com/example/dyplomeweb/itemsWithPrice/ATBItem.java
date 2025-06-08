package com.example.dyplomeweb.itemsWithPrice;

import com.example.dyplomeweb.enums.Brands;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ATBItem extends ItemWithPrice{
    public ATBItem(String itemName, Float price, Float oldPrice, String url, Timestamp updatedAt, Boolean isAvailable) {
        super(itemName, url, Brands.ATB, price, oldPrice, updatedAt, isAvailable);
    }
    public static ATBItem valueOf(ItemWithPrice s){
        return new ATBItem(s.getItemName(), s.getPrice(), s.getOldPrice(), s.getUrl(), s.getUpdatedAt(), s.getIsAvailable());
    }
}
