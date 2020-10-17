package br.com.zup.bootcamp.fleamarketapi.features.product;

import br.com.zup.bootcamp.fleamarketapi.features.account.Account;
import br.com.zup.bootcamp.fleamarketapi.features.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateProductRequest {

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

    @NotEmpty(message = "message.product.characteristics.mandatory")
    // min = 3
    private List<CreateProductCharacteristicRequest> characteristics;

    @NotNull(message = "message.product.category-id.mandatory")
//    @ExistingCategory
    private UUID categoryId;


    @Deprecated
    public CreateProductRequest() {
    }

    public Product toProduct(Account account, Category category) {
        return Product.builder()
                .name(this.name)
                .price(this.price)
                .quantity(this.quantity)
                .description(this.description)
                .createdAt(OffsetDateTime.now())
                .account(account)
                .category(category)
                .build();
    }
}
