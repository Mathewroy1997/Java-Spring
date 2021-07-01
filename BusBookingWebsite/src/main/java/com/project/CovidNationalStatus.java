package com.project;

import java.util.List;

public class CovidNationalStatus {

	private double activeCases;
	private double recovered;
	private double deaths;
	private double totalCases;
	public List<CovidStateStatus> stateWise;
	public double getActiveCases() {
		return activeCases;
	}
	public void setActiveCases(double activeCases) {
		this.activeCases = activeCases;
	}
	public double getRecovered() {
		return recovered;
	}
	public void setRecovered(double recovered) {
		this.recovered = recovered;
	}
	public double getDeaths() {
		return deaths;
	}
	public void setDeaths(double deaths) {
		this.deaths = deaths;
	}
	public double getTotalCases() {
		return totalCases;
	}
	public void setTotalCases(double totalCases) {
		this.totalCases = totalCases;
	}
}
