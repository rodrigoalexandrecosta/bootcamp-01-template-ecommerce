package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/authentication/login", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResult> authenticate(@RequestBody @Valid AuthenticationRequest request) {
        AuthenticationResult authenticationResult = this.authenticationService.authenticate(request);

        if (authenticationResult.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResult);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header(HttpHeaders.WWW_AUTHENTICATE, "{\"email\":\"email_address\", \"password\":\"valid_password\"}")
                .build();
    }

}
