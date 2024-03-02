package com.technicalEvaluation.devangeliste.controller;

import com.technicalEvaluation.devangeliste.Datos;
import com.technicalEvaluation.devangeliste.dto.TechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Technology;
import com.technicalEvaluation.devangeliste.service.impl.TechnologyServiceImpl;
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
class TechnologyControllerTest {

    @Mock
    private TechnologyServiceImpl technologyService;

    @InjectMocks
    private TechnologyController technologyController;

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

    @Nested
    class SaveTests {
        @Test
        void saveOkTest() {
            TechnologyDTO technologyDTO = TechnologyDTO.fromTechnology(technology);

            when(technologyService.save(any(Technology.class))).thenReturn(technologyDTO);

            ResponseEntity<TechnologyDTO> response = technologyController.save(technology);

            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            verify(technologyService).save(technology);
            verify(technologyService, times(1)).save(technology);
        }

        @Test
        void saveFailedTest() {
            when(technologyService.save(any(Technology.class))).thenReturn(null);

            ResponseEntity<TechnologyDTO> response = technologyController.save(technology);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            verify(technologyService).save(technology);
            verify(technologyService, times(1)).save(technology);
        }
    }

    @Nested
    class UpdateTests {
        @Test
        void updateOkTest() {
            TechnologyDTO technologyAuxDTO = TechnologyDTO.fromTechnology(Datos.TECHNOLOGY_2);

            when(technologyService.update(technology)).thenReturn(technologyAuxDTO);

            technology.setNameTechnology(technologyAuxDTO.nameTechnology());
            technology.setVersion(technologyAuxDTO.version());
            ResponseEntity<TechnologyDTO> response = technologyController.update(technology);

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(technologyService).update(technology);
            verify(technologyService, times(1)).update(technology);
        }

        @Test
        void updateFailedTest() {
            when(technologyService.update(technology)).thenReturn(null);

            ResponseEntity<TechnologyDTO> response = technologyController.update(technology);

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            verify(technologyService).update(technology);
            verify(technologyService, times(1)).update(technology);
        }
    }

    @Nested
    class FindTests {

        @Nested
        class FindByIdTests {
            @Test
            void findByIdOkTest() {
                TechnologyDTO technologyDTO = TechnologyDTO.fromTechnology(technology);

                when(technologyService.findById(technology.getId())).thenReturn(technologyDTO);

                ResponseEntity<TechnologyDTO> response = technologyController.findById(technology.getId());

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(technologyDTO, response.getBody());
                verify(technologyService).findById(technology.getId());
                verify(technologyService, times(1)).findById(technology.getId());
            }

            @Test
            void findByIdFailedTest() {
                when(technologyService.findById(technology.getId())).thenReturn(null);

                ResponseEntity<TechnologyDTO> response = technologyController.findById(technology.getId());

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(technologyService).findById(technology.getId());
                verify(technologyService, times(1)).findById(technology.getId());
            }
        }

        @Nested
        class FindAllTests {
            @Test
            void findAllOkTest() {
                List<TechnologyDTO> technologyDTOList = Datos.TECHNOLOGY_DTO_LIST;

                when(technologyService.findAll()).thenReturn(Datos.TECHNOLOGY_DTO_LIST);

                ResponseEntity<List<TechnologyDTO>> response = technologyController.findAll();

                assertEquals(HttpStatus.OK, response.getStatusCode());
                assertEquals(technologyDTOList.size(), response.getBody().size());
                verify(technologyService).findAll();
                verify(technologyService, times(1)).findAll();
            }

            @Test
            void findAllFailedTest() {
                when(technologyService.findAll()).thenReturn(null);

                ResponseEntity<List<TechnologyDTO>> response = technologyController.findAll();

                assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
                verify(technologyService).findAll();
                verify(technologyService, times(1)).findAll();
            }
        }

    }

    @Nested
    class DeleteTests {
        @Test
        void deleteByIdOkTest() {
            when(technologyService.deleteById(any(Long.class))).thenReturn(true);

            ResponseEntity<Boolean> response = technologyController.deleteById(any(Long.class));

            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            verify(technologyService).deleteById(any(Long.class));
            verify(technologyService, times(1)).deleteById(any(Long.class));
        }

        @Test
        void deleteByIdFailedTest() {
            when(technologyService.deleteById(any(Long.class))).thenReturn(false);

            ResponseEntity<Boolean> response = technologyController.deleteById(any(Long.class));

            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            verify(technologyService).deleteById(any(Long.class));
            verify(technologyService, times(1)).deleteById(any(Long.class));
        }

    }

}