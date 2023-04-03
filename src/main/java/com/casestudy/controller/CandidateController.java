package com.casestudy.controller;

import com.casestudy.dto.CandidateDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.entity.Candidate;
import com.casestudy.enums.CandidateStatus;
import com.casestudy.service.CandidateService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidate/")
@AllArgsConstructor
@CrossOrigin
public class CandidateController {

    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid CandidateWithInteractionDto candidateWithInteractionDto){
        Candidate candidate = candidateService.saveCandidateWithInteraction(candidateWithInteractionDto);
        return ResponseEntity.ok(candidate);
    }

    @GetMapping("name/{name}/surname/{surname}")
    public ResponseEntity<List<Candidate>> findCandidatesByNameAndSurname(@PathVariable String name, @PathVariable String surname){
        return candidateService.findCandidatesByNameAndSurname(name, surname);
    }
    @PutMapping
    public ResponseEntity updateCandidate(@RequestBody @Valid CandidateDto candidateDto){
        Candidate candidate = candidateService.updateCandidate(candidateDto);
        return ResponseEntity.ok(candidate);
    }

    @PutMapping("update-status-by-id/{id}/status/{status}")
    public ResponseEntity updateStatus(@PathVariable("status") CandidateStatus status, @PathVariable("id") Long id){
        return candidateService.updateStatus( status, id );
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity deleteCandidateById(@PathVariable Long id){
        return candidateService.deleteCandidate(id);
    }





}
