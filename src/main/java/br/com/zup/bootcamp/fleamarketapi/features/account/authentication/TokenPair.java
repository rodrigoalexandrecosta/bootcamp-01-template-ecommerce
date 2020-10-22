package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class TokenPair {

    private final String accessToken;
    private final String refreshToken;
}
