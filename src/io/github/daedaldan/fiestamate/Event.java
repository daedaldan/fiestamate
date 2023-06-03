package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {
	protected static int numEvents = 0;
	
	private String title;
	private String description;
	private String location;
	private LocalDateTime eventDateTime;
	protected int eventID;
	
	public Event(String title, String description, String location, LocalDateTime eventDateTime) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.eventDateTime = eventDateTime;
		
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
	
	public int getEventID() {
		return this.eventID;
	}
	
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm 'on' MM/dd/yyyy");
		return this.title + ": " + this.description + "\n" + this.location + ", " + this.eventDateTime.format(formatter);
	}
}
