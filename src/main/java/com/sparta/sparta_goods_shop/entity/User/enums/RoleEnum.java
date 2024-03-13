package com.sparta.sparta_goods_shop.entity.User.enums;

public enum RoleEnum {
    ADMIN(Authority.ADMIN),
    USER(Authority.USER);

    private final String authority;
    RoleEnum(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public static class Authority {
        public static final String USER = "ROLE_USER";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
