package com.technicalEvaluation.devangeliste.controller;

import com.technicalEvaluation.devangeliste.dto.TechnologyDTO;
import com.technicalEvaluation.devangeliste.model.entity.Technology;
import com.technicalEvaluation.devangeliste.service.ITechnologyService;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/technologies")
public class TechnologyController {

    @Autowired
    ITechnologyService service;

    @PostMapping
    @Operation(summary = "Save Technology", description = "Save a technology to the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Technology was successfully saved."),
            @ApiResponse(responseCode = "400", description = "The technology could not be saved.")})
    public ResponseEntity<TechnologyDTO> save(@RequestBody Technology technology) {
        Optional<TechnologyDTO> optional = Optional.ofNullable(service.save(technology));
        return (optional.isPresent()) ? new ResponseEntity<>(HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping
    @Operation(summary = "Update Technology", description = "Modifies a technology stored in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The technology was successfully modified."),
            @ApiResponse(responseCode = "400", description = "The technology could not be modified.")})
    public ResponseEntity<TechnologyDTO> update(@RequestBody Technology technology){
        Optional<TechnologyDTO> optional = Optional.ofNullable(service.update(technology));
        return (optional.isPresent()) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Technology By Id", description = "Find a technology from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Technology was successfully found."),
            @ApiResponse(responseCode = "404", description = "The technology has not been found..")})
    public ResponseEntity<TechnologyDTO> findById(@PathVariable Long id) {
        Optional<TechnologyDTO> optional = Optional.ofNullable(service.findById(id));
        return (optional.isPresent()) ? ResponseEntity.ok(optional.get()) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    @Operation(summary = "Find All Technologies", description = "Find all the technologies in the database.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All technologies were successfully found."),
            @ApiResponse(responseCode = "404", description = "No technologies found.")})
    public ResponseEntity<List<TechnologyDTO>> findAll() {
        Optional<List<TechnologyDTO>> optionalList = Optional.ofNullable(service.findAll());
        return (optionalList.isPresent()) ? ResponseEntity.ok(optionalList.get()) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Technology By Id", description = "Removes a technology from the database by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The technology was successfully eliminated."),
            @ApiResponse(responseCode = "404", description = "The right technology was not found.")})
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id) {
        return (service.deleteById(id)) ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
