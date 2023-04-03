package com.casestudy.dto;

import com.casestudy.enums.CandidateStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class CandidateDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    private String middleName;
    @NotNull
    private String surname;
    @NotNull
    @Pattern(regexp = "[0-9]+")
    private String phoneNumber;
    @Email
    private String email;
    private CandidateStatus status;
}
