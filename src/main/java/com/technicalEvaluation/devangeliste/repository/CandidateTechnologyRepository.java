package com.technicalEvaluation.devangeliste.repository;

import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateTechnologyRepository extends JpaRepository<CandidateTechnology,Long> {
}
