package kr.hs.dge.dgsw.ex1.service;

import kr.hs.dge.dgsw.ex1.dto.AuthenticationRequest;
import kr.hs.dge.dgsw.ex1.dto.JsonWebTokenResponse;

public interface AuthService {
    JsonWebTokenResponse auth(AuthenticationRequest request);
    JsonWebTokenResponse refresh(String token);
}








