package com.sparta.sparta_goods_shop.service;

import com.sparta.sparta_goods_shop.dto.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.GoodsResponseDto;

import java.util.List;

public interface GoodsService {

    //상품 등록
    GoodsResponseDto save(GoodsRequestDto requestDto);

    //상품 목록 조회
    List<GoodsResponseDto> findAll(String sortBy, boolean isAsc, int page, int size);

    //선택한 상품 조회
    GoodsResponseDto findById(Long goodsId);
}
