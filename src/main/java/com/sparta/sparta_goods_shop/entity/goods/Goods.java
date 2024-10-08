package com.sparta.sparta_goods_shop.entity.goods;

import com.sparta.sparta_goods_shop.dto.goods.GoodsRequestDto;
import com.sparta.sparta_goods_shop.entity.cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "goods")
@Getter
@NoArgsConstructor
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Category category;

    @Column
    private String file;


    @OneToMany(mappedBy = "goods", cascade = CascadeType.REMOVE)
    private List<Cart> cartList;

    public Goods(GoodsRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.quantity = requestDto.getQuantity();
        this.description = requestDto.getDescription();
        this.category = requestDto.getCategory();
    }

    public void uploadImage(String fileUrl) {
        this.file = fileUrl;
    }
}
