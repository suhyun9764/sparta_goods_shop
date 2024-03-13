package com.sparta.sparta_goods_shop.service;

import com.sparta.sparta_goods_shop.dto.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.GoodsResponseDto;

public interface GoodsService {

    //상품 등록
    GoodsResponseDto save(GoodsRequestDto requestDto);
}
