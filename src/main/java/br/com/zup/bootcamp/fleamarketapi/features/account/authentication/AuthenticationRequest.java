package br.com.zup.bootcamp.fleamarketapi.features.account.authentication;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AuthenticationRequest {

    @NotBlank(message = "message.authentication.email.mandatory")
    private String email;

    @NotBlank(message = "message.authentication.password.mandatory")
    private String password;


    @Deprecated
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
