package br.com.zup.bootcamp.fleamarketapi.features.account.validation;

import br.com.zup.bootcamp.fleamarketapi.features.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void initialize(UniqueEmail annotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return this.accountRepository.findByEmail(email).isEmpty();
    }
}
