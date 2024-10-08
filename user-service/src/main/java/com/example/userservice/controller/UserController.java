package com.example.userservice.controller;

import com.example.userservice.config.JWTUtil;
import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.JwtRequest;
import com.example.userservice.entity.JwtResponse;
import com.example.userservice.entity.ValueObjects.NewUserRequestTemplateVO;
import com.example.userservice.entity.ValueObjects.ResponseTemplateVO;
import com.example.userservice.service.implementation.UserServiceImpl;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithSector(@PathVariable("id") Long userId) {
        log.info("Fetching user..");
        return this.userService.getUserWithSector(userId);
    }
    @PostMapping("/save")
    public AppUser saveUser(@RequestBody NewUserRequestTemplateVO appUser) {
        log.info("Saving user..");
        for (AppUser user : this.userService.getAllUsers()) {
            if (appUser.getUserName().equals(user.getUsername())) {
                return null;
            }
        }
        AppUser newUser = new AppUser();
        newUser.setUserName(appUser.getUserName());
        newUser.setFirstName(appUser.getFirstName());
        newUser.setLastName(appUser.getLastName());
        newUser.setEmail(appUser.getEmail());
        if(appUser.getSectorId() == 0){
            newUser.setSectorId(82L);
        }else{
        newUser.setSectorId(appUser.getSectorId());}
        newUser.setPassword(appUser.getPassword());
        newUser.setArrangements(new ArrayList<>());
        if(appUser.getSuperiorId() ==null){
            newUser.setSuperiorId(65L);
        }
        if(appUser.getCompanyId()==null){
            newUser.setCompanyId(39L);
        }
        return this.userService.saveUser(newUser);
    }
    @PostMapping("/update/{id}")
    public AppUser updateUser(@PathVariable("id") Long id, @RequestBody NewUserRequestTemplateVO appUser){
    return this.userService.updateUser(id, appUser);
    }
    @PostMapping("/delete/{id}")
    public AppUser deleteUser(@PathVariable("id") Long id){
        return this.userService.deleteUser(id);
    }

    @GetMapping("/simpleUser/{id}")
    public AppUser getUser(@PathVariable("id") Long userId) {
        return this.userService.getSimpleUser(userId);
    }

    @GetMapping("/user")
    public AppUser getUserByUsername(@RequestParam("username") String username) {
        log.info("Fetching user..");
        return this.userService.findByUsername(username);
    }
    @GetMapping("/by")
    public List<AppUser> getUsersByUsernameInput(@RequestParam("username") String username){
        log.info("Fetching users by username..");
        return this.userService.getUsersByUsername(username);
    }

    @PutMapping("/setWorkVisible/{id}")
    public AppUser setWorkVisibleForUser(@PathVariable("id") Long id, @Param("visible") boolean visible) {
        log.info("Setting work visible for user..");
        AppUser user = this.userService.getSimpleUser(id);
        user.setWorkVisible(visible);
        return this.userService.saveUser(user);
    }

    @GetMapping("/all")
    public List<AppUser> getAllUsers() {
        log.info("Fetching all users..");
        return this.userService.getAllUsers();
    }

    @GetMapping("/all/sector/{sectorId}")
    public List<AppUser> getAllUsersInSector(@PathVariable Long sectorId) {
        log.info("Fetching all users in sector..");
        return this.userService.getAllUsersInSector(sectorId);
    }

    @GetMapping("/all/company/{companyId}")
    public List<AppUser> getAllUsersInCompany(@PathVariable Long companyId) {
        log.info("Fetching all users in company..");
        return this.userService.getAllUsersInCompany(companyId);
    }
    @PostMapping("/authenticate")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {
        log.info("Authenticating user..");
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

        final UserDetails userDetails
            = userService.loadUserByUsername(jwtRequest.getUsername());

        final String token =
            jwtUtil.generateToken(userDetails);
        userService.setToken(token);
        List<AppUser> users = this.userService.getUsersByUsername(jwtRequest.getUsername());
        return new JwtResponse(token, users.get(0).getUserId());
    }
}
