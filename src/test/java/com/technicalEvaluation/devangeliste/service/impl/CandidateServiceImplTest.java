package com.technicalEvaluation.devangeliste.service.impl;

import com.technicalEvaluation.devangeliste.Datos;
import com.technicalEvaluation.devangeliste.dto.CandidateDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesTechnologyDTO;
import com.technicalEvaluation.devangeliste.exception.EmptyRequiredFieldsException;
import com.technicalEvaluation.devangeliste.exception.InvalidFieldFormatException;
import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;
import com.technicalEvaluation.devangeliste.model.entity.Technology;
import com.technicalEvaluation.devangeliste.repository.CandidateRepository;
import com.technicalEvaluation.devangeliste.repository.CandidateTechnologyRepository;
import com.technicalEvaluation.devangeliste.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CandidateServiceImplTest {

    @Mock
    private CandidateRepository candidateRepository;

    @Mock
    private TechnologyRepository technologyRepository;

    @Mock
    private TechnologyServiceImpl technologyService;

    @Mock
    private CandidateTechnologyRepository candidateTechnologyRepository;

    @InjectMocks
    private CandidateServiceImpl candidateService;

    private Candidate candidate;
    private final Candidate candidate_1 = Datos.CANDIDATE_1;

    @BeforeEach
    void setUp() {
        candidate = Candidate.builder()
                .id(candidate_1.getId())
                .name(candidate_1.getName())
                .lastname(candidate_1.getLastname())
                .documentType(candidate_1.getDocumentType())
                .documentNumber(candidate_1.getDocumentNumber())
                .birthdate(candidate_1.getBirthdate())
                .build();
    }

    @Test
    void saveOkTest() {
        CandidateDTO candidateDTO = CandidateDTO.fromCandidate(Datos.CANDIDATE_1);

        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        CandidateDTO response = candidateService.save(candidate);

        assertNotNull(response);
        assertEquals(candidateDTO, response);
        verify(candidateRepository).save(any(Candidate.class));
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }

    @Nested
    class SaveFailedExceptionsTest {
        @Test
        void saveFailedExceptionEmptyRequiredFieldsTest() {
            Candidate candidateError = Datos.CANDIDATE_ERROR_EMPTY_REQUIRED_FIELD;

            EmptyRequiredFieldsException exception = assertThrows(EmptyRequiredFieldsException.class, () -> candidateService.save(candidateError));

            assertNotNull(exception.getMessage());
        }

        @Test
        void saveFailedExceptionInvalidFieldFormatDocumentNumberTest() {
            Candidate candidateError = Datos.CANDIDATE_ERROR_INVALID_FIELD_FORMAT_DOCUMENT_NUMBER;

            InvalidFieldFormatException exception = assertThrows(InvalidFieldFormatException.class, () -> candidateService.save(candidateError));

            assertNotNull(exception.getMessage());
        }

        @Test
        void saveFailedExceptionInvalidFieldFormatBirthdateTest() {
            Candidate candidateError = Datos.CANDIDATE_ERROR_INVALID_FIELD_FORMAT_BIRTHDATE;

            EmptyRequiredFieldsException exception = assertThrows(EmptyRequiredFieldsException.class, () -> candidateService.save(candidateError));

            assertNotNull(exception.getMessage());
        }

        @Test
        void saveFailedExceptionIlegalArgumentDocumentTypeTest() {
            Candidate candidateError = Datos.CANDIDATE_ERROR_ILEGAL_ARGUMENT_DOCUMENT_TYPE;

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> candidateService.save(candidateError));

            assertNotNull(exception.getMessage());
        }
    }

    @Test
    void update() {
        Optional<Candidate> optional = Optional.of(candidate);
        CandidateDTO candidateAuxDTO = CandidateDTO.fromCandidate(Datos.CANDIDATE_2);

        when(candidateRepository.findById(any(Long.class))).thenReturn(optional);
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        candidate.setName(candidateAuxDTO.name());
        candidate.setLastname(candidateAuxDTO.lastname());
        candidate.setDocumentType(candidateAuxDTO.documentType());
        candidate.setDocumentNumber(candidateAuxDTO.documentNumber());
        candidate.setBirthdate(candidateAuxDTO.birthdate());
        CandidateDTO response = candidateService.update(candidate);

        assertNotNull(response);
        assertNotEquals(CandidateDTO.fromCandidate(Datos.CANDIDATE_1), response);
        assertEquals(candidate.getName(), response.name());
        assertEquals(candidate.getLastname(), response.lastname());
        assertEquals(candidate.getDocumentType(), response.documentType());
        assertEquals(candidate.getDocumentNumber(), response.documentNumber());
        assertEquals(candidate.getBirthdate(), response.birthdate());
        verify(candidateRepository).findById(any(Long.class));
        verify(candidateRepository).save(any(Candidate.class));
        verify(candidateRepository, times(1)).findById(any(Long.class));
        verify(candidateRepository, times(1)).save(any(Candidate.class));
    }

    @Test
    void findById() {
        Optional<Candidate> optional = Optional.of(candidate);

        when(candidateRepository.findById(any(Long.class))).thenReturn(optional);

        CandidateDTO response = candidateService.findById(any(Long.class));

        assertNotNull(response);
        assertEquals(CandidateDTO.fromCandidate(candidate), response);
        verify(candidateRepository).findById(any(Long.class));
        verify(candidateRepository, times(1)).findById(any(Long.class));
    }

    @Test
    void findAll() {
        List<Candidate> candidateList= Datos.CANDIDATE_LIST;
        List<CandidateDTO> candidateDTOList = Datos.CANDIDATE_DTO_LIST;

        when(candidateRepository.findAll()).thenReturn(candidateList);

        List<CandidateDTO> response = candidateService.findAll();

        assertNotNull(response);
        assertEquals(candidateList.size(), response.size());
        assertEquals(candidateDTOList, response);
        verify(candidateRepository).findAll();
        verify(candidateRepository, times(1)).findAll();
    }

    @Test
    void deleteById() {
        when(candidateRepository.existsById(any(Long.class))).thenReturn(true);

        boolean response = candidateService.deleteById(candidate.getId());

        assertTrue(response);
        verify(candidateRepository).existsById(any(Long.class));
        verify(candidateRepository).deleteById(any(Long.class));
        verify(candidateRepository, times(1)).existsById(any(Long.class));
        verify(candidateRepository, times(1)).deleteById(any(Long.class));
    }

    @Test
    @Disabled
    void addYearsExperienceInTechnologyByIds() {
        Optional<Candidate> optionalCandidate = Optional.of(candidate);
        Optional<Technology> optionalTechnology =  Optional.of(Datos.TECHNOLOGY_1);
        CandidateTechnology candidateTechnology = Datos.CANDIDATE_TECHNOLOGY_1;
        //CandidateTechnology candidateTechnology = new CandidateTechnology(Datos.CANDIDATE_TECHNOLOGY_1.getId(), optionalCandidate.get(), optionalTechnology.get(), Datos.yearsExperiences1);

        optionalCandidate.get().getCandidateTechnology().add(candidateTechnology);

        when(candidateRepository.findById(any(Long.class))).thenReturn(optionalCandidate);
        when(technologyRepository.findById(any(Long.class))).thenReturn(optionalTechnology);
        when(candidateRepository.save(any(Candidate.class))).thenReturn(candidate);

        Long candidateId = optionalCandidate.get().getId();
        Long technologyId = optionalTechnology.get().getId();
        int yearsExperience = Datos.yearsExperiences1;
        CandidateTechnology response = candidateService.addYearsExperienceInTechnologyByIds(candidateId, technologyId, yearsExperience);

        assertNotNull(response);
        assertEquals(candidateTechnology, response);
        verify(candidateRepository).findById(any(Long.class));
        verify(candidateRepository).save(any(Candidate.class));
        verify(technologyRepository).findById(any(Long.class));
        verify(candidateRepository, times(1)).findById(any(Long.class));
        verify(candidateRepository, times(1)).save(any(Candidate.class));
        verify(technologyRepository, times(1)).findById(any(Long.class));
    }

    @Disabled
    @Test
    void getAllExperiencesInTechnologiesByCandidateId() {
        Optional<Candidate> optional = Optional.of(candidate);
        List<CandidateExperiencesTechnologyDTO> candidateExperiencesTechnologyDTODTOS = Datos.CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_LIST;
        List<CandidateTechnology> candidateTechnologyList = Datos.CANDIDATE_TECHNOLOGY_LIST;

        when(candidateRepository.findById(any(Long.class))).thenReturn(optional);

        List<CandidateExperiencesTechnologyDTO> response = candidateService.getAllExperiencesInTechnologiesByCandidateId(any(Long.class));

        assertNotNull(response);
        verify(technologyRepository).findById(any(Long.class));
        verify(technologyRepository, times(1)).findById(any(Long.class));
    }

    @Disabled
    @Test
    void getAllCandidatesAndExperiencesByNameTechnologyTest() {
        List<Technology> technologyList = Datos.TECHNOLOGY_LIST_JAVA;
        List<CandidateTechnology> candidateTechnologyList = Datos.CANDIDATE_TECHNOLOGY_LIST;
        List<CandidateExperiencesDTO> candidateExperiencesDTOList = Datos.CANDIDATE_EXPERIENCES_DTO_LIST_JAVA;

        when(technologyService.findTechnologies("Java")).thenReturn(technologyList);
        when(candidateTechnologyRepository.findAll()).thenReturn(candidateTechnologyList);

        List<CandidateExperiencesDTO> response = candidateService.getAllCandidatesAndExperiencesByTechnology("Java");

        assertNotNull(response);
        assertEquals(candidateExperiencesDTOList, response);
        assertEquals(candidateExperiencesDTOList.size(), response.size());
    }
}