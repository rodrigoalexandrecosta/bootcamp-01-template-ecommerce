package br.com.zup.bootcamp.fleamarketapi.model.validation.annotation;

import br.com.zup.bootcamp.fleamarketapi.model.validation.validator.PasswordStrengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PasswordStrengthValidator.class)
public @interface PasswordStrength {

    String message() default "{message.account.password.weak}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
