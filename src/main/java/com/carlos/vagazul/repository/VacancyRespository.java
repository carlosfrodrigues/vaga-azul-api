package com.carlos.vagazul.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carlos.vagazul.model.Vacancy;

@Repository
public interface VacancyRespository extends JpaRepository<Vacancy, Long>{

}