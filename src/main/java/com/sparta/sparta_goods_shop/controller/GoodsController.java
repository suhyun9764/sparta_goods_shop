package com.sparta.sparta_goods_shop.controller;

import com.amazonaws.services.s3.AmazonS3Client;
import com.sparta.sparta_goods_shop.dto.goods.GoodsRequestDto;
import com.sparta.sparta_goods_shop.dto.goods.GoodsResponseDto;
import com.sparta.sparta_goods_shop.service.goods.GoodsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{goodsId}")   //선택 상품 조회
    public ResponseEntity<GoodsResponseDto> findById(@PathVariable Long goodsId) {
        return ResponseEntity.ok(goodsService.findById(goodsId));
    }

    @PostMapping("/{goodsId}/image")   //이미지 업로드
    public ResponseEntity<GoodsResponseDto> uploadImage(@PathVariable Long goodsId,
                                                        @RequestParam("file")MultipartFile file) {
        return ResponseEntity.ok(goodsService.uploadImage(goodsId,file));
    }

}
