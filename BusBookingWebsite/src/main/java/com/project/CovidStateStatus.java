package com.project;

public class CovidStateStatus {
	
	private String region;
	private double activeCases;
	private double recovered;
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
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
	public double getNewDeceased() {
		return newDeceased;
	}
	public void setNewDeceased(double newDeceased) {
		this.newDeceased = newDeceased;
	}
	private double newDeceased;
}
