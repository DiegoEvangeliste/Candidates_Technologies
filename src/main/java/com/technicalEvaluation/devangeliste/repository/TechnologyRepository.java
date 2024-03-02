package com.technicalEvaluation.devangeliste.repository;

import com.technicalEvaluation.devangeliste.model.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology,Long> {
    List<Technology> findByNameTechnologyAndVersion(String name, String version);

    @Query(value = "SELECT * FROM TECHNOLOGIES t WHERE t.NAME_TECHNOLOGY = :nameTechnology", nativeQuery = true)
    List<Technology> findByNameTechnology(@Param("nameTechnology") String nameTechnology);
}
