package br.com.zup.bootcamp.fleamarketapi.model.response;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductOpinion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductOpinionResponse {

    private Integer stars;
    private String title;
    private String description;


    @Deprecated
    public ProductOpinionResponse() {
    }

    public ProductOpinionResponse(Integer stars, String title, String description) {
        this.stars = stars;
        this.title = title;
        this.description = description;
    }

    public static List<ProductOpinionResponse> from(List<ProductOpinion> productOpinions) {
        return productOpinions.stream()
                .map(opinion -> new ProductOpinionResponse(opinion.getStars(), opinion.getTitle(), opinion.getDescription()))
                .collect(Collectors.toList());
    }
}
