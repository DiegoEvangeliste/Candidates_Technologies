package com.technicalEvaluation.devangeliste.repository;

import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate,Long> {
}
