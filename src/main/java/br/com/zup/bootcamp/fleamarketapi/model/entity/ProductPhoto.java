package br.com.zup.bootcamp.fleamarketapi.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ProductPhoto {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String photoUrl;

//    @Transient
//    private File photo;

    @ManyToOne
    private Product product;

    @Deprecated
    public ProductPhoto() {
    }

    public ProductPhoto(String photoUrl, Product product) {
        this.photoUrl = photoUrl;
        this.product = product;
    }
}
