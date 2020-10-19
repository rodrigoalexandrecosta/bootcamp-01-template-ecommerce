package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.PasswordStrength;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.UniqueEmail;
import br.com.zup.bootcamp.fleamarketapi.model.Account;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAccountRequest {

    @NotBlank(message = "message.account.email.mandatory")
    @Length(max = 255, message = "message.account.email.length")
    @Email(message = "message.account.email.invalid-format")
    @UniqueEmail
    private String email;

    @NotBlank(message = "message.account.password.mandatory")
    @Length(min = 6, message = "message.account.password.length")
    @PasswordStrength
    private String password;


    public Account toAccount(String encodedPassword) {
        return Account.builder()
                .email(this.email)
                .password(encodedPassword)
                .createdAt(OffsetDateTime.now())
                .build();
    }
}
