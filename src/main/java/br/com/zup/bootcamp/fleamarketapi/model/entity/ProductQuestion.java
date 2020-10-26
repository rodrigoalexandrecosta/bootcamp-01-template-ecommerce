package br.com.zup.bootcamp.fleamarketapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ProductQuestion {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String question;

    private OffsetDateTime createdAt;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Product product;

    @Deprecated
    public ProductQuestion() {
    }

    public ProductQuestion(String question, OffsetDateTime createdAt, Account account, Product product) {
        this.question = question;
        this.createdAt = createdAt;
        this.account = account;
        this.product = product;
    }
}
