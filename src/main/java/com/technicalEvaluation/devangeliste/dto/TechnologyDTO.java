package com.technicalEvaluation.devangeliste.dto;

import com.technicalEvaluation.devangeliste.model.entity.Technology;

public record TechnologyDTO(String nameTechnology, String version) {

    public static TechnologyDTO fromTechnology(Technology technology) {
        return new TechnologyDTO(technology.getNameTechnology(), technology.getVersion());
    }
}
