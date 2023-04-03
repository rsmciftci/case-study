package com.casestudy.entity;

import com.casestudy.enums.InteractionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateInteraction{


    @Id
    @SequenceGenerator(name = "seq_candidate_interaction",allocationSize = 1)
    @GeneratedValue(generator = "seq_candidate_interaction", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "candidate_id", nullable = false)
    @JsonIgnore
    private Candidate candidate;
    @Enumerated(EnumType.STRING)
    private InteractionType type;
    @Column(length = 2000)
    private String content;
    @Column(nullable = false)
    private LocalDate date;
    private boolean candidateResponded;

}
