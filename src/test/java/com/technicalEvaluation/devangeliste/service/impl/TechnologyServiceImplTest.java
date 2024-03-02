package com.technicalEvaluation.devangeliste.service.impl;

import com.technicalEvaluation.devangeliste.Datos;
import com.technicalEvaluation.devangeliste.dto.TechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Technology;
import com.technicalEvaluation.devangeliste.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TechnologyServiceImplTest {

    @Mock
    TechnologyRepository technologyRepository;

    @InjectMocks
    TechnologyServiceImpl technologyService;

    private Technology technology;
    private final Technology technology_1 = Datos.TECHNOLOGY_1;

    @BeforeEach
    void setUp() {
        technology = Technology.builder()
                .id(technology_1.getId())
                .nameTechnology(technology_1.getNameTechnology())
                .version(technology_1.getVersion())
                .build();
    }

    @Test
    void saveOkTest() {
        TechnologyDTO technologyDTO = TechnologyDTO.fromTechnology(Datos.TECHNOLOGY_1);

        when(technologyRepository.save(any(Technology.class))).thenReturn(technology);

        TechnologyDTO response = technologyService.save(technology);

        assertNotNull(response);
        assertEquals(technologyDTO, response);
        verify(technologyRepository).save(any(Technology.class));
        verify(technologyRepository, times(1)).save(any(Technology.class));
    }

    @Test
    void updateOkTest() {
        Optional<Technology> optional = Optional.of(technology);
        TechnologyDTO technologyAuxDTO = TechnologyDTO.fromTechnology(Datos.TECHNOLOGY_2);

        when(technologyRepository.findById(any(Long.class))).thenReturn(optional);
        when(technologyRepository.save(any(Technology.class))).thenReturn(technology);

        technology.setNameTechnology(technologyAuxDTO.nameTechnology());
        technology.setVersion(technologyAuxDTO.version());
        TechnologyDTO response = technologyService.update(technology);

        assertNotNull(response);
        assertNotEquals(TechnologyDTO.fromTechnology(Datos.TECHNOLOGY_1), response);
        assertEquals(technology.getNameTechnology(), response.nameTechnology());
        assertEquals(technology.getVersion(), response.version());
        verify(technologyRepository).findById(any(Long.class));
        verify(technologyRepository).save(any(Technology.class));
        verify(technologyRepository, times(1)).findById(any(Long.class));
        verify(technologyRepository, times(1)).save(any(Technology.class));
    }

    @Nested
    class FindTests {
        @Test
        void findByIdOkTest() {
            Optional<Technology> optional = Optional.of(technology);

            when(technologyRepository.findById(any(Long.class))).thenReturn(optional);

            TechnologyDTO response = technologyService.findById(any(Long.class));

            assertNotNull(response);
            assertEquals(TechnologyDTO.fromTechnology(technology), response);
            verify(technologyRepository).findById(any(Long.class));
            verify(technologyRepository, times(1)).findById(any(Long.class));
        }

        @Test
        void findAllOkTest() {
            List<Technology> technologyList = Datos.TECHNOLOGY_LIST;
            List<TechnologyDTO> technologyDTOList = Datos.TECHNOLOGY_DTO_LIST;

            when(technologyRepository.findAll()).thenReturn(technologyList);

            List<TechnologyDTO> response = technologyService.findAll();

            assertNotNull(response);
            assertEquals(technologyList.size(), response.size());
            assertEquals(technologyDTOList, response);
            verify(technologyRepository).findAll();
            verify(technologyRepository, times(1)).findAll();
        }

        @Test
        void findTechnologiesByNameOkTest() {
            List<Technology> technologyListJava = Datos.TECHNOLOGY_LIST_JAVA;

            when(technologyRepository.findByNameTechnology("Java")).thenReturn(technologyListJava);

            List<Technology> response = technologyService.findTechnologies("Java");

            assertNotNull(response);
            assertEquals(technologyListJava, response);
            assertEquals(technologyListJava.size(), response.size());
            verify(technologyRepository).findByNameTechnology("Java");
            verify(technologyRepository, times(1)).findByNameTechnology("Java");
        }

        @Test
        void findTechnologiesByNameAndVersionOkTest() {
            List<Technology> technologyListJava = Datos.TECHNOLOGY_LIST_JAVA_8;

            when(technologyRepository.findByNameTechnologyAndVersion("Java", "8")).thenReturn(technologyListJava);

            List<Technology> response = technologyService.findTechnologies("Java 8");

            assertNotNull(response);
            assertEquals(technologyListJava, response);
            assertEquals(technologyListJava.size(), response.size());
            verify(technologyRepository).findByNameTechnologyAndVersion("Java", "8");
            verify(technologyRepository, times(1)).findByNameTechnologyAndVersion("Java", "8");
        }
    }

    @Test
    void deleteByIdOkTest() {
        when(technologyRepository.existsById(any(Long.class))).thenReturn(true);

        boolean response = technologyService.deleteById(technology.getId());

        assertTrue(response);
        verify(technologyRepository).existsById(any(Long.class));
        verify(technologyRepository).deleteById(any(Long.class));
        verify(technologyRepository, times(1)).existsById(any(Long.class));
        verify(technologyRepository, times(1)).deleteById(any(Long.class));
    }

}