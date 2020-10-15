package br.com.zup.bootcamp.fleamarketapi.features.account.domain;

import br.com.zup.bootcamp.fleamarketapi.features.account.validation.PasswordStrength;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAccountRequest {

    @NotNull(message = "message.account.login.mandatory")
    @Length(max = 255, message = "message.account.login.length")
    @Email(message = "message.account.login.invalid-format")
    private String login;

    @NotNull(message = "message.account.password.mandatory")
    @Length(min = 6, message = "message.account.password.length")
    @PasswordStrength
    private String password;


    public Account toAccount(String encodedPassword) {
        return Account.builder()
                .login(this.login)
                .password(encodedPassword)
                .createdAt(OffsetDateTime.now())
                .build();
    }
}
