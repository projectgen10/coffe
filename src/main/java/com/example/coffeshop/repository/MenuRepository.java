package com.example.coffeshop.repository;

import com.example.coffeshop.model.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String> {
    Optional<Menu> findByidMenu (String idMenu);
}
