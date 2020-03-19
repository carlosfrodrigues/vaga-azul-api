package com.carlos.vagazul.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carlos.vagazul.exception.*;
import com.carlos.vagazul.model.Vacancy;
import com.carlos.vagazul.repository.VacancyRespository;

@RestController
@RequestMapping("/api/v1")
public class VacancyController {
    @Autowired
    private VacancyRespository vacancyRepository;

    @GetMapping("/vacancies")
    public List<Vacancy> getAllEmployees() {
        return vacancyRepository.findAll();
    }

    @GetMapping("/vacancies/{id}")
    public ResponseEntity<Vacancy> getVacancyById(@PathVariable(value = "id") Long vacancyId)
        throws ResourceNotFoundException {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
          .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found for this id :: " + vacancyId));
        return ResponseEntity.ok().body(vacancy);
    }
    
    @PostMapping("/vacancies")
    public Vacancy createVacancy(@Valid @RequestBody Vacancy vacancy) {
        return vacancyRepository.save(vacancy);
    }

    @PutMapping("/vacancies/{id}")
    public ResponseEntity<Vacancy> updateEmployee(@PathVariable(value = "id") Long vacancyId,
         @Valid @RequestBody Vacancy vacancyDetails) throws ResourceNotFoundException {
    	Vacancy vacancy = vacancyRepository.findById(vacancyId)
        .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found for this id :: " + vacancyId));

    	vacancy.setLatitude(vacancyDetails.getLatitude());
    	vacancy.setLongitude(vacancyDetails.getLongitude());
        final Vacancy updatedVacancy = vacancyRepository.save(vacancy);
        return ResponseEntity.ok(updatedVacancy);
    }

    @DeleteMapping("/vacancies/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long vacancyId)
         throws ResourceNotFoundException {
    	Vacancy vacancy = vacancyRepository.findById(vacancyId)
       .orElseThrow(() -> new ResourceNotFoundException("Vacancy not found for this id :: " + vacancyId));

    	vacancyRepository.delete(vacancy);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}