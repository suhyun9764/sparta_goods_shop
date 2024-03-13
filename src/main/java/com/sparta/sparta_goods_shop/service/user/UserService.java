package com.sparta.sparta_goods_shop.service.user;

import com.sparta.sparta_goods_shop.dto.user.request.SignUpRequestDto;
import com.sparta.sparta_goods_shop.dto.user.response.UserResponseDto;

public interface UserService {

    //회원가입
    UserResponseDto signUp(SignUpRequestDto signUpRequestDto);

}
