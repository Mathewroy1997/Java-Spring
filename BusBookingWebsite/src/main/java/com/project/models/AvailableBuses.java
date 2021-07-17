package com.project.models;

public class AvailableBuses {
	private int busId;
	private String busType;
	private int ratePerSeat;
	private int availableSeats;
	
	private int tripId;

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

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

	public int getRatePerSeat() {
		return ratePerSeat;
	}

	public void setRatePerSeat(int ratePerSeat) {
		this.ratePerSeat = ratePerSeat;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}
