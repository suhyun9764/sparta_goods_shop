package com.sparta.sparta_goods_shop.repository;

import com.sparta.sparta_goods_shop.entity.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
