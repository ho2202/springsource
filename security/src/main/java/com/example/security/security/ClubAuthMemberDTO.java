package com.example.security.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubAuthMemberDTO extends User implements OAuth2User {

    private String email;
    private String name;
    private String password;
    private boolean fromSocial;

    public ClubAuthMemberDTO(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.password = password;
        this.email = username;
        this.fromSocial = fromSocial;
    }

    // 소셜 로그인 위해 처리
    private Map<String, Object> attr;

    // 소셜 로그인 포함 생성자
    public ClubAuthMemberDTO(String username, String password, boolean fromSocial,
            Collection<? extends GrantedAuthority> authorities, Map<String, Object> attr) {
        this(username, password, fromSocial, authorities);
        this.attr = attr;
    }

    // 소셜 로그인 처리
    @Override
    public Map<String, Object> getAttributes() {
        return this.attr;
    }
}
