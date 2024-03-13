package com.sparta.sparta_goods_shop.dto.user.request;

import com.sparta.sparta_goods_shop.entity.User.enums.GenderEnum;
import com.sparta.sparta_goods_shop.entity.User.enums.RoleEnum;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDto {
    private Long id;
    @Email
    private String email;
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$",
            message = "비밀번호는 최소 8자 이상, 15자 이하이며 알파벳 대소문자, 숫자, 특수문자로 구성되어야 합니다.")
    @NotBlank
    private String password;
    @NotNull
    private GenderEnum gender;
    @NotBlank
    private String phone;
    @NotBlank
    private String address;
    private RoleEnum roleEnum = RoleEnum.USER;
    private boolean admin = false;
    private String adminToken = "";

}
