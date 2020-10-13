package br.com.zup.bootcamp.fleamarketapi.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String login;

    private String password;
}
