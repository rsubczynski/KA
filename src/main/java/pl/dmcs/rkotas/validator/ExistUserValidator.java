package pl.dmcs.rkotas.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = ExistUserConstrainValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUserValidator
{

    // define default course code
    String value() default "";

    // define default error message
    String message() default "User exist in database";

    // define default groups
    Class<?>[] groups() default {};

    //define default payloads
    Class<? extends Payload>[] payload() default {};
}
