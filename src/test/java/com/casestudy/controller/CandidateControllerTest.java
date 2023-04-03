package com.casestudy.controller;

import com.casestudy.dto.CandidateDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.entity.Candidate;
import com.casestudy.enums.CandidateStatus;
import com.casestudy.enums.InteractionType;
import com.casestudy.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {
    @InjectMocks
    private CandidateController candidateController;
    @Mock
    private CandidateService candidateService;

    @Test
    void shouldSave() {
        CandidateWithInteractionDto candidateWithInteractionDto = getCandidateWithInteractionDto();
        ResponseEntity response = candidateController.save(candidateWithInteractionDto);
        assertEquals(HttpStatus.valueOf(200),response.getStatusCode());
        Mockito.verify(candidateService, Mockito.times(1)).saveCandidateWithInteraction(candidateWithInteractionDto);
    }

    @Test
    void shouldFindCandidatesByNameAndSurname() {
        String name = "Rasim";
        String surname = "Ciftci";
        ResponseEntity<List<Candidate>> response = candidateController.findCandidatesByNameAndSurname(name, surname);
        Mockito.verify(candidateService, Mockito.times(1)).findCandidatesByNameAndSurname(name,surname);
    }

    @Test
    void shouldUpdateCandidate() {
        CandidateDto candidateDto = getCandidateDto();
        ResponseEntity response = candidateController.updateCandidate(candidateDto);
        assertEquals(HttpStatus.valueOf(200),response.getStatusCode());
        Mockito.verify(candidateService, Mockito.times(1)).updateCandidate(candidateDto);
    }



    @Test
    void shouldUpdateStatus() {
        CandidateStatus candidateStatus = CandidateStatus.INTERVIEWING;
        ResponseEntity responseEntity = candidateController.updateStatus(candidateStatus, 1L);
        Mockito.verify(candidateService, Mockito.times(1)).updateStatus(candidateStatus, 1L);
    }

    @Test
    void shouldDeleteCandidateById() {
        Long id = 1L;
        ResponseEntity responseEntity = candidateController.deleteCandidateById(id);
        Mockito.verify(candidateService, Mockito.times(1)).deleteCandidate(id);
    }

    public CandidateWithInteractionDto getCandidateWithInteractionDto(){
        CandidateWithInteractionDto candidateWithInteractionDto = CandidateWithInteractionDto.builder()
                .name("Rasim")
                .surname("Ciftci")
                .phoneNumber("05413735686")
                .email("rsmciftci@gmail.com")
                .type(InteractionType.PHONE)
                .content("initial content")
                .date(LocalDate.now())
                .candidateResponded(false)
                .status(CandidateStatus.SOURCED)
                .build();
        return candidateWithInteractionDto;
    }

    private CandidateDto getCandidateDto() {
        CandidateDto candidateDto = CandidateDto.builder()
                .id(1L)
                .name("Rasim")
                .surname("Ciftci")
                .phoneNumber("05413735686")
                .email("rsmciftci@gmail.com")
                .status(CandidateStatus.HIRED)
                .build();

        return candidateDto;
    }
}