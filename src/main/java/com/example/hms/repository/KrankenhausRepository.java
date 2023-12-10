package com.example.hms.repository;

import com.example.hms.Model.Krankenhaus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KrankenhausRepository extends JpaRepository<Krankenhaus, Integer> {
    Optional<Krankenhaus> findByName(String name);


}
