package com.example.userservice.repository;

import com.example.userservice.entity.AppUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    List<AppUser> findAllBySectorId(Long id);
    List<AppUser> findAllByCompanyId(Long id);
    AppUser findByUserName(String username);
}
