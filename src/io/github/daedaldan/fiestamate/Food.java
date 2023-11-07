package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;

/**
 * The Food class represents a food venue at the festival.
 * 
 * The class contains info about the menu item offered, in addition to the 
 * title, description, location, date/time, and price of the event.
 * 
 * The user can access information about any of these event details.
 * 
 * @author danielwang
 *
 */
public class Food extends Event {
	// Menu item being offered at the food venue.
	private String menuItem;
	
	/**
	 * Constructor for creating a Food object based on its details.
	 * 
	 * @param title String for the food venue title
	 * @param description String for the food venue description
	 * @param location String for the food venue location
	 * @param eventDateTime LocalDateTime object for when the food venue opens
	 * @param price double for the cost of purchasing the menu item
	 * @param menuItem String for the menu iterm being offered
	 */
	public Food(String title, String description, String location, LocalDateTime eventDateTime, 
		       double price, String menuItem){
		// Call parent constructor in Event class.
		super(title, description, location, eventDateTime, price);
		
		this.menuItem = menuItem;
	}
	
	/**
	 * Returns a String representing the name of the menu item being offered.
	 * 
	 * @return String representing the name of the menu item being offered.
	 */
	public String getMenuItem() {
		return this.menuItem;
	}
	
	/**
	 * Returns s String containing all of the food venue details.
	 * 
	 * @return String containing all of the food venue details 
	 * (title, description, location, time, price, and menu item).
	 */
	@Override
	public String toString() {
		return super.toString() + ", " + this.menuItem;
	}
}
