package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;

public class Food extends Event {
	private String menuItem;
	
	public Food(String title, String description, String location, LocalDateTime eventDateTime, 
		       double price, String menuItem){
		super(title, description, location, eventDateTime, price);
		
		this.menuItem = menuItem;
	}
	
	public String getMenuItem() {
		return this.menuItem;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", " + this.menuItem;
	}
}
