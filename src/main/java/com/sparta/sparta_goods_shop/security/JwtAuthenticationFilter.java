package com.sparta.sparta_goods_shop.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.sparta_goods_shop.dto.user.request.LoginRequestDto;
import com.sparta.sparta_goods_shop.entity.User.enums.RoleEnum;
import com.sparta.sparta_goods_shop.jwt.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.sparta.sparta_goods_shop.constants.user.Messages.LOGIN_COMPLETE;
import static com.sparta.sparta_goods_shop.constants.user.Messages.LOGIN_FAIL;

@Slf4j(topic = "로그인 및 JWT 생성")
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/users/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getEmail(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        RoleEnum roleEnum = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRoleEnum();

        String token = jwtUtil.createToken(username, roleEnum);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        response.getWriter().write(String.format(LOGIN_COMPLETE, username));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(LOGIN_FAIL);
    }

}