package com.example.userservice.service;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;

public interface UserService {

    AppUser saveUser(AppUser appUser);

    ResponseTemplateVO getUserWithSector(Long userId);
}
