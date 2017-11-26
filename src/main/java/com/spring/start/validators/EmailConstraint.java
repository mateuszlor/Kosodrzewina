package com.spring.start.validators;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {})
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Size(min = 4, max = 40)
@NotEmpty
@Email
public @interface EmailConstraint {

    String message() default "validator.email.default";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
