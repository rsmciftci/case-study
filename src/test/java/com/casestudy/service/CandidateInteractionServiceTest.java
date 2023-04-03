package com.casestudy.service;

import com.casestudy.dto.CandidateInteractionDto;
import com.casestudy.dto.CandidateInteractionSavingDto;
import com.casestudy.entity.Candidate;
import com.casestudy.entity.CandidateInteraction;
import com.casestudy.enums.CandidateStatus;
import com.casestudy.enums.InteractionType;
import com.casestudy.mapper.CandidateInteractionMapper;
import com.casestudy.repository.CandidateInteractionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CandidateInteractionServiceTest {

    @InjectMocks
    private CandidateInteractionService candidateInteractionService;
    @Mock
    private CandidateInteractionRepository candidateInteractionRepository;

    @Test
    void shouldFindInteractionsByCandidateId_IfCandidateExist() {
        Long id = 1L;
        List<CandidateInteraction> interactions = getCandidateIntertionList();
        Mockito.when(candidateInteractionRepository.findCandidateInteractionsByCandidateId(id)).thenReturn(interactions);

        ResponseEntity response = candidateInteractionService.findInteractionsByCandidateId(id);
        String responseString = response.getBody().toString();

        assertEquals(interactions.toString(), responseString);
        assertEquals(HttpStatus.valueOf(200),response.getStatusCode());

    }
    @Test
    void shouldFindInteractionsByCandidateId_IfCandidateDoesntExitst() {

        Long id = 1L;
        List<CandidateInteraction> interactions = new ArrayList<>();

        Mockito.when(candidateInteractionRepository.findCandidateInteractionsByCandidateId(id)).thenReturn(interactions);

        ResponseEntity response = candidateInteractionService.findInteractionsByCandidateId(id);

        assertEquals(HttpStatus.valueOf(404),response.getStatusCode());

    }

    @Test
    void shouldSave() {

        CandidateInteractionSavingDto candidateInteractionSavingDto = getCandidateInteractionSavingDto();
        CandidateInteraction candidateInteraction = getCandidateInteraction(candidateInteractionSavingDto);

        Mockito.when(candidateInteractionRepository.save(Mockito.any(CandidateInteraction.class))).thenReturn(candidateInteraction);
        ResponseEntity response = candidateInteractionService.save(candidateInteractionSavingDto);

        assertEquals(candidateInteraction.toString(),response.getBody().toString());
        assertEquals(HttpStatus.valueOf(200),response.getStatusCode());
    }

    @Test
    void shouldUpdate() {

        CandidateInteractionDto candidateInteractionDto = getCandidateInteractionDto();
        CandidateInteraction candidateInteraction = getInteraction();
        Mockito.when(candidateInteractionRepository.save(Mockito.any(CandidateInteraction.class))).thenReturn(candidateInteraction);
        ResponseEntity response = candidateInteractionService.update(candidateInteractionDto);

        assertEquals(candidateInteraction.toString(), response.getBody().toString());
        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());

    }

    @Test
    void shouldDelete_IfCandidateExist() {
        Long id = 1L;
        Optional<CandidateInteraction> optinalInteraction = Optional.of(getInteraction());

        Mockito.when(candidateInteractionRepository.findById(id)).thenReturn(optinalInteraction);
        candidateInteractionRepository.deleteById(id);

        ResponseEntity response = candidateInteractionService.delete(1L);
        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());
    }

    @Test
    void shouldDelete_IfCandidateDoesntExist() {

        Long id = 1L;
        Optional<CandidateInteraction> optinalInteraction = Optional.empty();

        Mockito.when(candidateInteractionRepository.findById(id)).thenReturn(optinalInteraction);
        candidateInteractionRepository.deleteById(id);

        ResponseEntity response = candidateInteractionService.delete(1L);
        assertEquals(HttpStatus.valueOf(404), response.getStatusCode());
    }

    private List<CandidateInteraction> getCandidateIntertionList() {
        Candidate candidate = new Candidate();
        CandidateInteraction interaction1 = CandidateInteraction.builder()
                .id(1L)
                .candidate(candidate)
                .candidateResponded(false)
                .type(InteractionType.EMAIL)
                .content("content1")
                .date(LocalDate.now())
                .build();
        CandidateInteraction interaction2 = CandidateInteraction.builder()
                .id(1L)
                .candidate(candidate)
                .candidateResponded(false)
                .type(InteractionType.EMAIL)
                .content("content1")
                .date(LocalDate.now())
                .build();

        List<CandidateInteraction> interactions = new ArrayList<>();
        interactions.add(interaction1);
        interactions.add(interaction2);

        return interactions;
    }

    private CandidateInteractionSavingDto getCandidateInteractionSavingDto() {

        CandidateInteractionSavingDto dto = CandidateInteractionSavingDto.builder()
                .candidateId("1")
                .candidateResponded(true)
                .type(InteractionType.EMAIL)
                .date(LocalDate.now())
                .content("Current Content")
                .build();

        return dto;
    }

    private CandidateInteraction getCandidateInteraction(CandidateInteractionSavingDto candidateInteractionSavingDto) {

        CandidateInteraction candidateInteraction = CandidateInteractionMapper.INSTANCE
                .convertCandidateInteractionSavingDtoToCandidateInteraction(candidateInteractionSavingDto);

        return candidateInteraction;
    }

    private CandidateInteraction getInteraction() {
        CandidateInteraction interaction = CandidateInteractionMapper.INSTANCE
                .convertCandidateInteractionDtoToCandidateInteraction(getCandidateInteractionDto());
        return interaction;
    }

    private CandidateInteractionDto getCandidateInteractionDto() {
        CandidateInteractionDto candidateInteractionDto = CandidateInteractionDto.builder()
                .id("1")
                .candidateId("1")
                .type(InteractionType.EMAIL)
                .candidateResponded(true)
                .content("Current Content")
                .date(LocalDate.now())
                .build();
        return candidateInteractionDto;
    }
}