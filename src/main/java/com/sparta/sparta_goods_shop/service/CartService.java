package com.sparta.sparta_goods_shop.service;

import com.sparta.sparta_goods_shop.dto.CartResponseDto;
import com.sparta.sparta_goods_shop.entity.User.User;

import java.util.List;

public interface CartService {
    //장바구니에 담기
    List<CartResponseDto> addToCart(Long goodsId, Long quantity, User user);
}
