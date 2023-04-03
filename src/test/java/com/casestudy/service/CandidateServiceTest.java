package com.casestudy.service;

import com.casestudy.dto.CandidateDto;
import com.casestudy.dto.CandidateInteractionDto;
import com.casestudy.dto.CandidateWithInteractionDto;
import com.casestudy.entity.Candidate;
import com.casestudy.entity.CandidateInteraction;
import com.casestudy.enums.CandidateStatus;
import com.casestudy.enums.InteractionType;
import com.casestudy.mapper.CandidateInteractionMapper;
import com.casestudy.mapper.CandidateMapper;
import com.casestudy.repository.CandidateInteractionRepository;
import com.casestudy.repository.CandidateRepository;
import net.bytebuddy.asm.Advice;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @InjectMocks
    private CandidateService candidateService;
    @Mock
    private CandidateInteractionRepository candidateInteractionRepository;
    @Mock
    private CandidateRepository candidateRepository;

    @Test
    void shouldSaveCandidateWithInteraction() {

        CandidateWithInteractionDto dto = getCandidateWithInteractionDto();
        Candidate candidate = CandidateMapper.INSTANCE.convertCandidateWithInteractionDtoToCandidate(dto);

        Mockito.when(candidateRepository.save(Mockito.any(Candidate.class))).thenReturn(candidate);

        Candidate candidateResponse = candidateService.saveCandidateWithInteraction(dto);
        assertEquals(candidate, candidateResponse);

    }

    @Test
    void shouldFindCandidatesByNameAndSurname_IfCandidateExist() {
        String name = "Rasim";
        String surname = "Ciftci";
        List<Candidate> dummyCandidateList = getDummyCandidateList();
        Mockito.when(candidateRepository.findCandidatesByNameAndSurname(name,surname)).thenReturn(dummyCandidateList);

        ResponseEntity<List<Candidate>> candidateList = candidateService.findCandidatesByNameAndSurname(name, surname);

        assertEquals(dummyCandidateList, candidateList.getBody());
        assertEquals(HttpStatus.valueOf(200),candidateList.getStatusCode());

    }

    @Test
    void shouldFindCandidatesByNameAndSurname_IfCandidateDoenstExist() {
        String name = "Rasim";
        String surname = "Ciftci";
        List<Candidate> candidateList = null;

        ResponseEntity<List<Candidate>> candidateListResponse = candidateService.findCandidatesByNameAndSurname(name, surname);

        assertEquals(HttpStatus.valueOf(404),candidateListResponse.getStatusCode());

    }
    @Test
    void shouldUpdateCandidate() {

        CandidateDto candidateDto = getCandidateDto();
        Candidate candidate = CandidateMapper.INSTANCE.convertCandidateDtoToCandidate(candidateDto);

        Mockito.when(candidateRepository.save(Mockito.any(Candidate.class))).thenReturn(candidate);
        Candidate candidateResponse = candidateService.updateCandidate(candidateDto);

        assertEquals(candidate,candidateResponse);
    }

    @Test
    void shouldDeleteCandidate_IfCandidateExist() {

        Long id = 1L;
        Optional<Candidate> optionalCandidate = Optional.of(getCandidate());

        Mockito.when(candidateRepository.findById(id)).thenReturn(optionalCandidate);
        ResponseEntity response = candidateService.deleteCandidate(id);

        assertEquals(HttpStatus.valueOf(200), response.getStatusCode());
    }

    @Test
    void shouldDeleteCandidate_IfCandidateDoesntExist() {

        Long id = 1L;
        Optional<Candidate> optionalCandidate = Optional.empty();

        Mockito.when(candidateRepository.findById(id)).thenReturn(optionalCandidate);
        ResponseEntity response = candidateService.deleteCandidate(id);

        assertEquals(HttpStatus.valueOf(404), response.getStatusCode());
    }

    @Test
    void shouldUpdateStatus_IfCandidateExist() {
        CandidateStatus status = CandidateStatus.SOURCED;
        Long id = 1L;
        Optional<Candidate> optionalCandidate = Optional.of(getCandidate());

        Mockito.when(candidateRepository.findById(id)).thenReturn(optionalCandidate);

        ResponseEntity response = candidateService.updateStatus(status, id);

        assertEquals(HttpStatus.valueOf(200),response.getStatusCode());
    }

    @Test
    void shouldUpdateStatus_IfCandidateDoesntExist() {
        CandidateStatus status = CandidateStatus.SOURCED;
        Long id = 1L;
        Optional<Candidate> optionalCandidate = Optional.empty();

        Mockito.when(candidateRepository.findById(id)).thenReturn(optionalCandidate);

        ResponseEntity response = candidateService.updateStatus(status, id);

        assertEquals(HttpStatus.valueOf(404),response.getStatusCode());
    }

    private CandidateWithInteractionDto getCandidateWithInteractionDto() {
        CandidateWithInteractionDto dto = CandidateWithInteractionDto.builder()
                .name("Rasim")
                .surname("Ciftci")
                .email("rsmciftci@gmail.com")
                .phoneNumber("05413735686")
                .type(InteractionType.EMAIL)
                .candidateResponded(false)
                .status(CandidateStatus.SOURCED)
                .date(LocalDate.now())
                .content("Current Content")
                .build();

        return dto;
    }

    private List<Candidate> getDummyCandidateList() {

        List<Candidate> list = new ArrayList<>();

        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("Rasim");
        candidate.setStatus(CandidateStatus.SOURCED);
        candidate.setEmail("rsmciftci@gmail.com");
        candidate.setPhoneNumber("05413735686");

        list.add(candidate);
        return list;
    }
    private CandidateDto getCandidateDto() {
        CandidateDto candidateDto = CandidateDto.builder()
                .id(1L)
                .name("Rasim")
                .surname("Ciftci")
                .status(CandidateStatus.INTERVIEWING)
                .email("rsmciftci@gmail.com")
                .build();
        return  candidateDto;
    }
    private Candidate getCandidate() {
        Candidate candidate = new Candidate();
        candidate.setId(1L);
        candidate.setName("Rasim");
        candidate.setSurname("Ciftci");
        candidate.setEmail("rsmciftci@gmail.com");
        candidate.setPhoneNumber("05413735686");
        candidate.setStatus(CandidateStatus.SOURCED);
        return  candidate;
    }
}