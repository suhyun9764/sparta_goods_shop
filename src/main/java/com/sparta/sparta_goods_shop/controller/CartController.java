package com.sparta.sparta_goods_shop.controller;

import com.sparta.sparta_goods_shop.dto.CartResponseDto;
import com.sparta.sparta_goods_shop.security.UserDetailsImpl;
import com.sparta.sparta_goods_shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{goodsId}")
    public String test(@PathVariable Long goodsId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("come" + userDetails.getUser().getEmail());
        return "Ok" + goodsId;
    }

    @PostMapping("/{goodsId}")
    public ResponseEntity<List<CartResponseDto>> addToCart(@PathVariable Long goodsId, @RequestParam Long quantity,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("come" + "+" + goodsId);
        System.out.println(userDetails.getUser().getEmail());
        List<CartResponseDto> goodsList = cartService.addToCart(goodsId, quantity, userDetails.getUser());
        return ResponseEntity.ok(goodsList);
    }


}
