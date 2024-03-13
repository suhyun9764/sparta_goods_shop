package com.sparta.sparta_goods_shop.service.cart;

import com.sparta.sparta_goods_shop.dto.cart.CartResponseDto;
import com.sparta.sparta_goods_shop.entity.User.User;

import java.util.List;

public interface CartService {
    //장바구니에 담기
    List<CartResponseDto> addToCart(Long goodsId, Long quantity, User user);

    //장바구니 전체 조회
    List<CartResponseDto> findAll(User user);

    //선택한 상품의 수량 수정
    CartResponseDto updateQuantity(Long goodsId, Long updateQuantity, User user);

    //선택한 상품 장바구니에서 삭제
    String delete(Long goodsId, User user);
}
