package kr.hs.dge.dgsw.ex1.dto;

import kr.hs.dge.dgsw.ex1.entity.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Member {
    private String email;
    private String password;
    private String name;
    private MemberRole role;
}
