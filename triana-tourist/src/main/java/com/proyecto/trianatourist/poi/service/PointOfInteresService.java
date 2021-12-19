package com.proyecto.trianatourist.poi.service;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.category.service.CategoryService;
import com.proyecto.trianatourist.error.exceptions.EnityUniqueExceptionCustom;
import com.proyecto.trianatourist.error.exceptions.EntityNotFoundExceptionCustom;
import com.proyecto.trianatourist.poi.dto.PointOfInteresGenericDto;
import com.proyecto.trianatourist.poi.dto.PointOfInteresGenericDtoConverter;
import com.proyecto.trianatourist.poi.model.PointOfInteres;
import com.proyecto.trianatourist.poi.repository.PointOfInteresRepository;
import com.proyecto.trianatourist.rute.model.Rute;
import com.proyecto.trianatourist.rute.service.RuteService;
import com.proyecto.trianatourist.shared.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class PointOfInteresService extends BaseService<PointOfInteres, UUID, PointOfInteresRepository> {


    private final PointOfInteresGenericDtoConverter converter;
    private final CategoryService categoryService;
    private final RuteService ruteService;

    public PointOfInteresGenericDto edit(PointOfInteresGenericDto dto, UUID id) {
        Optional<PointOfInteres> poi= findById(id,PointOfInteres.class);
            poi.get().setName(dto.getName());
            poi.get().setLocation(dto.getLocation());
            poi.get().setDescription(dto.getDescription());
            poi.get().setDate(dto.getDate());
            poi.get().setCoverPhoto(dto.getCoverPhoto());
            poi.get().setPhoto2(dto.getPhoto2());
            poi.get().setPhoto3(dto.getPhoto3());
            return converter.poiToDto(save(poi.get())) ;
    }

    public void addCategory(UUID id1,UUID id2){
        Optional<Category> c = categoryService.findById(id2,Category.class);
        PointOfInteres p = repository.findFirstById(id1);
        if(p!=null){
            if(p.getListCategory().stream().filter(x->x.getName().equals(c.get().getName())).findFirst().isEmpty()){
                p.addCategoryToPoi(c.get());
                save(p);
            }
            // Cambiar la excepción
            else throw new EnityUniqueExceptionCustom(Category.class);
        }
        else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
     }

     public void addRute(UUID id1, UUID id2){
        Optional<Rute> r = ruteService.findById(id2,Rute.class);
         PointOfInteres p = repository.findFirstById(id1);
         if(p!=null){
             if(p.getListRute().stream().filter(x->x.getName().equals(r.get().getName())).findFirst().isEmpty()){
                 p.addRuteToPoi(r.get());
                 save(p);
             }
             // Cambiar la excepción
             else throw new EnityUniqueExceptionCustom(Rute.class);
         }
         else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
     }

     public void removePointOfInterest (UUID id){
        PointOfInteres p =repository.findFirstById(id);
        if(p!=null){
            p.getListRute().stream().forEach(x->p.removeRuteToPoi(x));
            p.getListCategory().stream().forEach(x->p.removeCategoryToPoi(x));
            edit(p);
            delete(p,PointOfInteres.class);
        }
        else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
     }

     public void removeRuteToPoi (UUID id1,UUID id2){
         Optional<Rute> r = ruteService.findById(id2,Rute.class);
         PointOfInteres p = repository.findFirstById(id1);
         if(p!=null){
             p.removeRuteToPoi(r.get());
             save(p);
         }
         else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
     }

    public void removeCategoryToPoi (UUID id1,UUID id2){
        Optional<Category> c = categoryService.findById(id2,Category.class);
        PointOfInteres p = repository.findFirstById(id1);
        if(p!=null){
            p.removeCategoryToPoi(c.get());
            save(p);
        }
        else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
    }


}
