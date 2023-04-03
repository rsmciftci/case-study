package com.casestudy.dto;

import com.casestudy.enums.CandidateStatus;
import com.casestudy.enums.InteractionType;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CandidateWithInteractionDto {

    @NotNull
    @Size(max = 50)
    private String name;
    private String middleName;
    @NotNull
    @Size(max = 50)
    private String surname;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;
    @Email
    private String email;
    private InteractionType type;

    @Size(max = 2000)
    private String content;
    @PastOrPresent
    private LocalDate date;
    private boolean candidateResponded;

    private CandidateStatus status;
}
