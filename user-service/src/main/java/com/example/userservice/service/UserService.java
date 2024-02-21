package com.example.userservice.service;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import java.util.List;

public interface UserService {

    AppUser saveUser(AppUser appUser);
    List<AppUser> getUsersByUsername(String username);

    ResponseTemplateVO getUserWithSector(Long userId);
    List<AppUser> getAllUsers();
    List<AppUser> getAllUsersInSector(Long sectorId);
    List<AppUser> getAllUsersInCompany(Long companyId);
    AppUser findByUsername(String username);
}
