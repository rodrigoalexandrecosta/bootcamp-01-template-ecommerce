package br.com.zup.bootcamp.fleamarketapi.model.validation.validator;

import br.com.zup.bootcamp.fleamarketapi.features.product.category.ProductCategoryRepository;
import br.com.zup.bootcamp.fleamarketapi.model.validation.annotation.ExistingParentCategory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ExistingParentCategoryValidator implements ConstraintValidator<ExistingParentCategory, UUID> {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void initialize(ExistingParentCategory annotation) {
    }

    @Override
    public boolean isValid(UUID parentCategoryId, ConstraintValidatorContext context) {
        if (parentCategoryId == null) {
            return true;
        }
        return this.productCategoryRepository.findById(parentCategoryId).isPresent();
    }
}
