package com.technicalEvaluation.devangeliste.service;

import com.technicalEvaluation.devangeliste.dto.CandidateDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesTechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;

import java.util.List;

public interface ICandidateService {
    CandidateDTO save(Candidate candidate);
    CandidateDTO update(Candidate candidate);
    CandidateDTO findById(Long id);
    List<CandidateDTO> findAll();
    boolean deleteById(Long id);
    CandidateTechnology addYearsExperienceInTechnologyByIds(Long candidateId, Long technologyId, int yearsExperience);
    List<CandidateExperiencesTechnologyDTO> getAllExperiencesInTechnologiesByCandidateId(Long candidateId);
    List<CandidateExperiencesDTO> getAllCandidatesAndExperiencesByTechnology(String textTechnology);
}
