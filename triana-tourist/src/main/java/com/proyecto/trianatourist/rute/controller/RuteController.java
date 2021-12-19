package com.proyecto.trianatourist.rute.controller;

import com.proyecto.trianatourist.poi.dto.PointOfInteresGenericDto;
import com.proyecto.trianatourist.rute.dto.RuteGenericDto;
import com.proyecto.trianatourist.rute.dto.RuteGenericDtoConverter;
import com.proyecto.trianatourist.rute.model.Rute;
import com.proyecto.trianatourist.rute.service.RuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rute")
public class RuteController {

    private final RuteService ruteService;
    private final RuteGenericDtoConverter converter;
    Class clas= Rute.class;

    @GetMapping("")
    public List<RuteGenericDto> getAllRute(){
        return ruteService.findAll(clas).stream().map(converter::ruteToDto).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<RuteGenericDto> getRuteById (@PathVariable("id") UUID id){
        return ResponseEntity.status(HttpStatus.CREATED).body(converter.ruteToDto(ruteService.findById(id,clas).get()));
    }

    @PostMapping("")
    public RuteGenericDto createRute (@Valid @RequestBody RuteGenericDto dto){
        return converter.ruteToDto(ruteService.save(converter.dtoToRute(dto)));
    }

    @PutMapping("/{id}")
    public RuteGenericDto editRute (@Valid @RequestBody RuteGenericDto dto, @PathVariable ("id") UUID id){
        return  ruteService.edit(dto,id);
    }

    @DeleteMapping("/{id}")
    public void deleteRute (@PathVariable ("id") UUID id){
        ruteService.removeRute(id);
    }
}
