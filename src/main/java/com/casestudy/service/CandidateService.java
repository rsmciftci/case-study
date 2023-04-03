package com.casestudy.service;

import com.casestudy.dto.CandidateDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.entity.Candidate;
import com.casestudy.entity.CandidateInteraction;
import com.casestudy.enums.CandidateStatus;
import com.casestudy.mapper.CandidateInteractionMapper;
import com.casestudy.mapper.CandidateMapper;
import com.casestudy.repository.CandidateInteractionRepository;
import com.casestudy.repository.CandidateRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CandidateService {

    private CandidateRepository candidateRepository;
    private CandidateInteractionRepository candidateInteractionRepository;

    @Transactional
    public Candidate saveCandidateWithInteraction(CandidateWithInteractionDto candidateWithInteractionDto){

        Candidate candidate = CandidateMapper.INSTANCE.convertCandidateWithInteractionDtoToCandidate(candidateWithInteractionDto);
        CandidateInteraction candidateInteraction = CandidateInteractionMapper.INSTANCE.
                convertCandidateWithInteractionDtoToCandidateInteraction(candidateWithInteractionDto);


        Candidate savedCandidate = candidateRepository.save(candidate);
        candidateInteraction.setCandidate(savedCandidate);
        candidateInteractionRepository.save(candidateInteraction);

        return savedCandidate;
    }

    public ResponseEntity<List<Candidate>> findCandidatesByNameAndSurname(String name, String surname){
        List<Candidate> candidates = candidateRepository.findCandidatesByNameAndSurname(name, surname);
        candidates.sort(Comparator.comparing(Candidate::getId));

        if(candidates.size() < 1 ){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(candidates);
        }
    }


    public Candidate updateCandidate(CandidateDto candidateDto) {
        Candidate candidate = CandidateMapper.INSTANCE.convertCandidateDtoToCandidate(candidateDto);
        return candidateRepository.save(candidate);
    }

    public ResponseEntity deleteCandidate(Long id) {

        Optional<Candidate> optionalCandidate = candidateRepository.findById(id);

        if(optionalCandidate.isPresent()){
            candidateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity updateStatus(CandidateStatus status, Long id) {

        Optional<Candidate> candidateOptional = candidateRepository.findById(id);

        if(candidateOptional.isPresent()){
            candidateRepository.updateStatusById(status,id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
