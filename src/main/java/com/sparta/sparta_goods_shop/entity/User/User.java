package com.sparta.sparta_goods_shop.entity.User;

import com.sparta.sparta_goods_shop.dto.user.request.SignUpRequestDto;
import com.sparta.sparta_goods_shop.entity.User.enums.GenderEnum;
import com.sparta.sparta_goods_shop.entity.User.enums.RoleEnum;
import com.sparta.sparta_goods_shop.entity.cart.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private GenderEnum gender;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleEnum roleEnum;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Cart> cartList;

    public User(SignUpRequestDto signUpRequestDto) {
        this.password = signUpRequestDto.getPassword();
        this.email = signUpRequestDto.getEmail();
        this.gender = signUpRequestDto.getGender();
        this.phone = signUpRequestDto.getPhone();
        this.address = signUpRequestDto.getAddress();
        this.roleEnum = signUpRequestDto.getRoleEnum();


    }
}
