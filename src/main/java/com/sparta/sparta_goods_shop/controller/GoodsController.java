package com.sparta.sparta_goods_shop.controller;

import com.sparta.sparta_goods_shop.dto.goods.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.goods.GoodsResponseDto;
import com.sparta.sparta_goods_shop.service.goods.GoodsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping    // 상품 목록 조회
    public ResponseEntity<List<GoodsResponseDto>> findAll(@RequestParam(required = false) String sortBy,
                                                          @RequestParam(required = false) boolean isAsc,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size) {
        List<GoodsResponseDto> goodsList = goodsService.findAll(sortBy, isAsc, page - 1, size);
        return ResponseEntity.ok(goodsList);
    }

    @GetMapping("/{goodsId}")
    public ResponseEntity<GoodsResponseDto> findById(@PathVariable Long goodsId) {
        return ResponseEntity.ok(goodsService.findById(goodsId));
    }

}
