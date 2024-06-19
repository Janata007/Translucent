package com.example.userservice.service.implementation;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.Arrangement;
import com.example.userservice.repository.ArrangementRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.ArrangementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;

@Service
@CrossOrigin
public class ArrangementServiceImpl implements ArrangementService {
    @Autowired
    private ArrangementRepository arrangementRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Arrangement saveArrangement(Arrangement arrangement) {
        return this.arrangementRepository.save(arrangement);
    }

    @Override
    public List<Arrangement> getArrangementsByUserId(Long userId) {
        AppUser user = this.userRepository.findById(userId).orElseThrow();
        return this.arrangementRepository.findAllByCreatedByUser(userId);
    }
    @Override
    public List<Arrangement> getAllArrangements() {
        return this.arrangementRepository.findAll();
    }

    @Override
    public List<Arrangement> getArrangementsByUserIdParticipant(Long userId) {
        List<Arrangement> all = this.arrangementRepository.findAll();
        List<Arrangement> result = new ArrayList<Arrangement>();
        for (Arrangement a : all) {
            for (AppUser u : a.getParticipants()) {
                if (u.getUserId().equals(userId)) {
                    result.add(a);
                }
            }
        }
        return result;
    }

    @Override
    public Arrangement findById(Long id) {
        return this.arrangementRepository.findById(id).orElseThrow();
    }

    @Override
    public Arrangement addNewParticipant(Arrangement a, AppUser u) {
        List<AppUser> participants = a.getParticipants();
        boolean existsFlag = false;
        for (AppUser user : participants) {
            if (user.equals(u)) {
                existsFlag = true;
            }
        }
        if (!existsFlag) {
            a.addNewParticipant(u);
            this.arrangementRepository.save(a);
        }
        return a;
    }
}
