package com.sparta.sparta_goods_shop.dto.goods;

import com.sparta.sparta_goods_shop.entity.goods.Category;
import com.sparta.sparta_goods_shop.entity.goods.Goods;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoodsResponseDto {
    private String name;

    private int price;

    private int quantity;

    private String description;

    private Category category;

    private String file;

    public GoodsResponseDto(Goods goods) {
        this.name = goods.getName();
        this.price = goods.getPrice();
        this.quantity = goods.getQuantity();
        this.description = goods.getDescription();
        this.category = goods.getCategory();
        this.file = goods.getFile();
    }
}
