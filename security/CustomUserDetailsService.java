package kr.hs.dge.dgsw.ex1.security;

import kr.hs.dge.dgsw.ex1.dto.Member;
import kr.hs.dge.dgsw.ex1.entity.MemberEntity;
import kr.hs.dge.dgsw.ex1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username(email)
        MemberEntity memberEntity
                = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));

        Member member = Member.builder()
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .name(memberEntity.getName())
                .role(memberEntity.getRole())
                .build();

        return new CustomUserDetails(member);
    }

}
