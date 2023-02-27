package ru.min.cbrfdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.cbrfdata.model.Model;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModelRepository extends JpaRepository<Model, UUID> {
    Optional<Model> findByLocalDate(LocalDate date);
    Model getById(UUID uuid);
}
