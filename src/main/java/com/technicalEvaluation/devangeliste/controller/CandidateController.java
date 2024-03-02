package com.technicalEvaluation.devangeliste.controller;

import com.technicalEvaluation.devangeliste.dto.CandidateDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesDTO;
import com.technicalEvaluation.devangeliste.dto.CandidateExperiencesTechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Candidate;
import com.technicalEvaluation.devangeliste.model.entity.CandidateTechnology;
import com.technicalEvaluation.devangeliste.service.ICandidateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    ICandidateService candidateService;

    @PostMapping
    @Operation(summary = "Save Candidate", description = "Save a candidate to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Candidate was successfully saved."),
            @ApiResponse(responseCode = "400", description = "The candidate could not be saved.")})
    public ResponseEntity<CandidateDTO> save(@RequestBody Candidate candidate) {
        Optional<CandidateDTO> optional = Optional.ofNullable(candidateService.save(candidate));
        return (optional.isPresent()) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @Operation(summary = "Update Candidate", description = "Modifies a candidate stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The candidate was successfully modified."),
            @ApiResponse(responseCode = "400", description = "The candidate could not be modified.")})
    public ResponseEntity<CandidateDTO> update(@RequestBody Candidate candidate){
        Optional<CandidateDTO> optional = Optional.ofNullable(candidateService.update(candidate));
        return (optional.isPresent()) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Candidate By Id", description = "Find a candidate from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Candidate was successfully found."),
            @ApiResponse(responseCode = "404", description = "The candidate has not been found..")})
    public ResponseEntity<CandidateDTO> findById(@PathVariable Long id) {
        Optional<CandidateDTO> optional = Optional.ofNullable(candidateService.findById(id));
        return (optional.isPresent()) ? ResponseEntity.ok(optional.get()) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(summary = "Find All Candidates", description = "Find all the candidates in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All candidates were successfully found."),
            @ApiResponse(responseCode = "404", description = "No candidates found.")})
    public ResponseEntity<List<CandidateDTO>> findAll() {
        Optional<List<CandidateDTO>> optionalList = Optional.ofNullable(candidateService.findAll());
        return (optionalList.isPresent()) ? ResponseEntity.ok(optionalList.get()) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Candidate By Id", description = "Removes a Candidate from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The Candidate was successfully eliminated."),
            @ApiResponse(responseCode = "404", description = "The right Candidate was not found.")})
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return (candidateService.deleteById(id)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/addExperienceTechnology")
    @Operation(summary = "Add Years Experiences In Technology By Id", description = "Add years of experience based on a Technology's ID and a Candidate's ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Added the years of experience successfully."),
            @ApiResponse(responseCode = "400", description = "Years of experience could not be added.")})
    public ResponseEntity<CandidateTechnology> addYearsExperienceInTechnologyByIds(
            @RequestParam Long candidateId, @RequestParam Long technologyId, @RequestParam int yearsExperience) {
        Optional<CandidateTechnology> optional = Optional.ofNullable(candidateService.addYearsExperienceInTechnologyByIds(candidateId, technologyId, yearsExperience));
        return (optional.isPresent()) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/experiencesTechnology/{id}")
    @Operation(summary = "Get All Experiences In Technology By Candidate Id", description = "Get all of a Candidate's technology experiences by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All of the candidate's technology experiences were successfully obtained."),
            @ApiResponse(responseCode = "404", description = "The candidate's technology experiences could not be obtained.")})
    public ResponseEntity<List<CandidateExperiencesTechnologyDTO>> getAllExperiencesInTechnologiesByCandidateId(@PathVariable Long id) {
        Optional<List<CandidateExperiencesTechnologyDTO>> optional = Optional.ofNullable(candidateService.getAllExperiencesInTechnologiesByCandidateId(id));
        return (optional.isPresent()) ? ResponseEntity.ok(optional.get()): new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/experiences/technology")
    @Operation(summary = "Get All Candidates And Experiences By Technology", description = "It obtains all candidates according to their experiences in a given technology.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All candidates were successfully obtained according to the indicated Technology.."),
            @ApiResponse(responseCode = "404", description = "Candidates could not be obtained according to the indicated Technology..")})
    public ResponseEntity<List<CandidateExperiencesDTO>> getAllCandidatesAndExperiencesByTechnology(@RequestParam String textTechnology) {
        Optional<List<CandidateExperiencesDTO>> optional = Optional.ofNullable(candidateService.getAllCandidatesAndExperiencesByTechnology(textTechnology));
        return (optional.isPresent()) ? ResponseEntity.ok(optional.get()) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
