package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.ProductPrice;
import com.example.dyplomeweb.itemsWithPrice.ItemWithPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice, Integer> {
    List<ProductPrice> findAllByProductId(Integer productId);

    @Query(value = "select new com.example.dyplomeweb.itemsWithPrice.ItemWithPrice(pr.name, sp.url, sp.brand, pp.price, pp.oldPrice, pp.updatedAt, pp.isAvailable) " +
            "from com.example.dyplomeweb.entity.ProductPrice as pp " +
            "inner join com.example.dyplomeweb.entity.Product as pr on pr.id = :id " +
            "inner join com.example.dyplomeweb.entity.ShopGeneralProduct as sgp on sgp.generalProduct.id = pr.id " +
            "inner join com.example.dyplomeweb.entity.ShopProduct as sp on sp.id = sgp.shopProduct.id " +
            "inner join com.example.dyplomeweb.entity.Shop as sh on sh.id = pp.shopId and sp.brand = sh.brand " +
            "where pp.product.id = :id"
    )
    List<ItemWithPrice> findAllItemWithPricesByGeneralProductId(@Param("id") Integer id);


}

