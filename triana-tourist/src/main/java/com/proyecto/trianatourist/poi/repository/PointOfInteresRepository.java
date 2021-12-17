package com.proyecto.trianatourist.poi.repository;

import com.proyecto.trianatourist.poi.model.PointOfInteres;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PointOfInteresRepository extends JpaRepository<PointOfInteres, UUID> {
}
