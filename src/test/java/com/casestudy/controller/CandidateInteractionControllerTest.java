package com.casestudy.controller;

import com.casestudy.dto.CandidateInteractionDto;
import com.casestudy.dto.CandidateInteractionSavingDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.enums.InteractionType;
import com.casestudy.service.CandidateInteractionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CandidateInteractionControllerTest {

    @InjectMocks
    private CandidateInteractionController candidateInteractionController;

    @Mock
    private CandidateInteractionService candidateInteractionService;

    @Test
    void shouldFindInteractionsByCandidateId() {
        Long id = 1L;
        candidateInteractionController.findInteractionsByCandidateId(id);
        Mockito.verify(candidateInteractionService, Mockito.times(1)).findInteractionsByCandidateId(id);
    }

    @Test
    void shouldSaveInteraction() {
        CandidateInteractionSavingDto dto = getCandidateInteractionSavingDto();
        candidateInteractionController.saveInteraction(dto);
        Mockito.verify(candidateInteractionService, Mockito.times(1)).save(dto);
    }

    @Test
    void shouldUpdate() {
        CandidateInteractionDto dto = getCandidateInteractionDto();
        candidateInteractionController.update(dto);
        Mockito.verify(candidateInteractionService, Mockito.times(1)).update(dto);
    }

    @Test
    void shouldDelete() {
        Long id = 1L;
        candidateInteractionController.delete(id);
        Mockito.verify(candidateInteractionService,Mockito.times(1)).delete(id);
    }

    private CandidateInteractionSavingDto getCandidateInteractionSavingDto() {

        CandidateInteractionSavingDto dto = CandidateInteractionSavingDto.builder()
                .candidateId("1")
                .candidateResponded(true)
                .type(InteractionType.EMAIL)
                .content("Current Content")
                .date(LocalDate.now())
                .build();

        return dto;

    }

    private CandidateInteractionDto getCandidateInteractionDto() {
        CandidateInteractionDto dto = CandidateInteractionDto.builder()
                .candidateId("1")
                .candidateResponded(false)
                .content("current content")
                .date(LocalDate.now())
                .id("1")
                .type(InteractionType.PHONE)
                .build();
        return dto;
    }
}