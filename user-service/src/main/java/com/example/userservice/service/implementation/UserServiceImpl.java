package com.example.userservice.service.implementation;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import com.example.userservice.entity.ValueObjects.Sector;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
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

    @Override
    public List<AppUser> getAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    public List<AppUser> getAllUsersInSector(Long sectorId) {
        return this.userRepository.findAllBySectorId(sectorId);
    }

    @Override
    public List<AppUser> getAllUsersInCompany(Long companyId) {
        return this.userRepository.findAllByCompanyId(companyId);
    }

    @Override
    public AppUser findByUsername(String username) {
        return this.userRepository.findByUserName(username);
    }

    public AppUser getSimpleUser(Long userId) {
        return this.userRepository.findById(userId).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUserName(username);
    }
}
