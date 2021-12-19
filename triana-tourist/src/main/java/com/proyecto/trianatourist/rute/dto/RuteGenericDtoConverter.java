package com.proyecto.trianatourist.rute.dto;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.rute.model.Rute;
import org.springframework.stereotype.Component;

@Component
public class RuteGenericDtoConverter {

    public RuteGenericDto ruteToDto(Rute rute){
        return RuteGenericDto.builder()
                .id(rute.getId())
                .name(rute.getName())
                .build();
    }

    public Rute dtoToRute(RuteGenericDto dto){
        return Rute.builder()
                .name(dto.getName())
                .build();
    }
}
