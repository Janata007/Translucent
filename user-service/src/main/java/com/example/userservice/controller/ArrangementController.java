package com.example.userservice.controller;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.Arrangement;
import com.example.userservice.entity.ValueObjects.NewArrangementRequestTemplateVO;
import com.example.userservice.service.implementation.ArrangementServiceImpl;
import com.example.userservice.service.implementation.UserServiceImpl;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/arrangements")
@Slf4j
@CrossOrigin
public class ArrangementController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ArrangementServiceImpl arrangementService;

    @GetMapping("/all/{userId}")
    public List<Arrangement> getAllArangementsForUser(@PathVariable Long userId) {
        log.info("Getting arrangements for user...");
        return this.arrangementService.getArrangementsByUserId(userId);
    }
    @GetMapping("/all/username/{username}")
    public List<Arrangement> getAllArangementsForUserByName(@PathVariable String username) {
        log.info("Getting arrangements for user...");
        AppUser user= this.userService.findByUsername(username);
        return this.arrangementService.getArrangementsByUserId(user.getUserId());
    }
    @GetMapping("/all")
    public List<Arrangement> getAllArangements() {
        log.info("Getting arrangements...");
        return this.arrangementService.getAllArrangements();
    }
    @GetMapping("/participate/{userId}")
    public List<Arrangement> getAllArangementsForParticipantUser(@PathVariable Long userId) {
        log.info("Getting arrangements for user as participant...");
        return this.arrangementService.getArrangementsByUserIdParticipant(userId);
    }

    @PostMapping("/{userId}")
    public Arrangement saveNewArrangement(@RequestBody NewArrangementRequestTemplateVO a, @PathVariable Long userId) {
        log.info("saveArrangement in ArrangementController");
        AppUser user = this.userService.getSimpleUser(userId);
        List<Arrangement> existing = this.arrangementService.getArrangementsByUserId(userId);
        for (Arrangement ar : existing) {
            if (ar.getName().equals(a.getName()) && ar.getCode().equals(a.getCode())) {
                return null;
            }
        }
        Arrangement newA = new Arrangement();
        newA.setCode(a.getCode());
        newA.setName(a.getName());
        newA.setDuration(a.getDuration());
        newA.setStartTime(a.getStartTime());
        newA.setEndTime(a.getEndTime());
        newA.setPriority(a.getPriority());
        newA.setParticipants(Arrays.asList(user));
        newA.setCreatedByUser(user.getUserId());
        return this.arrangementService.saveArrangement(newA);
    }

    @PostMapping("/addParticipant/{userId}/{arrangementId}")
    public Arrangement addParticipantToArrangement(@PathVariable Long userId, @PathVariable Long arrangementId) {
        log.info("adding participant in ArrangementController");
        AppUser user = this.userService.getSimpleUser(userId);
        Arrangement a = this.arrangementService.findById(arrangementId);
        a = this.arrangementService.addNewParticipant(a, user);
        return a;
    }

    @PostMapping("/removeParticipant/{userId}/{arrangementId}")
    public Arrangement removeParticipantToArrangement(@PathVariable Long userId, @PathVariable Long arrangementId) {
        log.info("adding participant in ArrangementController");
        AppUser user = this.userService.getSimpleUser(userId);
        Arrangement a = this.arrangementService.findById(arrangementId);
        a.removeParticipant(user);
        return a;
    }
    @PostMapping("/removeArrangement/{arrangementId}")
    public void removeArrangement(@PathVariable Long arrangementId) {
        log.info("removing an arrangement in ArrangementController");
        this.arrangementService.removeArrangement(arrangementId);
    }
}
