package com.casestudy.dto;

import com.casestudy.enums.InteractionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@Builder
public class CandidateInteractionDto {
    @NotNull(message = "Id is missing.")
    private String id;

    @NotNull(message = "Candidate Id is missing.")
    private String candidateId;

    private InteractionType type;
    @NotNull
    private String content;
    @PastOrPresent
    private LocalDate date;
    @NotNull( message = "Candidate response is missing.")
    private boolean candidateResponded;
}
