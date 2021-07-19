
package com.project.models;

public class CovidNationalStatus {

	private long activeCases;
	private long deaths;
	private long previousDayTests;
	private long totalCases;

	regionData[] regiondata;

	public long getActiveCases() {
		return activeCases;
	}

	public void setActiveCases(long activeCases) {
		this.activeCases = activeCases;
	}

	public long getDeaths() {
		return deaths;
	}

	public void setDeaths(long deaths) {
		this.deaths = deaths;
	}

	public long getPreviousDayTests() {
		return previousDayTests;
	}

	public void setPreviousDayTests(long previousDayTests) {
		this.previousDayTests = previousDayTests;
	}

	public long getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(long totalCases) {
		this.totalCases = totalCases;
	}

	public regionData[] getRegionData() {
		return regiondata;
	}

	public void setRegionData(regionData[] regiondata) {
		this.regiondata = regiondata;
	}

	@Override
	public String toString() {
		return "CovidReport [activeCases=" + activeCases + ", deaths=" + deaths + ", previousDayTests="
				+ previousDayTests + ", totalCases=" + totalCases + ", regionData=" + regiondata + "]";
	}

}
