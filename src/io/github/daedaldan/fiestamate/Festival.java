package io.github.daedaldan.fiestamate;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Festival {
	private ArrayList<Event> festivalEvents = new ArrayList<Event>();
	private String title;
	private String description;
	
	public Festival(String eventsFileName, String festivalFileName) {
		this.festivalEvents = processEventsFile(eventsFileName);
		
		// Read festival title and description from text file with details
		try (Scanner scan = new Scanner(new File(festivalFileName))) {
			// read title of festival from first line
			this.title = scan.nextLine();
			// read description of festival from second line
			this.description = scan.nextLine();
		} catch (Exception e) {
			System.out.println(e);
			
			// Add generic title and description if error occurs while opening or reading file
			this.title = "Festival";
			this.description = "No description found";
		}
	}
	
	public ArrayList<Event> getFestivalEvents() {
		return this.festivalEvents;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	// Returns String combining the text for each event separated by a new line
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Event currentEvent: this.festivalEvents) {
			sb.append(currentEvent);
			sb.append("\n\n");
		}
		
		return sb.toString();
	}
	
	/*  
	 * Uses Scanner to read event details stored in CSV file row-by-row.
	 * Each row contains details for a single Event object.
	 */
	private ArrayList<Event> processEventsFile(String fileName) {
		ArrayList<Event> events = new ArrayList<Event>();
		try (Scanner scan = new Scanner(new File(fileName))) {
			// skip header row
			if (scan.hasNextLine()) {
				scan.nextLine();
			}
			// process each row with event details
			while (scan.hasNextLine()) {
				events.add(processEventData(scan.nextLine()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return events;
	}
	
	private Event processEventData(String data) {
		// split input String data into separate variables
		String[] eventData = data.split(";");
		String eventType = eventData[6];
		
		String title = eventData[0];
		String description = eventData[1];
		String location = eventData[2];
		LocalDateTime dateTime;
		double price = Double.parseDouble(eventData[5]);
		
		// combine date and time values in String to create LocalDateTime object
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			dateTime = LocalDateTime.parse(eventData[3] + " " + eventData[4], formatter);
		} catch (DateTimeParseException e) {
			dateTime = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0);
			System.out.println(e);
		}
		
		// initialize object using input String's details and event type
		if (eventType.equals("Concert")) {
			String performer = eventData[8];
			String musicType = eventData[9];
			
			return new Concert(title, description, location, dateTime, price, performer, musicType);
		} else if (eventType.equals("Food")) {
			String menuItem = eventData[7];
			
			return new Food(title, description, location, dateTime, price, menuItem);
		} else {
			return new Event(title, description, location, dateTime, price);
		}
	}
}
