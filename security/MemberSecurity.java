package kr.hs.dge.dgsw.ex1.security;
import kr.hs.dge.dgsw.ex1.dto.Member;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MemberSecurity {
    public Member getMember() {
        return ((CustomUserDetails)SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getMember();
    }
}
