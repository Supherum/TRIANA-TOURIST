package com.proyecto.trianatourist.rute.dto;

import com.proyecto.trianatourist.validator.uniquename.anotation.UniqueRute;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class RuteGenericDto {

    private UUID id;
    @NotBlank(message = "{name.blank}")
    @UniqueRute
    private String name;
}
