package com.proyecto.trianatourist.category.repository;

import com.proyecto.trianatourist.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
