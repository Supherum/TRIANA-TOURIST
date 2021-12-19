package com.proyecto.trianatourist.validator.uniqueurl.validator;

import com.proyecto.trianatourist.validator.uniqueurl.anotation.UniqueUrl;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUrlValidator implements ConstraintValidator<UniqueUrl, Object> {

    private String url1;
    private String url2;

    @Override
    public void initialize(UniqueUrl constraintAnnotation) {
        this.url1=constraintAnnotation.urlPrimary();
        this.url2=constraintAnnotation.urlSecondary();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object url1Value = PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(url1);
        Object url2Value = PropertyAccessorFactory.forBeanPropertyAccess(value).getPropertyValue(url2);

        if (url1Value!=null && url2Value != null){
            return !url1Value.equals(url2Value);
        }
        else
            return true;
    }
}
