package com.example.userservice.repository;

import com.example.userservice.entity.Arrangement;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArrangementRepository extends JpaRepository<Arrangement, Long> {
    List<Arrangement> findAllByCreatedByUser(Long userId);
}
