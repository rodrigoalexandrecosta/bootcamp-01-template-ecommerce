package br.com.zup.bootcamp.fleamarketapi.model.validation.validator;

import br.com.zup.bootcamp.fleamarketapi.features.product.category.ProductCategoryRepository;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.UniqueCategory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void initialize(UniqueCategory annotation) {
    }

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext context) {
        return this.productCategoryRepository.findByName(categoryName).isEmpty();
    }
}
