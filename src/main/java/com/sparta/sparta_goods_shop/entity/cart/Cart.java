package com.sparta.sparta_goods_shop.entity.cart;

import com.sparta.sparta_goods_shop.entity.User.User;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cart")
@Getter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private Long quantity;

    public Cart(Goods goods, Long quantity, User user) {
        this.goods = goods;
        this.quantity = quantity;
        this.user = user;
    }

    public void updateQuantity(Long quantity) {
        this.quantity = quantity;
    }

}
