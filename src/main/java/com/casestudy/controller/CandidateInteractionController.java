package com.casestudy.controller;

import com.casestudy.dto.CandidateInteractionDto;
import com.casestudy.dto.CandidateInteractionSavingDto;
import com.casestudy.service.CandidateInteractionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/candidate-interactions/")
@AllArgsConstructor
@CrossOrigin
public class CandidateInteractionController {

    private CandidateInteractionService candidateInteractionService;

    @GetMapping("find-by-candidate-id/{id}")
    public ResponseEntity findInteractionsByCandidateId(@PathVariable Long id){
        return candidateInteractionService.findInteractionsByCandidateId(id);
    }
    @PostMapping
    public ResponseEntity saveInteraction(@RequestBody @Valid CandidateInteractionSavingDto candidateInteractionSavingDto){
        return candidateInteractionService.save(candidateInteractionSavingDto);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody @Valid CandidateInteractionDto candidateInteractionDto){
        return candidateInteractionService.update(candidateInteractionDto);
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return candidateInteractionService.delete(id);
    }

}
