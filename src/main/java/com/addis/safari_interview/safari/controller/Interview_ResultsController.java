package com.addis.safari_interview.safari.controller;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.addis.safari_interview.safari.Exceptions.CandidateNotFoundException;
import com.addis.safari_interview.safari.Exceptions.ErrorMessage;
import com.addis.safari_interview.safari.Exceptions.InvalidInputException;
import com.addis.safari_interview.safari.model.Interview_Results;
import com.addis.safari_interview.safari.service.Interview_ResultsService;

@RestController
public class Interview_ResultsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(Interview_ResultsController.class);
	@Autowired
	Interview_ResultsService resultsSerivce;

	/* Retrieves results with id */
	@GetMapping("/retrieve/{id}")
	public Optional<Interview_Results> getResultsById(@PathVariable Long id) {
		return resultsSerivce.findById(id);

	}

	/* Retrieves results by name */
	@GetMapping("/retrieveByName/{name}")
	public Iterable<Interview_Results> getResultsByName(@PathVariable String name) throws CandidateNotFoundException {
		if (resultsSerivce.findByCandidatName(name).spliterator().getExactSizeIfKnown() != 0) {
			return resultsSerivce.findByCandidatName(name);

		} else {
			// LOGGER.info("not found");
			throw new CandidateNotFoundException("Record not found!");
		}

	}

	/* Retrieves all results */
	@GetMapping("/retrieve")
	public Iterable<Interview_Results> getResult() {
		return resultsSerivce.findAll();

	}

	/*
	 * Adds a new results into the table and retuls a message if the name of the
	 * candidates already exists
	 */
	@PostMapping("/create")
	public Interview_Results addCandidate(@RequestBody Interview_Results _results) throws ValidationException {
		if (resultsSerivce.findByCandidatName(_results.getCandidatName()).spliterator().getExactSizeIfKnown() == 0)

			if (_results.getCandidateScore() <= 100 && _results.getCandidateScore() >= 0)
				return resultsSerivce.save(_results);
			else
				throw new InvalidInputException("Invalid input");
		else
			throw new ValidationException("Candidate Exists!");

	}

	/* Modifies the results table if the name exists */
	@PutMapping("/update")
	public Interview_Results updateCandidate(@RequestBody Interview_Results _results)
			throws InvalidInputException, CandidateNotFoundException {
		if (resultsSerivce.findByCandidatName(_results.getCandidatName()).spliterator().getExactSizeIfKnown() != 0) {
			Iterable<Interview_Results> data = resultsSerivce.findByCandidatName(_results.getCandidatName());
			LOGGER.info("inside update!");
			while (data.iterator().hasNext()) {
				Interview_Results r = data.iterator().next();
				r.setCandidatName(_results.getCandidatName());
				r.setCandidateScore(_results.getCandidateScore());
				if (_results.getCandidateScore() <= 100 && _results.getCandidateScore() >= 0)
					return resultsSerivce.save(r);
				else
					throw new InvalidInputException("Invalid input");
			}
		} else
			throw new CandidateNotFoundException("");
		return null;

	}

	/* Removes results by candidate name */
	@DeleteMapping("/removeByName/{name}")
	public String removeCandidateByName(@PathVariable String name) throws CandidateNotFoundException {
		if (resultsSerivce.findByCandidatName(name).spliterator().getExactSizeIfKnown() != 0) {
			resultsSerivce.deleteByCandidatName(name);
			return "Record removed!";
		} else
			throw new CandidateNotFoundException("");
	}

	/* Delete by id */
	@DeleteMapping("/remove/{id}")
	public void removeCandidate(@PathVariable long id) {
		resultsSerivce.deleteById(id);

	}

}