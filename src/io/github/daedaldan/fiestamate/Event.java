package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
	private static int numEvents = 0;
	
	private String title;
	private String description;
	private String location;
	private LocalDateTime eventDateTime;
	private int eventID;
	private int attendees;
	
	public Event(String title, String description, String location, LocalDateTime eventDateTime) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.eventDateTime = eventDateTime;
		this.attendees = 0;
		
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
	
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm 'on' MM/dd/yyyy");
		return this.title + ": " + this.description + "\n" + this.location + ", " + this.eventDateTime.format(formatter);
	}
}
