package com.technicalEvaluation.devangeliste.service.impl;

import com.technicalEvaluation.devangeliste.dto.TechnologyDTO;
import com.technicalEvaluation.devangeliste.exception.EmptyRequiredFieldsException;
import com.technicalEvaluation.devangeliste.exception.EmptyTextException;
import com.technicalEvaluation.devangeliste.exception.ExcessInputParametersException;
import com.technicalEvaluation.devangeliste.exception.InputTechnologyInvalidatesException;
import com.technicalEvaluation.devangeliste.model.TechnologiesENUM;
import com.technicalEvaluation.devangeliste.model.entity.Technology;
import com.technicalEvaluation.devangeliste.repository.TechnologyRepository;
import com.technicalEvaluation.devangeliste.service.ITechnologyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TechnologyServiceImpl implements ITechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Override
    public TechnologyDTO save(Technology technology) {
        if (validate(technology)){
            log.info("The technology was successfully validated");
            return TechnologyDTO.fromTechnology(technologyRepository.save(technology));
        }
        Optional<TechnologyDTO> optional = Optional.empty();
        return optional.get();
    }

    @Override
    public TechnologyDTO update(Technology technology) {
        Optional<Technology> optional = technologyRepository.findById(technology.getId());
        if (validate(technology)){
            log.info("The technology was successfully validated");
            if (optional.isPresent()) {
                optional.get().setNameTechnology(technology.getNameTechnology());
                optional.get().setVersion(technology.getVersion());
                technologyRepository.save(optional.get());
            }
            return TechnologyDTO.fromTechnology(optional.get());
        }
        Optional<TechnologyDTO> optionalEmpty = Optional.empty();
        return optionalEmpty.get();
    }

    @Override
    public TechnologyDTO findById(Long id) {
        Optional<Technology> optional = technologyRepository.findById(id);
        return TechnologyDTO.fromTechnology(optional.get());
    }

    @Override
    public List<TechnologyDTO> findAll() {
        List<Technology> technologies = technologyRepository.findAll();
        List<TechnologyDTO> dtos = new ArrayList<>();
        if (!technologies.isEmpty()){
            for (Technology tech : technologies)
                dtos.add(TechnologyDTO.fromTechnology(tech));
        }
        return dtos;
    }

    @Override
    public boolean deleteById(Long id) {
        if (technologyRepository.existsById(id)) {
            technologyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Technology> findTechnologies(String textTechnology) {
            //textTechnology.isBlank();
        if (textTechnology.equals(""))
            throw new EmptyTextException("Empty text.");
        log.info("It was validated that the entered text is not empty.");

        List<Technology> listTechnologies = new ArrayList<>();
        List<String> arregloCadenaTechnology = Arrays.asList(textTechnology.split(" "));

        if (arregloCadenaTechnology.size() > 2)
            throw  new ExcessInputParametersException("It has more parameters than required.");
        log.info("It was validated that the number of items in the list generated from the text has no more than 2 items.");

        String name = arregloCadenaTechnology.get(0);
        if (!validateNameTechnology(name))
            throw new InputTechnologyInvalidatesException("The technology entered is invalid.");
        log.info("It was validated that the name of the technology is the allowed one.");

        if (arregloCadenaTechnology.size() > 1) {
            String version = arregloCadenaTechnology.get(1);
            listTechnologies = technologyRepository.findByNameTechnologyAndVersion(name, version);
        } else
            listTechnologies = technologyRepository.findByNameTechnology(textTechnology);

        return listTechnologies;
    }

    private boolean validate(Technology technology) {
        if (technology.getVersion().isEmpty() || technology.getNameTechnology().isEmpty())
            throw new EmptyRequiredFieldsException("The technology has empty fields");
        return validateNameTechnology(technology.getNameTechnology());
    }

    private boolean validateNameTechnology(String name) {
        try {
            TechnologiesENUM.valueOf(name.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
