package com.proyecto.trianatourist.category.service;

import com.proyecto.trianatourist.category.dto.CategoryGenericDto;
import com.proyecto.trianatourist.category.dto.CategoryGenericDtoConverter;
import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.category.repository.CategoryRepository;
import com.proyecto.trianatourist.error.exceptions.EntityNotFoundExceptionCustom;
import com.proyecto.trianatourist.poi.model.PointOfInteres;
import com.proyecto.trianatourist.poi.repository.PointOfInteresRepository;
import com.proyecto.trianatourist.shared.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Log
@Service
@RequiredArgsConstructor
public class CategoryService extends BaseService<Category, UUID, CategoryRepository> {

    private final CategoryGenericDtoConverter converter;
    private final PointOfInteresRepository pointOfInteresRepository;

    public CategoryGenericDto edit(CategoryGenericDto dto, UUID id) {
        Optional<Category> category = findById(id, Category.class);

        category.get().setName(dto.getName());
        return converter.categoryToDto(repository.save(category.get()));
    }

    public void removeCategory (UUID id){
        Category c = repository.findFirstById(id);
        if(c!=null){
           List<UUID> listaId = c.getListPointOfInterest().stream().map(x-> x.getId()).collect(Collectors.toList());
           List<PointOfInteres> listaPoi =new ArrayList<>();
           listaId.forEach(x->listaPoi.add(pointOfInteresRepository.findFirstById(x)));
           listaPoi.forEach(x->x.removeCategoryToPoi(c));
           listaPoi.forEach(x->pointOfInteresRepository.save(x));
           delete(c,Category.class);
        }
        else throw new EntityNotFoundExceptionCustom(PointOfInteres.class);
    }

}
