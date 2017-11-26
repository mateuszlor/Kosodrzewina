package com.spring.start.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegistrationNumberValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RegistrationNumberConstraint {

    String message() default "Nieprawidłowy numer rejestracyjny";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
