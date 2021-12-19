package com.proyecto.trianatourist.category.repository;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.poi.model.PointOfInteres;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    @EntityGraph(value = "graph-category-poi",type = EntityGraph.EntityGraphType.FETCH)
    Category findFirstById (UUID id);



}
