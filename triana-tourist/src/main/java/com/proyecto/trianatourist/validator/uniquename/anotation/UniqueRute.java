package com.proyecto.trianatourist.validator.uniquename.anotation;

import com.proyecto.trianatourist.validator.uniquename.validator.UniqueCategoryValidator;
import com.proyecto.trianatourist.validator.uniquename.validator.UniqueRuteValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueRuteValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface UniqueRute {

    String message() default "El campo debe ser único";
    Class <?> [] groups() default {};
    Class <? extends Payload> [] payload() default {};

}
