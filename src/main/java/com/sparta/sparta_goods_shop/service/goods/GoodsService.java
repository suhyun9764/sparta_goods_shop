package com.sparta.sparta_goods_shop.service.goods;

import com.sparta.sparta_goods_shop.dto.goods.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.goods.GoodsResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodsService {

    //상품 등록
    GoodsResponseDto save(GoodsRequestDto requestDto);

    //상품 목록 조회
    List<GoodsResponseDto> findAll(String sortBy, boolean isAsc, int page, int size);

    //선택한 상품 조회
    GoodsResponseDto findById(Long goodsId);

    GoodsResponseDto uploadImage(Long goodsId, MultipartFile file);
}
