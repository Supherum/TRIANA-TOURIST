package com.proyecto.trianatourist.category.dto;

import com.proyecto.trianatourist.validator.uniquename.anotation.UniqueCategory;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class CategoryGenericDto {

    private UUID id;
    @NotBlank(message = "{name.blank}")
    @UniqueCategory
    private String name;
}
