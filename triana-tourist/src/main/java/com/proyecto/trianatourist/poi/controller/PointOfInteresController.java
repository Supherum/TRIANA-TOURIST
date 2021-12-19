package com.proyecto.trianatourist.poi.controller;

import com.fasterxml.jackson.annotation.JsonView;

import com.proyecto.trianatourist.poi.Views.PointOfInteresViews;
import com.proyecto.trianatourist.poi.dto.PointOfInteresGenericDto;
import com.proyecto.trianatourist.poi.dto.PointOfInteresGenericDtoConverter;
import com.proyecto.trianatourist.poi.service.PointOfInteresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/poi")
@RequiredArgsConstructor
public class PointOfInteresController {

    private final PointOfInteresService pointOfInteresService;
    private final PointOfInteresGenericDtoConverter converter;
    Class clas= PointOfInteresService.class;

    @GetMapping("")
    @JsonView (value = PointOfInteresViews.Normal.class)
    public List<PointOfInteresGenericDto> getAllPoi(){
        return pointOfInteresService.findAll(clas).stream().map(converter::poiToDto).collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    @JsonView (value = PointOfInteresViews.Detail.class)
    public PointOfInteresGenericDto getPoiById(@PathVariable("id") UUID id){
        return converter.poiToDto(pointOfInteresService.findById(id,clas).get());
    }

    @PostMapping("")
    @JsonView (value = PointOfInteresViews.Detail.class)
    public ResponseEntity<PointOfInteresGenericDto> createPoi (@Valid @RequestBody PointOfInteresGenericDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.poiToDto(pointOfInteresService.save(converter.dtoToPoi(dto))));
    }

    @PutMapping("/{id}")
    @JsonView (value = PointOfInteresViews.Detail.class)
    public PointOfInteresGenericDto editPoi (@Valid @RequestBody PointOfInteresGenericDto dto, @PathVariable ("id") UUID id){
        return  pointOfInteresService.edit(dto,id);
    }

    @DeleteMapping("/{id}")
    public void deletePoi (@PathVariable ("id") UUID id){
        pointOfInteresService.deleteById(id,clas);
    }

    @PutMapping("/{id1}/category/{id2}")
    public void addCategoryToPoi(@PathVariable ("id1") UUID id1, @PathVariable ("id2") UUID id2){
        pointOfInteresService.addCategory(id1,id2);
    }

    @PutMapping("/{id1}/rute/{id2}")
    public void addRuteToPoi(@PathVariable ("id1") UUID id1, @PathVariable ("id2") UUID id2){
        pointOfInteresService.addRute(id1,id2);
    }

    @DeleteMapping ("/{id1}/rute/{id2}")
    public void removeRuteToPoi(@PathVariable ("id1") UUID id1, @PathVariable ("id2") UUID id2){
        pointOfInteresService.removeRuteToPoi(id1,id2);
    }

    @DeleteMapping ("/{id1}/category/{id2}")
    public void removeCategoryToPoi(@PathVariable ("id1") UUID id1, @PathVariable ("id2") UUID id2){
        pointOfInteresService.removeCategoryToPoi(id1,id2);
    }

}
