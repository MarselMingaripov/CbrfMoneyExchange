package ru.min.cbrfdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.min.cbrfdata.model.Data;


import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DataRepository extends JpaRepository<Data, UUID> {
}
