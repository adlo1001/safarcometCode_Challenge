package com.addis.safari_interview.safari.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Interview_Results {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String candidatName;

	/*set min and max values in here. */
	/*@Max(value=100)
	@Min(value = 0)*/
	@NotNull
	private double candidateScore;
	private boolean isSuccessful;

	public Interview_Results() {
		super();

	}

	public Interview_Results(String candidatName, double candidateScore, boolean isSuccessful) {
		super();
		this.candidatName = candidatName;
		this.candidateScore = candidateScore;
		this.isSuccessful = isSuccessful;
	}

	public String getCandidatName() {
		return candidatName;
	}

	public void setCandidatName(String candidatName) {
		this.candidatName = candidatName;
	}

	public double getCandidateScore() {
		return candidateScore;
	}

	public void setCandidateScore(double candidateScore) {
		this.candidateScore = candidateScore;
	}

	public boolean isSuccessful() {
		return isSuccessful;
	}

	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}

	@Override
	public String toString() {
		return "Interview_Results [candidatName=" + candidatName + ", candidateScore=" + candidateScore
				+ ", isSuccessful=" + isSuccessful + "]";
	}

}
