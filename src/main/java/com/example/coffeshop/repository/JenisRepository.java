package com.example.coffeshop.repository;

import com.example.coffeshop.model.entity.Jenis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JenisRepository extends JpaRepository<Jenis, String> {
    @Override
    Optional<Jenis> findById (String idJenis);
}
