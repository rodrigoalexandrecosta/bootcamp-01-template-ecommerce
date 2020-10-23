package br.com.zup.bootcamp.fleamarketapi.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountResponse {

    private String email;


    @Deprecated
    public AccountResponse() {
    }

    public AccountResponse(String email) {
        this.email = email;
    }

}
