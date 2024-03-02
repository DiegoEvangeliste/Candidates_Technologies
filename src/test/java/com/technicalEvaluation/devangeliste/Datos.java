package com.technicalEvaluation.devangeliste;

import com.technicalEvaluation.devangeliste.dto.CandidateDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesTechnologyDTO;
import com.technicalEvaluation.devangeliste.dto.TechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnologyId;
import com.technicalEvaluation.devangeliste.model.entity.Technology;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Datos {
    public final static Technology TECHNOLOGY_1 = Technology.builder().id(1L).nameTechnology("Java").version("8").build();
    public final static Technology TECHNOLOGY_2 = Technology.builder().id(2L).nameTechnology("Java").version("17").build();
    public final static Technology TECHNOLOGY_3 = Technology.builder().id(3L).nameTechnology("Python").version("3.8").build();

    public final static List<Technology> TECHNOLOGY_LIST = Arrays.asList(TECHNOLOGY_1, TECHNOLOGY_2, TECHNOLOGY_3);

    public final static List<Technology> TECHNOLOGY_LIST_JAVA = Arrays.asList(TECHNOLOGY_1, TECHNOLOGY_2);

    public final static List<Technology> TECHNOLOGY_LIST_JAVA_8 = Arrays.asList(TECHNOLOGY_1);

    public final static List<TechnologyDTO> TECHNOLOGY_DTO_LIST = Arrays.asList(
            TechnologyDTO.fromTechnology(TECHNOLOGY_1),
            TechnologyDTO.fromTechnology(TECHNOLOGY_2),
            TechnologyDTO.fromTechnology(TECHNOLOGY_3)
    );

    public final static Candidate CANDIDATE_1 = Candidate.builder().id(1L).name("CANDIDATO 1").lastname("PRIMERO")
            .documentType("DNI").documentNumber(123456789).birthdate(LocalDate.of(2000,5,23)).build();
    public final static Candidate CANDIDATE_2 = Candidate.builder().id(1L).name("CANDIDATO 2").lastname("SEGUNDO")
            .documentType("DNI").documentNumber(987654321).birthdate(LocalDate.of(1966,7,2)).build();
    public final static Candidate CANDIDATE_3 = Candidate.builder().id(1L).name("CANDIDATO 3").lastname("TERCERO")
            .documentType("DNI").documentNumber(432156789).birthdate(LocalDate.of(1996,12,31)).build();

    public final static Candidate CANDIDATE_ERROR_EMPTY_REQUIRED_FIELD = Candidate.builder().id(1L).name("").lastname("PRIMERO")
            .documentType("DNI").documentNumber(123456789).birthdate(LocalDate.of(2000,5,23)).build();
    public final static Candidate CANDIDATE_ERROR_INVALID_FIELD_FORMAT_DOCUMENT_NUMBER = Candidate.builder().id(1L).name("CANDIDATO 1").lastname("PRIMERO")
            .documentType("DNI").documentNumber(-1).birthdate(LocalDate.of(2000,5,23)).build();
    public final static Candidate CANDIDATE_ERROR_INVALID_FIELD_FORMAT_BIRTHDATE = Candidate.builder().id(1L).name("").lastname("PRIMERO")
            .documentType("DNI").documentNumber(123456789).birthdate(LocalDate.of(1000,5,23)).build();
    public final static Candidate CANDIDATE_ERROR_ILEGAL_ARGUMENT_DOCUMENT_TYPE = Candidate.builder().id(1L).name("").lastname("PRIMERO")
            .documentType("PASAPORTE").documentNumber(123456789).birthdate(LocalDate.of(1000,5,23)).build();


    public final static List<Candidate> CANDIDATE_LIST = Arrays.asList(CANDIDATE_1, CANDIDATE_2, CANDIDATE_3);

    public final static List<CandidateDTO> CANDIDATE_DTO_LIST = Arrays.asList(
            CandidateDTO.fromCandidate(CANDIDATE_1),
            CandidateDTO.fromCandidate(CANDIDATE_2),
            CandidateDTO.fromCandidate(CANDIDATE_3)
    );

    public final static int yearsExperiences1 = 5;
    public final static int yearsExperiences2 = 10;
    public final static int yearsExperiences3 = 27;

    private final static CandidateTechnologyId CANDIDATE_TECHNOLOGY_ID_1 = new CandidateTechnologyId(CANDIDATE_1.getId(), TECHNOLOGY_1.getId());
    private final static CandidateTechnologyId CANDIDATE_TECHNOLOGY_ID_1_2 = new CandidateTechnologyId(CANDIDATE_1.getId(), TECHNOLOGY_2.getId());
    private final static CandidateTechnologyId CANDIDATE_TECHNOLOGY_ID_2 = new CandidateTechnologyId(CANDIDATE_2.getId(), TECHNOLOGY_2.getId());
    private final static CandidateTechnologyId CANDIDATE_TECHNOLOGY_ID_3 = new CandidateTechnologyId(CANDIDATE_3.getId(), TECHNOLOGY_3.getId());

    public final static CandidateTechnology CANDIDATE_TECHNOLOGY_1 = CandidateTechnology.builder()
            .id(CANDIDATE_TECHNOLOGY_ID_1).candidate(CANDIDATE_1).technology(Datos.TECHNOLOGY_1).experienceAge(yearsExperiences1).build();
    public final static CandidateTechnology CANDIDATE_TECHNOLOGY_1_2 = CandidateTechnology.builder()
            .id(CANDIDATE_TECHNOLOGY_ID_1_2).candidate(CANDIDATE_1).technology(Datos.TECHNOLOGY_2).experienceAge(yearsExperiences3).build();
    public final static CandidateTechnology CANDIDATE_TECHNOLOGY_2 = CandidateTechnology.builder()
            .id(CANDIDATE_TECHNOLOGY_ID_2).candidate(CANDIDATE_2).technology(Datos.TECHNOLOGY_2).experienceAge(yearsExperiences2).build();
    public final static CandidateTechnology CANDIDATE_TECHNOLOGY_3 = CandidateTechnology.builder()
            .id(CANDIDATE_TECHNOLOGY_ID_3).candidate(CANDIDATE_3).technology(Datos.TECHNOLOGY_3).experienceAge(yearsExperiences3).build();

    public final static List<CandidateTechnology> CANDIDATE_TECHNOLOGY_LIST = Arrays.asList(
            CANDIDATE_TECHNOLOGY_1, CANDIDATE_TECHNOLOGY_2, CANDIDATE_TECHNOLOGY_3
    );


    public final static CandidateExperiencesTechnologyDTO CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_1 =
            CandidateExperiencesTechnologyDTO.fromCandidateExperiencesTechnology(CANDIDATE_TECHNOLOGY_1);
    public final static CandidateExperiencesTechnologyDTO CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_1_2 =
            CandidateExperiencesTechnologyDTO.fromCandidateExperiencesTechnology(CANDIDATE_TECHNOLOGY_1_2);
    public final static CandidateExperiencesTechnologyDTO CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_2 =
            CandidateExperiencesTechnologyDTO.fromCandidateExperiencesTechnology(CANDIDATE_TECHNOLOGY_2);
    public final static CandidateExperiencesTechnologyDTO CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_3 =
            CandidateExperiencesTechnologyDTO.fromCandidateExperiencesTechnology(CANDIDATE_TECHNOLOGY_3);

    public final static List<CandidateExperiencesTechnologyDTO> CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_LIST = Arrays.asList(
            CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_1, CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_2, CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_3
    );

    private final static List<CandidateExperiencesTechnologyDTO> CANDIDATE_1_EXPERIENCES_TECHNOLOGY_DTO_LIST = Arrays.asList(
            CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_1, CANDIDATE_EXPERIENCES_TECHNOLOGY_DTO_1_2
    );

    public final static CandidateExperiencesDTO CANDIDATE_EXPERIENCES_DTO_1 =
            CandidateExperiencesDTO.fromCandidateExperiencesDTO(CANDIDATE_1, yearsExperiences1);
    public final static CandidateExperiencesDTO CANDIDATE_EXPERIENCES_DTO_2 =
            CandidateExperiencesDTO.fromCandidateExperiencesDTO(CANDIDATE_2, yearsExperiences2);
    public final static CandidateExperiencesDTO CANDIDATE_EXPERIENCES_DTO_3 =
            CandidateExperiencesDTO.fromCandidateExperiencesDTO(CANDIDATE_3, yearsExperiences3);

    public final static List<CandidateExperiencesDTO> CANDIDATE_EXPERIENCES_DTO_LIST_JAVA = Arrays.asList(
            CANDIDATE_EXPERIENCES_DTO_1, CANDIDATE_EXPERIENCES_DTO_2
    );

}
