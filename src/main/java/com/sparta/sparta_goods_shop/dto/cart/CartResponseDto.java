package com.sparta.sparta_goods_shop.dto.cart;

import com.sparta.sparta_goods_shop.dto.goods.GoodsResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CartResponseDto {
    private GoodsResponseDto goods;
    private Long quantity;

    public CartResponseDto(GoodsResponseDto goodsResponseDto, Long quantity) {
        this.goods = goodsResponseDto;
        this.quantity = quantity;
    }
}
