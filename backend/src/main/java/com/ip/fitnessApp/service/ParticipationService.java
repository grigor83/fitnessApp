package com.ip.fitnessApp.service;

import com.ip.fitnessApp.model.Participation;
import com.ip.fitnessApp.repository.ParticipationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ParticipationService {
    private final ParticipationRepository participationRepository;

    @Autowired
    public ParticipationService(ParticipationRepository participationRepository) {
        this.participationRepository = participationRepository;
    }

    public List<Participation> findAll(){
        return participationRepository.findAll();
    }

    public Participation createParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

}
