package com.proyecto.trianatourist.validator.uniquename.validator;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.category.service.CategoryService;
import com.proyecto.trianatourist.validator.uniquename.anotation.UniqueCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

    @Autowired
    private CategoryService service;

    @Override
    public void initialize(UniqueCategory constraintAnnotation) { }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        List<Category> listCategory = service.findAll(Category.class);
        return !listCategory.stream().filter(x->x.getName().equals(value)).findFirst().isPresent();
    }
}
