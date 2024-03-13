package com.sparta.sparta_goods_shop.service.cart;

import com.sparta.sparta_goods_shop.dto.cart.CartResponseDto;
import com.sparta.sparta_goods_shop.dto.goods.GoodsResponseDto;
import com.sparta.sparta_goods_shop.entity.User.User;
import com.sparta.sparta_goods_shop.entity.cart.Cart;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import com.sparta.sparta_goods_shop.repository.CartRepository;
import com.sparta.sparta_goods_shop.repository.GoodsRepository;
import com.sparta.sparta_goods_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sparta.sparta_goods_shop.constants.cart.Messages.CANT_OVER_MAX_QUANTITY;
import static com.sparta.sparta_goods_shop.constants.goods.Messages.NOT_FOUND_GOODS;
import static com.sparta.sparta_goods_shop.constants.user.Messages.NOT_FOUND_USER;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final GoodsRepository goodsRepository;
    private final UserRepository userRepository;
    private final static Long MAX_QUANTITY = 10L;

    @Override
    @Transactional  // 장바구니에 담기
    public List<CartResponseDto> addToCart(Long goodsId, Long quantity, User user) {
        Goods goods = getGoods(goodsId);
        checkQuantity(quantity);

        Optional<Cart> findCart = cartRepository.findByUserAndGoods(user, goods);
        addGoodsToCart(quantity, user, findCart, goods);

        List<CartResponseDto> cartList = new ArrayList<>();
        User findUser = getUser(user);
        makeCartList(findUser, cartList);

        return cartList;

    }

    @Override   //장바구니 전체 조회
    public List<CartResponseDto> findAll(User user) {
        List<CartResponseDto> cartList = new ArrayList<>();
        makeCartList(getUser(user),cartList);
        return cartList;
    }

    private void addGoodsToCart(Long quantity, User user, Optional<Cart> findCart, Goods goods) {
        if (findCart.isPresent()) {   // 이미 담은 제품인 경우
            Cart cart = findCart.get();
            checkQuantity(cart.getQuantity() + quantity);
            cart.addQuantity(quantity);
        } else {  // 처음 등록하는 제품인 경우
            cartRepository.save(new Cart(goods, quantity, user));
        }
    }

    private Goods getGoods(Long goodsId) {
        Goods goods = goodsRepository.findById(goodsId).orElseThrow(() ->
                new NullPointerException(NOT_FOUND_GOODS));
        return goods;
    }

    private static void checkQuantity(Long quantity) {
        if (quantity > MAX_QUANTITY) {
            throw new IllegalArgumentException(CANT_OVER_MAX_QUANTITY);
        }
    }

    private User getUser(User user) {
        User findUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() ->
                new NullPointerException(NOT_FOUND_USER));
        return findUser;
    }


    private static void makeCartList(User findUser, List<CartResponseDto> cartList) {
        findUser.getCartList().stream().forEach(cart -> {
            cartList.add(new CartResponseDto(new GoodsResponseDto(cart.getGoods()), cart.getQuantity()));
        });
    }

}
