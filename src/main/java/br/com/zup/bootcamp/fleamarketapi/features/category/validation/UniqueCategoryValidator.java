package br.com.zup.bootcamp.fleamarketapi.features.category.validation;

import br.com.zup.bootcamp.fleamarketapi.features.category.CategoryRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

    private final CategoryRepository categoryRepository;

    @Override
    public void initialize(UniqueCategory annotation) {
    }

    @Override
    public boolean isValid(String categoryName, ConstraintValidatorContext context) {
        return !this.categoryRepository.existsByName(categoryName);
    }
}
