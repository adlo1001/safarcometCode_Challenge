package com.addis.safari_interview.safari.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.addis.safari_interview.safari.model.Interview_Results;



@Service
public interface Interview_ResultsService extends JpaRepository<Interview_Results, Long> {


	Iterable<Interview_Results> findByCandidatName(String candidatName);
	void deleteByCandidatName(String candidatName);
	
}

