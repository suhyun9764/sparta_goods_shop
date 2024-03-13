package com.sparta.sparta_goods_shop.controller;

import com.sparta.sparta_goods_shop.dto.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.GoodsResponseDto;
import com.sparta.sparta_goods_shop.service.GoodsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.sparta.sparta_goods_shop.entity.User.enums.RoleEnum.Authority.ADMIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goods")
public class GoodsController {
    private final GoodsService goodsService;

    @Secured(ADMIN)
    @PostMapping    // 상품 등록
    public ResponseEntity<GoodsResponseDto> save(@Valid @RequestBody GoodsRequestDto requestDto) {
        GoodsResponseDto goodsResponseDto = goodsService.save(requestDto);
        return ResponseEntity.ok(goodsResponseDto);
    }

}
