package br.com.zup.bootcamp.fleamarketapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(generator = "uuid")
    private UUID id;

    @NotBlank(message = "message.product.name.mandatory")
    private String name;

    @NotNull(message = "message.product.price.mandatory")
    @Positive(message = "message.product.price.value")
    private BigDecimal price;

    @NotNull(message = "message.product.quantity.mandatory")
    @PositiveOrZero(message = "message.product.quantity.value")
    private Integer quantity;

    @NotBlank(message = "message.product.description.mandatory")
    @Length(max = 1000, message = "message.product.description.length")
    private String description;

    @NotNull(message = "message.product.created-at.mandatory")
    private OffsetDateTime createdAt;

    @OneToMany(mappedBy = "product")
    private List<ProductCharacteristic> productCharacteristics;

    @OneToMany(mappedBy = "product")
    private List<ProductPhoto> productPhotos;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Account account;


    @Deprecated
    public Product() {
    }
}
