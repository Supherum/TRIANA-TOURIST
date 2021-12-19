package com.proyecto.trianatourist.validator.uniquename.validator;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.category.service.CategoryService;
import com.proyecto.trianatourist.rute.model.Rute;
import com.proyecto.trianatourist.rute.service.RuteService;
import com.proyecto.trianatourist.validator.uniquename.anotation.UniqueCategory;
import com.proyecto.trianatourist.validator.uniquename.anotation.UniqueRute;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueRuteValidator implements ConstraintValidator<UniqueRute, String> {
    @Autowired
    private RuteService service;

    @Override
    public void initialize(UniqueRute constraintAnnotation) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<Rute> listRute = service.findAll(Category.class);
        return !listRute.stream().filter(x->x.getName().equals(value)).findFirst().isPresent();
    }
}
