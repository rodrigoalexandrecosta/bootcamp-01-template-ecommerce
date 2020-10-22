package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import br.com.zup.bootcamp.fleamarketapi.model.response.AccountResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Component
@Slf4j
public class JWTSigner {

    public String generateToken(AccountResponse accountResponse, Duration duration) {
        try {
            final String signature = "12345";
            final Algorithm algorithm = Algorithm.HMAC256(signature);

            Date expiresAt = Date.from((Instant.now(Clock.systemUTC()).plus(duration)));

            return JWT.create()
                    .withIssuedAt(Date.from(Instant.now(Clock.systemUTC())))
                    .withClaim("email", accountResponse.getEmail())
                    .withClaim("createdAt", accountResponse.getCreatedAt().toString())
                    .withExpiresAt(expiresAt)
                    .withIssuer("ZupBootcamp")
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }

}
