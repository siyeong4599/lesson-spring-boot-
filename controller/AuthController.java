package kr.hs.dge.dgsw.ex1.controller;

import kr.hs.dge.dgsw.ex1.dto.AuthenticationRequest;
import kr.hs.dge.dgsw.ex1.dto.JsonWebTokenResponse;
import kr.hs.dge.dgsw.ex1.dto.RefreshTokenRequest;
import kr.hs.dge.dgsw.ex1.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("")
    public ResponseEntity<JsonWebTokenResponse> auth(@RequestBody AuthenticationRequest request){
        JsonWebTokenResponse jsonWebTokenResponse = authService.auth(request);

        return ResponseEntity.ok(jsonWebTokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<JsonWebTokenResponse> refresh(
            @RequestBody RefreshTokenRequest request
            ){
        JsonWebTokenResponse jsonWebTokenResponse = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(jsonWebTokenResponse);
    }


}







