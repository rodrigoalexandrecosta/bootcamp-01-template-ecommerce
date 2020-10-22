package br.com.zup.bootcamp.fleamarketapi.model.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AccountResponse {

    private String email;
    private OffsetDateTime createdAt;


    @Deprecated
    public AccountResponse() {
    }

    public AccountResponse(String email, OffsetDateTime createdAt) {
        this.email = email;
        this.createdAt = createdAt;
    }

}
