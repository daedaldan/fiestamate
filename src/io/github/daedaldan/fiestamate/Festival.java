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
	
	public Festival(String fileName) {
		this.festivalEvents = processEventsFile(fileName);
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
		String[] eventData = data.split(";");
		String title = eventData[0];
		String description = eventData[1];
		String location = eventData[2];
		LocalDateTime dateTime;
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			dateTime = LocalDateTime.parse(eventData[3] + " " + eventData[4], formatter);
		} catch (DateTimeParseException e) {
			dateTime = LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0);
			System.out.println(e);
		}
			
		Event myEvent = new Event(title, description, location, dateTime);
		
		return myEvent;
	}
}
