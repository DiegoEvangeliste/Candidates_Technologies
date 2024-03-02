package com.technicalEvaluation.devangeliste.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CandidateTechnologyId implements Serializable {
    private Long candidateId;
    private Long technologyId;
}
