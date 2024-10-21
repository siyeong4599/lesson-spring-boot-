package kr.hs.dge.dgsw.ex1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JsonWebTokenResponse {
    private String accessToken;
    private String refreshToken;
}
