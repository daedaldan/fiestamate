package io.github.daedaldan.fiestamate;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a festival and all of the events associated with the festival.
 * 
 * The class is initialized based on input data from a CSV file containing information 
 * about each event at the festival. 
 * 
 * @author danielwang
 *
 */
public class Festival {
	// ArrayList of Events offered at the festival.
	private ArrayList<Event> festivalEvents = new ArrayList<Event>();
	// Title of the festival.
	private String title;
	// Description of the festival.
	private String description;
	
	/**
	 * Constructor for creating a Festival object based on event data from a CSV file.
	 * 
	 * @param eventsFileName name of the input CSV file containing the information for each event.
	 * @param festivalFileName name of the output TXT file that the festival information will be written to.
	 */
	public Festival(String eventsFileName, String festivalFileName) {
		// Populate the festivalEvents ArrayList using the event data in the CSV file.
		this.festivalEvents = processEventsFile(eventsFileName);
		
		// Read festival title and description from text file with details.
		try (Scanner scan = new Scanner(new File(festivalFileName))) {
			// Read title of festival from first line.
			this.title = scan.nextLine();
			// Read description of festival from second line.
			this.description = scan.nextLine();
		} catch (Exception e) {
			System.out.println(e);
			
			// Add generic title and description if error occurs while opening or reading file.
			this.title = "Festival";
			this.description = "No description found";
		}
	}
	
	/**
	 * Returns ArrayList of Event objects representing festival events.
	 * 
	 * @return ArrayList of Events at the festival
	 */
	public ArrayList<Event> getFestivalEvents() {
		return this.festivalEvents;
	}
	
	/**
	 * Returns the title of the festival.
	 * 
	 * @return String representing title of the festival
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * Returns the description of the festival.
	 * 
	 * @return String representing description of the festival
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Returns String combining the text for each event separated by a new line.
	 * 
	 * @return String combining the text for each event separated by a new line
	 */
	public String toString() {
		// Create a new StringBuffer object.
		StringBuffer sb = new StringBuffer();
		
		// For each Event in the festivalEvents ArrayList, append its information to the StringBuffer, then append a new line.
		for (Event currentEvent: this.festivalEvents) {
			sb.append(currentEvent);
			sb.append("\n\n");
		}
		
		// Return the String containing the information for all of the festival events.
		return sb.toString();
	}
	
	/*  
	 * This method uses a Scanner to read the event details stored in CSV file row-by-row,
	 * with each row containing details for a single Event object.
	 * 
	 * @param fileName String representing name of CSV file with the event details
	 * @return ArrayList of Event objects created from the CSV file data
	 */
	private ArrayList<Event> processEventsFile(String fileName) {
		// Initialize empty ArrayList of Event objects.
		ArrayList<Event> events = new ArrayList<Event>();
		
		// Try initializing a Scanner using the specified fileName.
		try (Scanner scan = new Scanner(new File(fileName))) {
			// Skip the header row in the CSV file.
			if (scan.hasNextLine()) {
				scan.nextLine();
			}
			// Process each row with event details.
			while (scan.hasNextLine()) {
				events.add(processEventData(scan.nextLine()));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return events;
	}
	
	/*  
	 * This method uses a Scanner to read the information for a single event,
	 * given a String of event details created from a single row of the CSV file.
	 * 
	 * @param data String representing a single row in the CSV file
	 * @return Event object created using the event information
	 */
	private Event processEventData(String data) {
		// Split input String data into separate variables.
		String[] eventData = data.split(";");
		
		// Assign the data from each column in the CSV file to its corresponding variable.
		String eventType = eventData[6];
		String title = eventData[0];
		String description = eventData[1];
		String location = eventData[2];
		LocalDateTime dateTime;
		double price = Double.parseDouble(eventData[5]);
		
		// Combine the date and time values in the String to create a LocalDateTime object.
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			dateTime = LocalDateTime.parse(eventData[3] + " " + eventData[4], formatter);
		} catch (DateTimeParseException e) {
			// If an error occurs parsing the date and time, set the date and time to January 1st, 2000.
			dateTime = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0);
			System.out.println(e);
		}
		
		// Initialize and return the proper object using the event type and input String's details.
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
