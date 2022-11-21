package com.example.userservice.service;

import com.example.userservice.entity.Arrangement;
import java.util.List;

public interface ArrangementService {

    Arrangement saveArrangement(Arrangement arrangement);
    List<Arrangement> getArrangementsByUserId(Long userId);
}
