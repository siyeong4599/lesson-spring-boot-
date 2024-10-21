package kr.hs.dge.dgsw.ex1.service;

import io.jsonwebtoken.Claims;
import kr.hs.dge.dgsw.ex1.dto.AuthenticationRequest;
import kr.hs.dge.dgsw.ex1.dto.JsonWebTokenResponse;
import kr.hs.dge.dgsw.ex1.dto.Member;
import kr.hs.dge.dgsw.ex1.jwt.JwtUtil;
import kr.hs.dge.dgsw.ex1.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    public JsonWebTokenResponse auth(AuthenticationRequest request) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Member member = ((CustomUserDetails) authenticate.getPrincipal()).getMember();

        return JsonWebTokenResponse.builder()
                .accessToken(
                        jwtUtil.generateAccessToken(member.getEmail())
                )
                .refreshToken(
                        jwtUtil.generateRefreshToken(member.getEmail())
                ).build();
    }

    @Override
    public JsonWebTokenResponse refresh(String token) {
        Claims claims = jwtUtil.getClaims(token);
        String email = claims.getSubject(); // subject <- email
        return JsonWebTokenResponse.builder()
                .accessToken(
                        jwtUtil.generateAccessToken(email)
                )
                .refreshToken(
                        jwtUtil.generateRefreshToken(email)
                ).build();
    }
}
