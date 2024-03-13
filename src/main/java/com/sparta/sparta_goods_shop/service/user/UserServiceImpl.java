package com.sparta.sparta_goods_shop.service.user;

import com.sparta.sparta_goods_shop.dto.user.request.SignUpRequestDto;
import com.sparta.sparta_goods_shop.dto.user.response.UserResponseDto;
import com.sparta.sparta_goods_shop.entity.User.User;
import com.sparta.sparta_goods_shop.entity.User.enums.RoleEnum;
import com.sparta.sparta_goods_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sparta.sparta_goods_shop.constants.user.Messages.EMAIL_DUPLICATE;
import static com.sparta.sparta_goods_shop.constants.user.Messages.WRONG_ADMIN_TOKEN;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    @Override   //회원 가입
    public UserResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        checkDuplicateEmail(signUpRequestDto);  //이메일 중복 검사
        assignAdminRole(signUpRequestDto);  // 관리자 권한 선택 시 권한 부여
        encryptPassword(signUpRequestDto);  // 비밀번호 암호화

        User saveUser = userRepository.save(new User(signUpRequestDto));
        return new UserResponseDto(saveUser);
    }

    // 이메일 중복 확인
    private void checkDuplicateEmail(SignUpRequestDto signUpRequestDto) {
        if (userRepository.existsByEmail(signUpRequestDto.getEmail()))
            throw new IllegalArgumentException(EMAIL_DUPLICATE);
    }

    // 관리자 선택 시 유효성 검증 후 관리자 권한 부여
    private void assignAdminRole(SignUpRequestDto signUpRequestDto) {
        if (signUpRequestDto.isAdmin()) {
            if (signUpRequestDto.getAdminToken() == ADMIN_TOKEN)
                throw new IllegalArgumentException(WRONG_ADMIN_TOKEN);

            signUpRequestDto.setRoleEnum(RoleEnum.ADMIN);
        }
    }

    // 비밀번호 암호화
    private void encryptPassword(SignUpRequestDto signUpRequestDto) {
        String encodePwd = passwordEncoder.encode(signUpRequestDto.getPassword());
        signUpRequestDto.setPassword(encodePwd);
    }
}
