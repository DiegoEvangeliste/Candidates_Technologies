package com.technicalEvaluation.devangeliste.controller;

import com.technicalEvaluation.devangeliste.Datos;
import com.technicalEvaluation.devangeliste.dto.CandidateDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesTechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;
import com.technicalEvaluation.devangeliste.service.impl.CandidateServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @Mock
    private CandidateServiceImpl candidateService;

    @InjectMocks
    private CandidateController candidateController;

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

    @Nested
    class SaveTests {
        @Test
        void saveOkTest() {
            CandidateDTO candidateDTO = CandidateDTO.fromCandidate(candidate);

            when(candidateService.save(any(Candidate.class))).thenReturn(candidateDTO);

            ResponseEntity<CandidateDTO> response = candidateController.save(candidate);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            verify(candidateService).save(candidate);
            verify(candidateService, times(1)).save(candidate);
        }

        @Test
        void saveFailedTest() {
            when(candidateService.save(any(Candidate.class))).thenReturn(null);

            ResponseEntity<CandidateDTO> response = candidateController.save(candidate);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            verify(candidateService).save(candidate);
            verify(candidateService, times(1)).save(candidate);
        }
    }

    @Nested
    class UpdateTests {
        @Test
        void updateOkTest() {
            CandidateDTO candidateAuxDTO = CandidateDTO.fromCandidate(Datos.CANDIDATE_2);

            when(candidateService.update(candidate)).thenReturn(candidateAuxDTO);

            candidate.setName(candidateAuxDTO.name());
            candidate.setLastname(candidateAuxDTO.lastname());
            candidate.setDocumentType(candidateAuxDTO.documentType());
            candidate.setDocumentNumber(candidateAuxDTO.documentNumber());
            candidate.setBirthdate(candidateAuxDTO.birthdate());
            ResponseEntity<CandidateDTO> response = candidateController.update(candidate);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(candidateService).update(candidate);
            verify(candidateService, times(1)).update(candidate);
        }

        @Test
        void updateFailedTest() {
            when(candidateService.update(candidate)).thenReturn(null);

            ResponseEntity<CandidateDTO> response = candidateController.update(candidate);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            verify(candidateService).update(candidate);
            verify(candidateService, times(1)).update(candidate);
        }
    }

    @Nested
    class FindTests {

        @Nested
        class FindByIdTests {
            @Test
            void findByIdOkTest() {
                CandidateDTO candidateDTO = CandidateDTO.fromCandidate(candidate);

                when(candidateService.findById(candidate.getId())).thenReturn(candidateDTO);

                ResponseEntity<CandidateDTO> response = candidateController.findById(candidate.getId());

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(candidateDTO, response.getBody());
                verify(candidateService).findById(candidate.getId());
                verify(candidateService, times(1)).findById(candidate.getId());
            }

            @Test
            void findByIdFailedTest() {
                when(candidateService.findById(candidate.getId())).thenReturn(null);

                ResponseEntity<CandidateDTO> response = candidateController.findById(candidate.getId());

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(candidateService).findById(candidate.getId());
                verify(candidateService, times(1)).findById(candidate.getId());
            }
        }

        @Nested
        class FindAllTests {
            @Test
            void findAllOkTest() {
                List<CandidateDTO> candidateDTOList = Datos.CANDIDATE_DTO_LIST;

                when(candidateService.findAll()).thenReturn(Datos.CANDIDATE_DTO_LIST);

                ResponseEntity<List<CandidateDTO>> response = candidateController.findAll();

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(candidateDTOList.size(), response.getBody().size());
                verify(candidateService).findAll();
                verify(candidateService, times(1)).findAll();
            }

            @Test
            void findAllFailedTest() {
                when(candidateService.findAll()).thenReturn(null);

                ResponseEntity<List<CandidateDTO>> response = candidateController.findAll();

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(candidateService).findAll();
                verify(candidateService, times(1)).findAll();
            }
        }

    }

    @Nested
    class DeleteTests {
        @Test
        void deleteByIdOkTest() {
            when(candidateService.deleteById(any(Long.class))).thenReturn(true);

            ResponseEntity<Boolean> response = candidateController.deleteById(any(Long.class));

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(candidateService).deleteById(any(Long.class));
            verify(candidateService, times(1)).deleteById(any(Long.class));
        }

        @Test
        void deleteByIdFailedTest() {
            when(candidateService.deleteById(any(Long.class))).thenReturn(false);

            ResponseEntity<Boolean> response = candidateController.deleteById(any(Long.class));

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            verify(candidateService).deleteById(any(Long.class));
            verify(candidateService, times(1)).deleteById(any(Long.class));
        }

    }

    @Nested
    class CandidateTechnologyTest {
        @Nested
        class addYearsExperienceInTechnologyByIdsTests {
            @Test
            void addYearsExperienceInTechnologyByIdsOkTest() {
                CandidateTechnology candidateTechnology = Datos.CANDIDATE_TECHNOLOGY_1;
                Long idCandidate = candidate.getId();
                Long idTechnology = Datos.TECHNOLOGY_1.getId();
                int yearsExperience = Datos.yearsExperiences1;

                when(candidateService.addYearsExperienceInTechnologyByIds(idCandidate, idTechnology, yearsExperience)).thenReturn(candidateTechnology);

                ResponseEntity<CandidateTechnology> response = candidateController
                        .addYearsExperienceInTechnologyByIds(idCandidate, idTechnology, yearsExperience);

                assertEquals(HttpStatus.CREATED, response.getStatusCode());
                verify(candidateService).addYearsExperienceInTechnologyByIds(idCandidate, idTechnology, yearsExperience);
                verify(candidateService, times(1)).addYearsExperienceInTechnologyByIds(idCandidate, idTechnology, yearsExperience);
            }

            @Test
            void addYearsExperienceInTechnologyByIdsFailedTest() {
                when(candidateService.addYearsExperienceInTechnologyByIds(any(Long.class), any(Long.class), any(int.class))).thenReturn(null);

                ResponseEntity<CandidateTechnology> response = candidateController
                        .addYearsExperienceInTechnologyByIds(any(Long.class), any(Long.class), any(int.class));

                assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
                verify(candidateService).addYearsExperienceInTechnologyByIds(any(Long.class), any(Long.class), any(int.class));
                verify(candidateService, times(1)).addYearsExperienceInTechnologyByIds(any(Long.class), any(Long.class), any(int.class));
            }
        }

        @Nested
        class getAllExperiencesInTechnologiesByCandidateIdTests {
            @Test
            void getAllExperiencesInTechnologiesByCandidateIdOkTest() {
                List<CandidateExperiencesTechnologyDTO> candidateExperiencesTechnologyDTOList = Datos.CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_LIST;

                when(candidateService.getAllExperiencesInTechnologiesByCandidateId(any(Long.class))).thenReturn(candidateExperiencesTechnologyDTOList);

                ResponseEntity<List<CandidateExperiencesTechnologyDTO>> response = candidateController.getAllExperiencesInTechnologiesByCandidateId(candidate.getId());

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(candidateExperiencesTechnologyDTOList.size(), response.getBody().size());
                verify(candidateService).getAllExperiencesInTechnologiesByCandidateId(any(Long.class));
                verify(candidateService, times(1)).getAllExperiencesInTechnologiesByCandidateId(any(Long.class));
            }

            @Test
            void getAllExperiencesInTechnologiesByCandidateIdFailedTest() {
                when(candidateService.getAllExperiencesInTechnologiesByCandidateId(any(Long.class))).thenReturn(null);

                ResponseEntity<List<CandidateExperiencesTechnologyDTO>> response = candidateController.getAllExperiencesInTechnologiesByCandidateId(candidate.getId());

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(candidateService).getAllExperiencesInTechnologiesByCandidateId(any(Long.class));
                verify(candidateService, times(1)).getAllExperiencesInTechnologiesByCandidateId(any(Long.class));
            }
        }

        @Nested
        class getAllCandidatesAndExperiencesByTechnologyTests {
            @Test
            void getAllCandidatesAndExperiencesByTechnologyOkTest() {
                List<CandidateExperiencesDTO> candidateExperiencesDTOS = Datos.CANDIDATE_EXPERIENCES_DTO_LIST_JAVA;

                when(candidateService.getAllCandidatesAndExperiencesByTechnology("Java")).thenReturn(candidateExperiencesDTOS);

                ResponseEntity<List<CandidateExperiencesDTO>> response = candidateController.getAllCandidatesAndExperiencesByTechnology("Java");

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(candidateExperiencesDTOS.size(), response.getBody().size());
                verify(candidateService).getAllCandidatesAndExperiencesByTechnology("Java");
                verify(candidateService, times(1)).getAllCandidatesAndExperiencesByTechnology("Java");
            }

            @Test
            void getAllCandidatesAndExperiencesByTechnologyFailedTest() {
                when(candidateService.getAllCandidatesAndExperiencesByTechnology("cualquier cosa")).thenReturn(null);

                ResponseEntity<List<CandidateExperiencesDTO>> response = candidateController.getAllCandidatesAndExperiencesByTechnology("cualquier cosa");

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(candidateService).getAllCandidatesAndExperiencesByTechnology("cualquier cosa");
                verify(candidateService, times(1)).getAllCandidatesAndExperiencesByTechnology("cualquier cosa");
            }

        }
    }

}