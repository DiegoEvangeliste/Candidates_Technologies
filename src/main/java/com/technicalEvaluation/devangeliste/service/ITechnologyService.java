package com.technicalEvaluation.devangeliste.service;

import com.technicalEvaluation.devangeliste.dto.TechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Technology;

import java.util.List;

public interface ITechnologyService {
    TechnologyDTO save(Technology technology);
    TechnologyDTO update(Technology technology);
    TechnologyDTO findById(Long id);
    List<TechnologyDTO> findAll();
    boolean deleteById(Long id);
    List<Technology> findTechnologies(String technology);
}
