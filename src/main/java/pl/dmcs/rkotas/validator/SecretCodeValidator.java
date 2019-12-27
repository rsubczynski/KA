package pl.dmcs.rkotas.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = SecretCodeConstrainValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SecretCodeValidator {

    // define default course code
    String value() default "";

    // define default error message
    String message() default "Bad secret code";

    // define default groups
    Class<?>[] groups() default {};

    //define default payloads
    Class<? extends Payload>[] payload() default {};

}
