package com.sparta.sparta_goods_shop.service;

import com.sparta.sparta_goods_shop.dto.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.GoodsResponseDto;

import java.awt.print.Pageable;
import java.util.List;

public interface GoodsService {

    //상품 등록
    GoodsResponseDto save(GoodsRequestDto requestDto);
    //
    List<GoodsResponseDto> findAll(String sortBy, boolean isAsc, int page, int size);
}
