package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;

/**
 * The Concert class represents a music performance at a festival. 
 * 
 * The class contains info about the performer and music type, in addition to the 
 * title, description, location, date/time, and price of the event.
 * 
 * The user can access information about any of these event details.
 * 
 * @author danielwang
 *
 */
public class Concert extends Event {
	private String performer;
	private String musicType;
	
	/**
	 * Constructor for creating a Concert object based on its details.
	 * 
	 * @param title String for the concert title
	 * @param description String for the concert description
	 * @param location String for the concert location
	 * @param eventDateTime LocalDateTime object for when the concert begins
	 * @param price double for the cost of admission to the concert
	 * @param performer the name of the artist performing at the concert
	 * @param musicType the category of music being performed at the concert
	 */
	public Concert(String title, String description, String location, LocalDateTime eventDateTime, 
			       double price, String performer, String musicType) {
		// Call parent constructor in Event class.
		super(title, description, location, eventDateTime, price);
		
		this.performer = performer;;
		this.musicType = musicType;
	}
	
	/**
	 * Returns the name of the performer at the concert.
	 * 
	 * @return String representing name of the artist performing at the concert.
	 */
	public String getPerformer() {
		return this.performer;
	}
	
	/**
	 * Returns the category of music being performed at the concert.
	 * 
	 * @return String representing category of music being performed.
	 */
	public String getMusicType() {
		return this.musicType;
	}
	
	/**
	 * Returns s String containing all of the concert details.
	 * 
	 * @return String containing all of the concert details 
	 * (title, description, location, time, price, music type, and performer).
	 */
	@Override
	public String toString() {
		return super.toString() + ", " + this.musicType + " Concert by " + this.performer;
	}
}
