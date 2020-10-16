package br.com.zup.bootcamp.fleamarketapi.features.category.validation;

import br.com.zup.bootcamp.fleamarketapi.features.category.CategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

@RequiredArgsConstructor
public class ExistingCategoryValidator implements ConstraintValidator<ExistingCategory, UUID> {

    private final CategoryRepository categoryRepository;

    @Override
    public void initialize(ExistingCategory annotation) {
    }

    @Override
    public boolean isValid(UUID parentCategoryId, ConstraintValidatorContext context) {
        if (parentCategoryId == null) {
            return true;
        }
        return this.categoryRepository.findById(parentCategoryId).isEmpty();
    }
}
