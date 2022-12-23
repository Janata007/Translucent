package com.example.userservice.service.implementation;

import com.example.userservice.entity.AppUser;
import com.example.userservice.entity.Arrangement;
import com.example.userservice.repository.ArrangementRepository;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.ArrangementService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
        }
        return a;
    }
}
