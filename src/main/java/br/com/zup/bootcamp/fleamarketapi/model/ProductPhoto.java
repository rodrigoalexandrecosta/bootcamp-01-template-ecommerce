package br.com.zup.bootcamp.fleamarketapi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.util.UUID;

@Entity
@Getter
@Setter
public class ProductPhoto {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    private String photoLocation;

//    @Transient
//    private File photo;

    @ManyToOne
    private Product product;

    @Deprecated
    public ProductPhoto() {
    }

    public ProductPhoto(String photoLocation, Product product) {
        this.photoLocation = photoLocation;
        this.product = product;
    }
}
