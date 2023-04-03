package com.casestudy.entity;
import com.casestudy.enums.CandidateStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Candidate{

    @Id
    @SequenceGenerator(name = "seq_candidate",allocationSize = 1)
    @GeneratedValue(generator = "seq_candidate", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String middleName;
    @Column(nullable = false)
    private String surname;
    @Column(unique = true)
    private String phoneNumber;
    @Column(unique = true)
    private String email;
    @OneToMany(mappedBy = "candidate", cascade = CascadeType.REMOVE)
    private List<CandidateInteraction> candidateInteractions;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CandidateStatus status;
}
