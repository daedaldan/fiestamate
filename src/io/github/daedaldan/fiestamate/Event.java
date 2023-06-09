package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

public class Event {
	protected static int numEvents = 0;
	
	private String title;
	private String description;
	private String location;
	private LocalDateTime eventDateTime;
	private double price;
	protected int eventID;
	
	public Event(String title, String description, String location, LocalDateTime eventDateTime, double price) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.eventDateTime = eventDateTime;
		this.price = price;
		
		numEvents++;
		this.eventID = numEvents;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public LocalDateTime getEventDateTime() {
		return this.eventDateTime;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public int getEventID() {
		return this.eventID;
	}
	
	public String toString() {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("h:mm a 'on' MM/dd/yyyy");
		DecimalFormat decimalFormatter = new DecimalFormat("#.##");
		
		return this.title + ": " + this.description + "\n" + this.location + ", " 
				+ this.eventDateTime.format(dateFormatter) + "\n" + (this.price == 0 ? "Free" : "Price of $" + decimalFormatter.format(this.price));
	}
}
