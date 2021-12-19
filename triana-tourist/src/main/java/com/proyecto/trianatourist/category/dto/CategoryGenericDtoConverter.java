package com.proyecto.trianatourist.category.dto;

import com.proyecto.trianatourist.category.model.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class CategoryGenericDtoConverter {

    public CategoryGenericDto categoryToDto(Category category){
        return CategoryGenericDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public Category dtoToCategory(CategoryGenericDto dto){
        return Category.builder()
                .name(dto.getName())
                .build();
    }
}
