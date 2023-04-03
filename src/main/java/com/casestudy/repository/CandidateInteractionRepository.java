package com.casestudy.repository;

import com.casestudy.entity.CandidateInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateInteractionRepository extends JpaRepository<CandidateInteraction, Long> {
    List<CandidateInteraction> findCandidateInteractionsByCandidateId(Long id);

}
