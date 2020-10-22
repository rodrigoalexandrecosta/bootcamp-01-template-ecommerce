package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import br.com.zup.bootcamp.fleamarketapi.features.account.encryption.CustomPasswordEncoder;
import br.com.zup.bootcamp.fleamarketapi.model.entity.Account;
import br.com.zup.bootcamp.fleamarketapi.model.response.AccountResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository accountRepository;
    private final CustomPasswordEncoder passwordEncoder;
    private final JWTSigner jwtSigner;

    public AuthenticationResult authenticate(AuthenticationRequest request) {
        return this.accountRepository.findByEmail(request.getEmail())
                .map(account -> this.validateCredentials(request.getPassword(), account))
                .orElse(this.authenticationFail());
    }

    private AuthenticationResult validateCredentials(String plainPassword, Account account) {
        boolean isAuthenticated = this.passwordEncoder.matches(plainPassword, account.getPassword());

        if (isAuthenticated) {
            return this.authenticationSuccess(new AccountResponse(account.getEmail(), account.getCreatedAt()));
        }
        return this.authenticationFail();
    }

    private AuthenticationResult authenticationSuccess(AccountResponse accountResponse) {
        final TokenPair tokenPair = this.generateTokenPair(accountResponse);

        return AuthenticationResult.builder()
                .authenticated(true)
                .accessToken(tokenPair.getAccessToken())
                .refreshToken(tokenPair.getRefreshToken())
                .accountResponse(accountResponse)
                .build();
    }

    private AuthenticationResult authenticationFail() {
        return AuthenticationResult.builder()
                .authenticated(false)
                .build();
    }

    private TokenPair generateTokenPair(AccountResponse accountResponse) {
        String accessToken = jwtSigner.generateToken(accountResponse, Duration.ofHours(24L));
        String refreshToken = jwtSigner.generateToken(accountResponse, Duration.ofHours(48L));

        return new TokenPair(accessToken, refreshToken);
    }
}
