package br.com.zup.bootcamp.fleamarketapi.features.account.validation;

import lombok.extern.slf4j.Slf4j;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Slf4j
public class PasswordStrengthValidator implements ConstraintValidator<PasswordStrength, String> {

    @Override
    public void initialize(PasswordStrength annotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
//        PasswordValidator validator = new PasswordValidator(List.of(
//
//                // length between 6 and 150 chars
//                new LengthRule(6, 150),
//
//                // at least one lower case char
//                new CharacterRule(EnglishCharacterData.LowerCase, 1),
//
//                // at least one digit char
//                new CharacterRule(EnglishCharacterData.Digit, 1)
//        ));

        PasswordValidator validator = new PasswordValidator(new LengthRule(6, 150));

        RuleResult validationResult = validator.validate(new PasswordData(password));

        if (!validationResult.isValid()) {
            this.logReasonsForPasswordWeakness(validationResult);
            return false;
        }

        return true;
    }

    private void logReasonsForPasswordWeakness(RuleResult validationResult) {
        validationResult.getDetails()
                .stream()
                .map(RuleResultDetail::getErrorCode)
                .forEach(log::info);
    }

}
