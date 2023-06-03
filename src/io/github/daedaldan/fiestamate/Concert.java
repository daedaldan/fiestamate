package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;

public class Concert extends Event {
	private String performer;
	private String musicType;
	
	public Concert(String title, String description, String location, LocalDateTime eventDateTime, 
			       String performer, String musicType) {
		super(title, description, location, eventDateTime);
		
		this.performer = performer;;
		this.musicType = musicType;
	}
	
	public String getPerformer() {
		return this.performer;
	}
	
	public String getMusicType() {
		return this.musicType;
	}
	
	@Override
	public String toString() {
		return super.toString() + "\n" + this.musicType + " Concert by " + this.performer;
	}
}
