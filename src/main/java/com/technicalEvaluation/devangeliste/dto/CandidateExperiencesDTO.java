package com.technicalEvaluation.devangeliste.dto;

import com.technicalEvaluation.devangeliste.model.entity.Candidate;

import java.time.LocalDate;

public record CandidateExperiencesDTO(String name, String lastname, String documentType,
                                      Integer documentNumber, LocalDate birthdate, int yearsExperiences) {

    public static CandidateExperiencesDTO fromCandidateExperiencesDTO(Candidate candidate,  int yearsExperiences) {
        return new CandidateExperiencesDTO(
                candidate.getName(),
                candidate.getLastname(),
                candidate.getDocumentType(),
                candidate.getDocumentNumber(),
                candidate.getBirthdate(),
                yearsExperiences);
    }
}
