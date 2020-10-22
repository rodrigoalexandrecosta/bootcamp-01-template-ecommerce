package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import br.com.zup.bootcamp.fleamarketapi.model.response.AccountResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuthenticationResult {

    private boolean authenticated;
    private String accessToken;
    private String refreshToken;
    private AccountResponse accountResponse;

    @Deprecated
    public AuthenticationResult() {
    }

}
