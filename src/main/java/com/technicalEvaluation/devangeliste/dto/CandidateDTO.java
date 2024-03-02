package com.technicalEvaluation.devangeliste.dto;

import com.technicalEvaluation.devangeliste.model.entity.Candidate;

import java.time.LocalDate;

public record CandidateDTO(String name, String lastname, String documentType, Integer documentNumber, LocalDate birthdate) {

    public static CandidateDTO fromCandidate(Candidate candidate) {
        return new CandidateDTO(candidate.getName(), candidate.getLastname(),
                candidate.getDocumentType(), candidate.getDocumentNumber(), candidate.getBirthdate());
    }
}
