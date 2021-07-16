package com.project.models;

public class BusDetails {
	private int busId;
	private String busType;
	private int busRatePerKm;
	private int busTotalSeats;

	public int getBusId() {
		return busId;
	}

	public void setBusId(int busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public int getBusRatePerKm() {
		return busRatePerKm;
	}

	public void setBusRatePerKm(int busRatePerKm) {
		this.busRatePerKm = busRatePerKm;
	}

	public int getBusTotalSeats() {
		return busTotalSeats;
	}

	public void setBusTotalSeats(int busTotalSeats) {
		this.busTotalSeats = busTotalSeats;
	}

}
