package kr.hs.dge.dgsw.ex1.jwt.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component // @Configuration
@ConfigurationProperties(prefix = "application.jwt")
public class JwtProperties {
    private String secretKey;
    private long expiration;
    private long refreshExpiration;
}
