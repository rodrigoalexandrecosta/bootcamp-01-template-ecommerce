package br.com.zup.bootcamp.fleamarketapi.features.account;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "message.account.email.mandatory")
    @Length(max = 255, message = "message.account.email.length")
    @Email(message = "message.account.email.invalid-format")
    private String email;

    @NotBlank(message = "message.account.password.mandatory")
    @Length(min = 6, message = "message.account.password.length")
    private String password;

    @NotNull(message = "message.account.created-at.mandatory")
    private OffsetDateTime createdAt;
}
