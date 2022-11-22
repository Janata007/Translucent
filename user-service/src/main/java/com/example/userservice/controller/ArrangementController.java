package com.example.userservice.controller;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.Arrangement;
import com.example.userservice.entity.ValueObjects.NewArrangementRequestTemplateVO;
import com.example.userservice.service.implementation.ArrangementServiceImpl;
import com.example.userservice.service.implementation.UserServiceImpl;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrangements")
@Slf4j
public class ArrangementController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private ArrangementServiceImpl arrangementService;

    @PostMapping("/{userId}")
    public Arrangement saveNewArrangement(@RequestBody NewArrangementRequestTemplateVO a, @PathVariable Long userId) {
        log.info("saveArrangement in ArrangementController");
        AppUser user = this.userService.getSimpleUser(userId);
        Arrangement newA = new Arrangement();
        newA.setCode(a.getCode());
        newA.setName(a.getName());
        newA.setDuration(a.getDuration());
        newA.setStartTime(a.getStartTime());
        newA.setEndTime(a.getEndTime());
        newA.setPriority(a.getPriority());
        newA.setParticipants(Arrays.asList(user));
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
}
