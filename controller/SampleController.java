package kr.hs.dge.dgsw.ex1.controller;

import kr.hs.dge.dgsw.ex1.dto.Member;
import kr.hs.dge.dgsw.ex1.security.CustomUserDetails;
import kr.hs.dge.dgsw.ex1.security.MemberSecurity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sample")
@Slf4j
public class SampleController {

    private final MemberSecurity memberSecurity;

    // Bean을 통해서 가져오기
    @GetMapping("/test1")
    public void test1(){
        Member member = memberSecurity.getMember();
        log.info("Member : {}", member);
    }

    // Principal 객체에 접근해 정보를 가져오기
    @GetMapping("/test2")
    public void test2(Principal principal){
        log.info(".................................. username : {}", principal.getName());
    }

    // Authentication 객체에 접근해 정보를 가져오기
    @GetMapping("/test3")
    public void test3(Authentication authentication){
        Member member = ((CustomUserDetails) authentication
                .getPrincipal())
                .getMember();
        log.info("................................... Member : {} ", member);
    }

    @GetMapping("/test4")
    public void test4(
            @AuthenticationPrincipal
            CustomUserDetails customUserDetails
    ){
        log.info("........ Member : {}",
                customUserDetails.getMember());
    }

    @GetMapping("/member/profile")
    public void test5(
            @AuthenticationPrincipal
            CustomUserDetails customUserDetails
    ){
        log.info("................ Member : {}", customUserDetails.getMember());
    }
}
