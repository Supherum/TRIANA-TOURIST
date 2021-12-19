package com.proyecto.trianatourist.poi.repository;

import com.proyecto.trianatourist.category.model.Category;
import com.proyecto.trianatourist.poi.model.PointOfInteres;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PointOfInteresRepository extends JpaRepository<PointOfInteres, UUID> {

    @EntityGraph(value = "graph-poi-category",type = EntityGraph.EntityGraphType.LOAD)
    PointOfInteres findFirstById(UUID uuid);

 //   @Query("SELECT p FROM PointOfInteres p JOIN FETCH p.listCategory WHERE :categoria IN p.listCategory")
 //   public List<PointOfInteres> findPoiCat (@Param("categoria") Category categoria);

}
