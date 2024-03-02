package com.technicalEvaluation.devangeliste.service.impl;

import com.technicalEvaluation.devangeliste.dto.CandidateDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesTechnologyDTO;
import com.technicalEvaluation.devangeliste.exception.EmptyRequiredFieldsException;
import com.technicalEvaluation.devangeliste.exception.InvalidFieldFormatException;
import com.technicalEvaluation.devangeliste.model.DocumentTypeENUM;
import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnologyId;
import com.technicalEvaluation.devangeliste.model.entity.Technology;
import com.technicalEvaluation.devangeliste.repository.CandidateRepository;
import com.technicalEvaluation.devangeliste.repository.CandidateTechnologyRepository;
import com.technicalEvaluation.devangeliste.repository.TechnologyRepository;
import com.technicalEvaluation.devangeliste.service.ICandidateService;
import com.technicalEvaluation.devangeliste.service.ITechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CandidateServiceImpl implements ICandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private TechnologyRepository technologyRepository;

    @Autowired
    private CandidateTechnologyRepository candidateTechnologyRepository;

    @Autowired
    private ITechnologyService technologyService;

    @Override
    public CandidateDTO save(Candidate candidate) {
        if (validateCandidate(candidate)){
            log.info("The candidate was successfully validated");
            candidateRepository.save(candidate);
            return CandidateDTO.fromCandidate(candidate);
        }
        Optional<CandidateDTO> optional = Optional.empty();
        return optional.get();
    }

    @Override
    public CandidateDTO update(Candidate candidate) {
        Optional<Candidate> optional = candidateRepository.findById(candidate.getId());
        if (validateCandidate(candidate)){
            log.info("The candidate was successfully validated");
            if (optional.isPresent()) {
                optional.get().setName(candidate.getName());
                optional.get().setLastname(candidate.getLastname());
                optional.get().setDocumentType(candidate.getDocumentType());
                optional.get().setDocumentNumber(candidate.getDocumentNumber());
                optional.get().setBirthdate(candidate.getBirthdate());
                candidateRepository.save(optional.get());
            }
            return CandidateDTO.fromCandidate(optional.get());
        }
        Optional<CandidateDTO> optionalEmpty = Optional.empty();
        return optionalEmpty.get();
    }

    @Override
    public CandidateDTO findById(Long id) {
        Optional<Candidate> optional = candidateRepository.findById(id);
        return CandidateDTO.fromCandidate(optional.get());
    }

    @Override
    public List<CandidateDTO> findAll() {
        List<Candidate> candidates = candidateRepository.findAll();
        List<CandidateDTO> dtos = new ArrayList<>();
        if (!candidates.isEmpty())
            for (Candidate cand : candidates)
                dtos.add(CandidateDTO.fromCandidate(cand));
        return dtos;
    }

    @Override
    public boolean deleteById(Long id) {
        if (candidateRepository.existsById(id)) {
            candidateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CandidateTechnology addYearsExperienceInTechnologyByIds(Long candidateId, Long technologyId, int yearsExperience) {
        if (validateYearExperience(yearsExperience)){
            log.info("The candidate's years of technology experience have been successfully validated");
            Optional<Candidate> candidate = candidateRepository.findById(candidateId);
            Optional<Technology> technology =  technologyRepository.findById(technologyId);

            if (candidate.isPresent() && technology.isPresent()) {
                CandidateTechnologyId id = new CandidateTechnologyId(candidateId, technologyId);
                CandidateTechnology candidateTechnology = new CandidateTechnology(id, candidate.get(), technology.get(), yearsExperience);
                candidate.get().getCandidateTechnology().add(candidateTechnology);
                candidateRepository.save(candidate.get());
                return candidateTechnology;
            }
        }
        Optional<CandidateTechnology> optionalEmpty = Optional.empty();
        return optionalEmpty.get();
    }

    @Override
    public List<CandidateExperiencesTechnologyDTO> getAllExperiencesInTechnologiesByCandidateId(Long id) {
        Optional<Candidate> optional = candidateRepository.findById(id);
        List<CandidateExperiencesTechnologyDTO> dtos = new ArrayList<>();
        if (optional.isPresent()) {
            List<CandidateTechnology> experienciesTechnologies = optional.get().getCandidateTechnology();
            if (!experienciesTechnologies.isEmpty())
                for (CandidateTechnology experience : experienciesTechnologies)
                    dtos.add(CandidateExperiencesTechnologyDTO.fromCandidateExperiencesTechnology(experience));
        }
        return dtos;
    }

    @Override
    public List<CandidateExperiencesDTO> getAllCandidatesAndExperiencesByTechnology(String textTechnology) {
        Optional<List<Technology>> optionalTechnologies = Optional.ofNullable(technologyService.findTechnologies(textTechnology));
        List<CandidateExperiencesDTO> candidateExperiencesDTOS = new ArrayList<>();

        if (!optionalTechnologies.get().isEmpty()) {
            List<CandidateTechnology> candidatesTechnologies = candidateTechnologyRepository.findAll();
            for (CandidateTechnology ct : candidatesTechnologies)
                for(Technology tech : optionalTechnologies.get())
                    if(ct.getTechnology().equals(tech))
                        candidateExperiencesDTOS.add(CandidateExperiencesDTO.fromCandidateExperiencesDTO(ct.getCandidate(), ct.getExperienceAge()));
        }
        return candidateExperiencesDTOS;
    }

    private boolean validateCandidate(Candidate candidate) {
        if (candidate.getName().isEmpty() || candidate.getLastname().isEmpty() || candidate.getDocumentType().isEmpty() || candidate.getBirthdate() == null)
            throw new EmptyRequiredFieldsException("The candidate has empty fields");
        if (candidate.getDocumentNumber() < 1)
            throw new InvalidFieldFormatException("The document cannot be less than 1");
        if (candidate.getBirthdate().isBefore(LocalDate.of(1900, 1, 1))
                || candidate.getBirthdate().isAfter(LocalDate.of(2005, 12, 31)))
            throw new InvalidFieldFormatException("The date of birth format is not valid, it must be between 1900/01/01 and 2005/12/31");
        try {
            DocumentTypeENUM.valueOf(candidate.getDocumentType().toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private boolean validateYearExperience(int yearExperience) {
        return (yearExperience > 1 && yearExperience < 100);
    }

}
