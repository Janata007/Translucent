package com.example.userservice.service.implementation;

import com.example.userservice.AppUserRole;
import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.ValueObjects.NewUserRequestTemplateVO;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import com.example.userservice.entity.ValueObjects.Sector;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import java.util.ArrayList;
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
        appUser.setRole(AppUserRole.NORMAL);
        return this.userRepository.save(appUser);
    }

    @Override
    public List<AppUser> getUsersByUsername(String username) {
        List<AppUser> userList = new ArrayList<>();
        for(AppUser appUser:this.userRepository.findAll()){
            if(appUser.getUsername().equals(username)){
                userList.add(appUser);
            }
        }
        return userList;
    }

    @Override
    public ResponseTemplateVO getUserWithSector(Long userId) {
        ResponseTemplateVO vo = new ResponseTemplateVO();
        AppUser appUser = this.userRepository.findById(userId).get();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        if(!(appUser.getSectorId() ==null)) {
            ResponseEntity<Sector> response = restTemplate.exchange(
                    "http://localhost:9001/sector/" + appUser.getSectorId(), HttpMethod.GET, requestEntity, Sector.class);
            vo.setAppUser(appUser);
            vo.setSector(response.getBody());
        }else{
            vo.setAppUser(appUser);
            vo.setSector(null);
        }

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

    @Override
    public AppUser deleteUser(Long userId) {
        AppUser user = this.userRepository.findById(userId).orElseThrow();
        this.userRepository.deleteById(userId);
        return user;
    }

    @Override
    public AppUser updateUser(Long id, NewUserRequestTemplateVO appUser) {
        AppUser updated = new AppUser();
        updated.setUserName(appUser.getUserName());
        updated.setFirstName(appUser.getFirstName());
        updated.setEmail(appUser.getEmail());
        updated.setPassword(appUser.getPassword());
        updated.setUserId(id);
        updated.setSectorId(appUser.getSectorId());
        updated.setCompanyId(appUser.getCompanyId());
        updated.setSuperiorId(appUser.getSuperiorId());
        return this.userRepository.save(updated);
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
