package br.com.zup.bootcamp.fleamarketapi.model.response;

import br.com.zup.bootcamp.fleamarketapi.model.entity.Product;
import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductOpinion;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Setter
public class ProductDetailsResponse {

    private String name;
    private BigDecimal price;
    private Integer quantity;
    private String description;
    private List<ProductCharacteristicResponse> characteristics;
    private List<ProductPhotoResponse> photos;
    private List<ProductQuestionResponse> questions;
    private List<ProductOpinionResponse> opinions;
    private Integer totalRatingStars;
    private BigDecimal mediumOfRatingStars;


    public ProductDetailsResponse(Product product) {
        this.name = product.getName();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.description = product.getDescription();
        this.characteristics = ProductCharacteristicResponse.from(product.getProductCharacteristics());
        this.photos = ProductPhotoResponse.from(product.getProductPhotos());
        this.questions = ProductQuestionResponse.from(product.getProductQuestions());
        this.opinions = ProductOpinionResponse.from(product.getProductOpinions());
        this.totalRatingStars = this.opinions.size();
        this.mediumOfRatingStars = this.calculateMediumRatingStars(product.getProductOpinions());
    }

    private BigDecimal calculateMediumRatingStars(final List<ProductOpinion> opinions) {
        final int totalRatingStars = opinions.size();
        int mediumOfRatingStars = 0;
        for(ProductOpinion opinion : opinions) {
            mediumOfRatingStars += opinion.getStars();
        }

        return new BigDecimal(Integer.toString(mediumOfRatingStars))
                .divide(new BigDecimal(Integer.toString(totalRatingStars)), 2, RoundingMode.HALF_UP);
    }
}
