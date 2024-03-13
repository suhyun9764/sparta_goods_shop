package com.sparta.sparta_goods_shop.repository;

import com.sparta.sparta_goods_shop.entity.User.User;
import com.sparta.sparta_goods_shop.entity.cart.Cart;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserAndGoods(User user, Goods goods);

}
