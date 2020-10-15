package br.com.zup.bootcamp.fleamarketapi.features.account;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class CustomPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder passwordEncoder;

    boolean matches(String plainPassword, String accountPassword) {
        return passwordEncoder.matches(plainPassword, accountPassword);
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
