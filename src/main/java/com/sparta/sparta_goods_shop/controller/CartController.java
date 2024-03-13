package com.sparta.sparta_goods_shop.controller;

import com.sparta.sparta_goods_shop.dto.cart.CartResponseDto;
import com.sparta.sparta_goods_shop.security.UserDetailsImpl;
import com.sparta.sparta_goods_shop.service.cart.CartService;
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

    @GetMapping
    public ResponseEntity<List<CartResponseDto>> findAll(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(cartService.findAll(userDetails.getUser()));
    }

    @PostMapping("/{goodsId}")
    public ResponseEntity<List<CartResponseDto>> addToCart(@PathVariable Long goodsId, @RequestParam Long quantity,
                                                           @AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<CartResponseDto> goodsList = cartService.addToCart(goodsId, quantity, userDetails.getUser());
        return ResponseEntity.ok(goodsList);
    }

    @PutMapping("/{goodsId}")
    public ResponseEntity<CartResponseDto> update(@PathVariable Long goodsId, @RequestParam Long updateQuantity,
                                                  @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CartResponseDto cartResponseDto = cartService.updateQuantity(goodsId, updateQuantity, userDetails.getUser());
        return ResponseEntity.ok(cartResponseDto);
    }


}
