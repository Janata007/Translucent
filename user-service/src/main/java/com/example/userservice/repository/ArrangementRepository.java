package com.example.userservice.repository;

import com.example.userservice.entity.Arrangement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrangementRepository extends JpaRepository<Arrangement, Long> {
}
