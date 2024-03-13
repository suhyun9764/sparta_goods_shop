package com.sparta.sparta_goods_shop.repository;

import com.sparta.sparta_goods_shop.entity.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Long> {
}
