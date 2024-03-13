package com.sparta.sparta_goods_shop.controller;

import com.sparta.sparta_goods_shop.dto.user.request.SignUpRequestDto;
import com.sparta.sparta_goods_shop.dto.user.response.UserResponseDto;
import com.sparta.sparta_goods_shop.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.sparta.sparta_goods_shop.constants.user.Messages.PLEASE_LOGIN;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/login-page") //로그인 요청 페이지
    public String customLoginPage() {
        return PLEASE_LOGIN;
    }

    @PostMapping("/signup") // 회원 가입
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        UserResponseDto responseDto = userService.signUp(signUpRequestDto);
        return ResponseEntity.ok(responseDto);
    }

}
