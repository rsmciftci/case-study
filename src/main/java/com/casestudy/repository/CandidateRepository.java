package com.casestudy.repository;

import com.casestudy.entity.Candidate;
import com.casestudy.enums.CandidateStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {


 @Query("select c from Candidate c where upper(c.name) = upper(?1) and upper(c.surname) = upper(?2)")
 List<Candidate> findCandidatesByNameAndSurname(String name, String surname);



    @Transactional
    @Modifying
    @Query("update Candidate c set c.status = ?1 where c.id = ?2")
    void updateStatusById(CandidateStatus status, Long id);














}
