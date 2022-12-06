package com.example.translucentfe.service;

import com.example.translucentfe.model.NewUser;
import com.example.translucentfe.model.UserWithSectorVO;

public interface UserService {
    UserWithSectorVO getUserWithSector(Long userId);
    NewUser registerUser(NewUser user);
}
