package com.example.CafeTour.auth;

import com.example.CafeTour.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PrincipalDetails implements UserDetails {

    private User user;
    public PrincipalDetails(User user) {
        this.user=user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities=new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    public String getNickName(){
        return user.getNickName();
    }

    @Override
    public String getUsername() {
        return user.getEmail(); //저자는 이메일로 로그인을 하기 때문에 email을 받아온다.
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUser(User user){
        this.user=user;
    }
}
