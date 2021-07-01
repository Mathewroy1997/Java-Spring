package com.project;

public class Trip {

	
int routeID;
	int tripID;
	int seats;
	String date;
	public int totalPrice;
	public int getRouteID() {
		return routeID;
	}
	public void setRouteID(int routeID) {
		this.routeID = routeID;
	}
	public int getTripID() {
		return tripID;
	}
	public void setTripID(int tripID) {
		this.tripID = tripID;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTotalprice(int totalPrice) {
		this.totalPrice=totalPrice;
	}
}
