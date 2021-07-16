package com.project.models;

public class RouteDetails {
	private int routeId;
	private String departure;
	private String destination;
	private int totalDistanceInKm;

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getTotalDistanceInKm() {
		return totalDistanceInKm;
	}

	public void setTotalDistanceInKm(int totalDistanceInKm) {
		this.totalDistanceInKm = totalDistanceInKm;
	}

}
