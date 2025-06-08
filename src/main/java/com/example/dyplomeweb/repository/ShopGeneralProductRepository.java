package com.example.dyplomeweb.repository;

import com.example.dyplomeweb.entity.ShopGeneralProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShopGeneralProductRepository extends JpaRepository<ShopGeneralProduct, Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into shop_general_products(shop_product_id, general_product_id) VALUES (:sid, :gid)", nativeQuery = true)
    void save(@Param("sid") Integer shopProductId, @Param("gid") Integer generalProductId);

    ClassValue<Object> findByShopProductId(Integer shopProductId);
}
