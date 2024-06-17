package com.example.userservice.service;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.Arrangement;
import java.util.List;

public interface ArrangementService {

    Arrangement saveArrangement(Arrangement arrangement);
    List<Arrangement> getArrangementsByUserId(Long userId);
    List<Arrangement> getArrangementsByUserIdParticipant(Long userId);
    Arrangement findById(Long id);
    Arrangement addNewParticipant(Arrangement a, AppUser u);
    List<Arrangement> getAllArrangements();
}
