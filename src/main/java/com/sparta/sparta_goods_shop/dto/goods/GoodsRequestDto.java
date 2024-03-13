package com.sparta.sparta_goods_shop.dto.goods;

import com.sparta.sparta_goods_shop.entity.goods.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoodsRequestDto {
    @NotBlank
    private String name;

    @NotNull
    private int price;

    @NotNull
    private int quantity;

    @NotBlank
    private String description;

    private Category category;
}
