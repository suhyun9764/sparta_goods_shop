package com.sparta.sparta_goods_shop.dto.user.response;

import com.sparta.sparta_goods_shop.entity.User.User;
import com.sparta.sparta_goods_shop.entity.User.enums.GenderEnum;
import com.sparta.sparta_goods_shop.entity.User.enums.RoleEnum;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private Long id;
    private String email;
    private GenderEnum gender;
    private String phone;
    private String address;
    private RoleEnum roleEnum;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.roleEnum = user.getRoleEnum();
    }
}
