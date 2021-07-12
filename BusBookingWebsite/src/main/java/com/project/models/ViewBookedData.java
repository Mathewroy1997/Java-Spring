package com.project.models;

public class ViewBookedData {
	public int userid;

	public String date;
	public String departure;
	public String destination;
	public int tickets;

	public int tripid;


public int getTripid() {
	return tripid;
}
public void setTripid(int tripid) {
	this.tripid = tripid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
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
public int getTickets() {
	return tickets;
}
public void setTickets(int tickets) {
	this.tickets = tickets;
}

}
