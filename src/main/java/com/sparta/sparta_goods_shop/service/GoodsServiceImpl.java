package com.sparta.sparta_goods_shop.service;

import com.sparta.sparta_goods_shop.dto.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.GoodsResponseDto;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import com.sparta.sparta_goods_shop.repository.GoodsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    @Override   // 상품 등록
    public GoodsResponseDto save(GoodsRequestDto requestDto) {
        Goods goods = goodsRepository.save(new Goods(requestDto));
        return new GoodsResponseDto(goods);
    }
}
