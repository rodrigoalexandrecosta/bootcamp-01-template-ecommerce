package br.com.zup.bootcamp.fleamarketapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductOpinion {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private Integer stars;

    private String title;

    private String description;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Product product;


    @Deprecated
    public ProductOpinion() {
    }
}
