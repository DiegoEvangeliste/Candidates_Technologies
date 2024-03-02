package com.technicalEvaluation.devangeliste.dto;

import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;

public record CandidateExperiencesTechnologyDTO(String nameTechnology, String versionTechnology, int yearsExperience) {

    public static CandidateExperiencesTechnologyDTO fromCandidateExperiencesTechnology(CandidateTechnology candidateTechnology) {
        return new CandidateExperiencesTechnologyDTO(
                candidateTechnology.getTechnology().getNameTechnology(),
                candidateTechnology.getTechnology().getVersion(),
                candidateTechnology.getExperienceAge());
    }

}
