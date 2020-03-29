package com.chuncongcong.productmgmt.config.authorization;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Hu
 * @date 2019/3/8 15:07
 */

/**
 * security使用的User
 */
public class AuthUser implements UserDetails {

    private Long storeId;

    private String name;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public AuthUser(Long storeId, String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.storeId = storeId;
        this.name = name;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    // 账号是否未过期，默认是false，记得要改一下
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账号是否未锁定，默认是false，记得也要改一下
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 账号凭证是否未过期，默认是false，记得还要改一下
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 这个有点抽象不会翻译，默认也是false，记得改一下
    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getName() {
        return name;
    }

    public Long getStoreId() {
        return storeId;
    }
}
