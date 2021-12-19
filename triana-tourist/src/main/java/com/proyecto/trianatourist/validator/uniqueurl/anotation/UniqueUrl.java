package com.proyecto.trianatourist.validator.uniqueurl.anotation;


import com.proyecto.trianatourist.validator.uniqueurl.validator.UniqueUrlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueUrlValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUrl {

    String message() default "Las urls no pueden ser iguales";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String urlPrimary();
    String urlSecondary();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List{
        UniqueUrl[] value();
    }
}
