package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.ShopProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopProductRepository extends JpaRepository<ShopProduct, Integer> {
    @Query(value = "select * from shop_products where id not in (select shop_product_id from shop_general_products)",
    nativeQuery = true)
    List<ShopProduct> findAllWhichNotPresentedInShopGeneralProducts();
}
