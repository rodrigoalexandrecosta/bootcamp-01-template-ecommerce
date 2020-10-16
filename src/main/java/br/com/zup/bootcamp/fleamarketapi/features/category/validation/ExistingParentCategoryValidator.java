package br.com.zup.bootcamp.fleamarketapi.features.category.validation;

import br.com.zup.bootcamp.fleamarketapi.features.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class ExistingParentCategoryValidator implements ConstraintValidator<ExistingParentCategory, UUID> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void initialize(ExistingParentCategory annotation) {
    }

    @Override
    public boolean isValid(UUID parentCategoryId, ConstraintValidatorContext context) {
        if (parentCategoryId == null) {
            return true;
        }
        return this.categoryRepository.findById(parentCategoryId).isPresent();
    }
}
