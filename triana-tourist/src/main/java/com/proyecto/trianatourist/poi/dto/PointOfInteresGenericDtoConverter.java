package com.proyecto.trianatourist.poi.dto;

import com.proyecto.trianatourist.poi.model.PointOfInteres;
import org.springframework.stereotype.Component;

@Component
public class PointOfInteresGenericDtoConverter {

    public PointOfInteresGenericDto poiToDto(PointOfInteres poi){
        return  PointOfInteresGenericDto.builder()
                .id(poi.getId())
                .name(poi.getName())
                .location(poi.getLocation())
                .description(poi.getDescription())
                .date(poi.getDate())
                .coverPhoto(poi.getCoverPhoto())
                .photo2(poi.getPhoto2())
                .photo3(poi.getPhoto3())
                .build();
    }

    public PointOfInteres dtoToPoi (PointOfInteresGenericDto dto){
        return PointOfInteres.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .description(dto.getDescription())
                .date(dto.getDate())
                .coverPhoto(dto.getCoverPhoto())
                .photo2(dto.getPhoto2())
                .photo3(dto.getPhoto3())
                .build();
    }
}
