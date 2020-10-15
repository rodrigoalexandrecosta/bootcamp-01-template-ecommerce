package br.com.zup.bootcamp.fleamarketapi.features.account.validation;

import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final AccountRepository accountRepository;

    @Override
    public void initialize(UniqueEmail annotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !this.accountRepository.existsByEmail(email);
    }
}
