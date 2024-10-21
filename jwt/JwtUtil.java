package kr.hs.dge.dgsw.ex1.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import kr.hs.dge.dgsw.ex1.dto.Member;
import kr.hs.dge.dgsw.ex1.entity.MemberEntity;
import kr.hs.dge.dgsw.ex1.jwt.properties.JwtProperties;
import kr.hs.dge.dgsw.ex1.repository.MemberRepository;
import kr.hs.dge.dgsw.ex1.security.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final MemberRepository memberRepository;

    // access token
    public String generateAccessToken(String email) {
        String accessToken = Jwts.builder()
                .setSubject(email) // username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpiration()))
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
        return accessToken;
    }
    // refresh token
    public String generateRefreshToken(String email){
        String refreshToken = Jwts.builder()
                .setSubject(email) // username
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(
                                System.currentTimeMillis() + jwtProperties.getRefreshExpiration()
                        )
                )
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecretKey())
                .compact();
        return refreshToken;
    }

    // 토큰 복호화 및 클레임 확인
    public Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecretKey())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new JwtException("Expired JWT token");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("Unsupported JWT token");
        } catch (MalformedJwtException e) {
            throw new JwtException("Invalid JWT token");
        } catch (SignatureException e) {
            throw new JwtException("Invalid JWT token");
        } catch (IllegalArgumentException e) {
            throw new JwtException("JWT claims string is empty");
        }
    }

    public Authentication getAuthentication(String token){
        Claims claims = getClaims(token);
        String email = claims.getSubject();

        MemberEntity memberEntity
                = memberRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found with email : " + email)
                );
        // MemberEntity -> Member
        Member member = Member.builder()
                .email(memberEntity.getEmail())
                .password(memberEntity.getPassword())
                .name(memberEntity.getName())
                .role(memberEntity.getRole())
                .build();

        // 사용자 인증 및 권한 정보를 담는 객체
        CustomUserDetails customUserDetails = new CustomUserDetails(member);

        return new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
        // return Authentication
    }



}
