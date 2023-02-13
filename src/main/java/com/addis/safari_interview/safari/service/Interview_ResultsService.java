package com.addis.safari_interview.safari.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.addis.safari_interview.safari.model.Interview_Results;


@Service
public interface Interview_ResultsService extends JpaRepository<Interview_Results, Long> {


	Iterable<Interview_Results> findByCandidatName(String candidatName);
	void deleteByCandidatName(String candidatName);

}

