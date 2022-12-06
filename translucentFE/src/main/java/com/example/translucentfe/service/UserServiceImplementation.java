package com.example.translucentfe.service;

import com.example.translucentfe.model.NewUser;
import com.example.translucentfe.model.UserWithSectorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserWithSectorVO getUserWithSector(Long userId) {
        UserWithSectorVO vo = new UserWithSectorVO();
        vo =
            restTemplate.getForObject("http://9191/users/" + userId, UserWithSectorVO.class);
        return vo;
    }
    @Override
    public NewUser registerUser(NewUser user) {
        NewUser created = restTemplate.postForObject("http://9191/users/", user, NewUser.class);
        return created;
    }
}
