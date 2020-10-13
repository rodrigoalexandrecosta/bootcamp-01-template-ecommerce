package br.com.zup.bootcamp.fleamarketapi.model.request;

import br.com.zup.bootcamp.fleamarketapi.model.Account;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.OffsetDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateAccountRequest {

    @NotNull(message = "message.account.login.mandatory")
    @Email(message = "message.account.login.invalid-format")
    private String login;

    @NotNull(message = "message.account.password.mandatory")
    @Length(min = 6, message = "message.account.password.length")
    private String password;

//    @NotNull(message = "message.account.created-at.mandatory")
//    @PastOrPresent(message = "message.account.created-at.past-or-present-date")
//    private OffsetDateTime createdAt;

    public Account toAccount() {
        return Account.builder()
                .login(this.login)
                .password(this.password)
                .build();
    }
}
