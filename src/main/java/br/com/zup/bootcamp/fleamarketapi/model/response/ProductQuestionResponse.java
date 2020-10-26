package br.com.zup.bootcamp.fleamarketapi.model.response;

import br.com.zup.bootcamp.fleamarketapi.model.entity.ProductQuestion;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProductQuestionResponse {

    private String question;
    private OffsetDateTime createdAt;


    @Deprecated
    public ProductQuestionResponse() {
    }

    public ProductQuestionResponse(String question, OffsetDateTime createdAt) {
        this.question = question;
        this.createdAt = createdAt;
    }

    public static List<ProductQuestionResponse> from(List<ProductQuestion> productQuestions) {
        return productQuestions.stream()
                .map(question -> new ProductQuestionResponse(question.getQuestion(), question.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
