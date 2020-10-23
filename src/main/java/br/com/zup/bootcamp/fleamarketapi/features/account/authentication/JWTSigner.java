package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import br.com.zup.bootcamp.fleamarketapi.model.response.AccountResponse;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
            final Algorithm algorithm = Algorithm.HMAC256("12345");
            Date expiresAt = Date.from((Instant.now(Clock.systemUTC()).plus(duration)));

            return JWT.create()
                    .withIssuedAt(Date.from(Instant.now(Clock.systemUTC())))
                    .withClaim("email", accountResponse.getEmail())
                    .withExpiresAt(expiresAt)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
            throw new IllegalStateException(e.getMessage());
        }
    }

}
