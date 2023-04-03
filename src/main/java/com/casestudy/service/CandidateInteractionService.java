package com.casestudy.service;

import com.casestudy.dto.CandidateInteractionDto;
import com.casestudy.dto.CandidateInteractionSavingDto;
import com.casestudy.entity.CandidateInteraction;
import com.casestudy.mapper.CandidateInteractionMapper;
import com.casestudy.repository.CandidateInteractionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateInteractionService {

    private CandidateInteractionRepository candidateInteractionRepository;
    public ResponseEntity findInteractionsByCandidateId(Long id) {

        List<CandidateInteraction> candidateInteractions =
                candidateInteractionRepository.findCandidateInteractionsByCandidateId(id);
        candidateInteractions.sort(Comparator.comparing(CandidateInteraction::getId));

        if( candidateInteractions.size() == 0 ){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(candidateInteractions);
        }
    }

    public ResponseEntity save(CandidateInteractionSavingDto candidateInteractionSavingDto ){

        CandidateInteraction candidateInteraction = CandidateInteractionMapper.INSTANCE
                .convertCandidateInteractionSavingDtoToCandidateInteraction(candidateInteractionSavingDto);

        CandidateInteraction savedInteraction = candidateInteractionRepository.save(candidateInteraction);

        return ResponseEntity.ok(savedInteraction);
    }

    public ResponseEntity update(CandidateInteractionDto candidateInteractionDto) {

        CandidateInteraction candidateInteraction = CandidateInteractionMapper.INSTANCE
                .convertCandidateInteractionDtoToCandidateInteraction(candidateInteractionDto);
        CandidateInteraction updatedInteraction = candidateInteractionRepository.save(candidateInteraction);

        return ResponseEntity.ok(updatedInteraction);
    }

    public ResponseEntity delete(Long id) {

        Optional<CandidateInteraction> optinalInteraction = candidateInteractionRepository.findById(id);

        if( optinalInteraction.isPresent() ){
            candidateInteractionRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }


    }
}
