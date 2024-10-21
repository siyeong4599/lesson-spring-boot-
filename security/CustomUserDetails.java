package kr.hs.dge.dgsw.ex1.security;

import kr.hs.dge.dgsw.ex1.dto.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Getter
public class CustomUserDetails implements UserDetails {

    private final Member member;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(Member member) {
        this.member = member;
        this.authorities
        = Collections.singleton(new SimpleGrantedAuthority(member.getRole().getKey()));
//        List<GrantedAuthority> grantedAuthorities
//                = new ArrayList<>();
//        grantedAuthorities.add(
//                new SimpleGrantedAuthority(member.getRole().getKey())
//        );
//        this.authorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
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
}
