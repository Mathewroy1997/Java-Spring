package com.project;

public class Route {

	public int routeID;
	public int rate;
	public String departure;
	public String destination;
	
	
	public int getRouteID() {
		return routeID;
	}

	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDeparture() {
		return departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}
	
	
}
