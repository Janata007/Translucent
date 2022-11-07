package com.example.userservice.service.implementation;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import com.example.userservice.entity.ValueObjects.Sector;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public AppUser saveUser(AppUser appUser) {
        return this.userRepository.save(appUser);
    }

    @Override
    public ResponseTemplateVO getUserWithSector(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        AppUser appUser = this.userRepository.findById(userId).get();
        Sector sector =
            restTemplate.getForObject("http://SECTOR-SERVICE/sector/" + appUser.getSectorId(), Sector.class);
        vo.setAppUser(appUser);
        vo.setSector(sector);
        return vo;
    }
}
