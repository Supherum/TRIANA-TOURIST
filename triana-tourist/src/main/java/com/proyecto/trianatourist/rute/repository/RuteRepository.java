package com.proyecto.trianatourist.rute.repository;

import com.proyecto.trianatourist.rute.model.Rute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RuteRepository extends JpaRepository<Rute, UUID> {
}
