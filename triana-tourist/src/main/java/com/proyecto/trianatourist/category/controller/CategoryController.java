package com.proyecto.trianatourist.category.controller;

import com.proyecto.trianatourist.category.dto.CategoryGenericDto;
import com.proyecto.trianatourist.category.dto.CategoryGenericDtoConverter;
import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.category.service.CategoryService;
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
@RequiredArgsConstructor
@RequestMapping("/category")

public class CategoryController {

    private final PointOfInteresService pointOfInteresService;
    private final CategoryService categoryService;
    private final CategoryGenericDtoConverter converter;
    Class clas=Category.class;


    @GetMapping("")
    public List<CategoryGenericDto> getAllCategory(){
        return categoryService.findAll(clas).stream().map(converter::categoryToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CategoryGenericDto getCategoryById(@PathVariable ("id")UUID id){
        return converter.categoryToDto(categoryService.findById(id,clas).get());
    }

    @PostMapping ("")
    public ResponseEntity<CategoryGenericDto> createCategory (@Valid @RequestBody CategoryGenericDto dto){
       return ResponseEntity.status(HttpStatus.CREATED).body(converter.categoryToDto(categoryService.save(converter.dtoToCategory(dto))));
    }

    @PutMapping("/{id}")
    public CategoryGenericDto editCategory (@Valid @RequestBody CategoryGenericDto dto, @PathVariable ("id") UUID id){
      return  categoryService.edit(dto,id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory (@PathVariable ("id") UUID id){
        categoryService.removeCategory(id);
    }

}
