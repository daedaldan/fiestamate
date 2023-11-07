package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;

/**
 * The Event class represents an event at a festival and can be
 * extended to create more specific event types.
 * 
 * Each event has a title, description, location, date/time, price, and ID.
 * 
 * The user can access information about any of these event details.
 * 
 * @author danielwang
 *
 */
public class Event {
	// The number of all events in existence.
	protected static int numEvents = 0;
	
	// The title and description of the event.
	private String title;
	private String description;
	// Where the event is located.
	private String location;
	// The date and time that the event is taking place.
	private LocalDateTime eventDateTime;
	// The price of admission for the event.
	private double price;
	// Each Event has a unique integer identifier.
	protected int eventID;
	
	/**
	 * Constructor for creating an Event object based on its details.
	 * 
	 * @param title String for the event title
	 * @param description String for the event description
	 * @param location String for the event location
	 * @param eventDateTime LocalDateTime object for when the event begins
	 * @param price double for the cost of admission to the event
	 */
	public Event(String title, String description, String location, LocalDateTime eventDateTime, double price) {
		this.title = title;
		this.description = description;
		this.location = location;
		this.eventDateTime = eventDateTime;
		this.price = price;
		
		// Increment the number of events in existence.
		numEvents++;
		// Set the event's unique identifier to numEvents.
		this.eventID = numEvents;
	}
	
	/**
	 * Returns the title of the event.
	 * 
	 * @return String representing title of the event
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the description of the event.
	 * 
	 * @return String representing description of the event
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Returns the location of the event.
	 * 
	 * @return String representing location of the event
	 */
	public String getLocation() {
		return this.location;
	}
	
	/**
	 * Returns the date and time that the event starts.
	 * 
	 * @return LocalDateTime object representing start date and time for the event
	 */
	public LocalDateTime getEventDateTime() {
		return this.eventDateTime;
	}
	
	/**
	 * Returns the price of the event.
	 * 
	 * @return double representing cost of admission of the event
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Returns the ID of the event.
	 * 
	 * @return int representing the event ID
	 */
	public int getEventID() {
		return this.eventID;
	}
	
	/**
	 * Returns a String containing all of the event details.
	 * 
	 * @return String containing all of the event details (title, description, location, time, and price).
	 */
	public String toString() {
		// Initialize DateTimeFormatter for converting the LocalDateTime object to a String with the time and date.
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("h:mm a 'on' MM/dd/yyyy");
		// Initialize DecimalFormat object for formatting the price of the event.
		DecimalFormat decimalFormatter = new DecimalFormat("#.##");
		
		return this.title + ": " + this.description + "\n" + this.location + ", " 
				+ this.eventDateTime.format(dateFormatter) + "\n" + (this.price == 0 ? "Free" : "Price of $" + decimalFormatter.format(this.price));
	}
}
