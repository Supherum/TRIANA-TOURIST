package com.proyecto.trianatourist.rute.service;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.error.exceptions.EntityNotFoundExceptionCustom;
import com.proyecto.trianatourist.poi.dto.PointOfInteresGenericDto;
import com.proyecto.trianatourist.poi.model.PointOfInteres;
import com.proyecto.trianatourist.poi.repository.PointOfInteresRepository;
import com.proyecto.trianatourist.rute.dto.RuteGenericDto;
import com.proyecto.trianatourist.rute.dto.RuteGenericDtoConverter;
import com.proyecto.trianatourist.rute.model.Rute;
import com.proyecto.trianatourist.rute.repository.RuteRepository;
import com.proyecto.trianatourist.shared.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RuteService extends BaseService<Rute, UUID, RuteRepository> {


    private final RuteGenericDtoConverter converter;
    private final PointOfInteresRepository pointOfInteresRepository;

    public RuteGenericDto edit(RuteGenericDto dto, UUID id) {
        Optional<Rute> poi = findById(id, Rute.class);

        poi.get().setName(dto.getName());
        return converter.ruteToDto(save(poi.get()));
    }

    public void removeRute (UUID id){
        Rute r = repository.findFirstById(id);
        if(r!=null){
            List<UUID> listaId = r.getListPointOfInterest().stream().map(x-> x.getId()).collect(Collectors.toList());
            List<PointOfInteres> listaPoi =new ArrayList<>();
            listaId.forEach(x->listaPoi.add(pointOfInteresRepository.findFirstById(x)));
            listaPoi.forEach(x->x.removeRuteToPoi(r));
            listaPoi.forEach(x->pointOfInteresRepository.save(x));
            delete(r,Category.class);
        }
        else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
    }
}
