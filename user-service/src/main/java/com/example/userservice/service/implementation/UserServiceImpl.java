package com.example.userservice.service.implementation;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import com.example.userservice.entity.ValueObjects.Sector;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    private String token;

    @Override
    public AppUser saveUser(AppUser appUser) {
        return this.userRepository.save(appUser);
    }

    @Override
    public ResponseTemplateVO getUserWithSector(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        AppUser appUser = this.userRepository.findById(userId).get();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<Sector> response = restTemplate.exchange(
            "http://localhost:9001/sector/" + appUser.getSectorId(), HttpMethod.GET, requestEntity, Sector.class);
        vo.setAppUser(appUser);
        vo.setSector(response.getBody());

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
    public void setToken(String token){
        this.token = token;
    }
}
