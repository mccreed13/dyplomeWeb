package com.example.dyplomeweb.response;

import com.example.dyplomeweb.dto.ReceiptDTO;
import com.example.dyplomeweb.itemsWithPrice.ATBItem;
import com.example.dyplomeweb.itemsWithPrice.ItemWithPrice;
import com.example.dyplomeweb.itemsWithPrice.MetroItem;
import com.example.dyplomeweb.itemsWithPrice.SilpoItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Setter
@Getter
public class ProductWithPriceByBrand {
    ReceiptDTO receiptDTO;
    Map<String, ATBItem> atbItems;
    Map<String, SilpoItem> silpoItems;
    Map<String, MetroItem> metroItems;
    String sumPriceAtb;
    String sumPriceSilpo;
    String sumPriceMetro;

    public ProductWithPriceByBrand(ReceiptDTO receiptDTO, Map<String, ATBItem> atbItems, Map<String, SilpoItem> silpoItems, Map<String, MetroItem> metroItems) {
        this.receiptDTO = receiptDTO;
        this.atbItems = atbItems;
        this.silpoItems = silpoItems;
        this.metroItems = metroItems;
        sumPriceAtb = String.format("%.2f", getSum(atbItems));
        sumPriceMetro = String.format("%.2f", getSum(metroItems));
        sumPriceSilpo = String.format("%.2f", getSum(silpoItems));
    }

    private Float getSum(Map<String, ? extends ItemWithPrice>  items) {
        return items.values().stream().filter(Objects::nonNull).map(ItemWithPrice::getPrice).filter(Objects::nonNull).reduce(Float::sum).orElse(0f);
    }
}
