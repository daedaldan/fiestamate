package io.github.daedaldan.fiestamate;

import java.util.ArrayList;
import java.util.Scanner;

public class Festival {
	private ArrayList<Event> festivalEvents = new ArrayList<Event>();
	
	public Festival(String fileName) {
		this.festivalEvents = processEventData(fileName);
	}
	
	/*  
	 * Uses Scanner to read event details stored in CSV file row-by-row.
	 * Each row contains details for a single Event object.
	 */
	private ArrayList<Event> processEventData(String fileName) {
		ArrayList<Event> events = new ArrayList<Event>();
		
		try (Scanner scan = new Scanner(fileName)) {
			// skip header row
			if (scan.hasNextLine()) {
				scan.nextLine();
			}
			// process each row with event details
			while (scan.hasNextLine()) {
				System.out.println(scan.nextLine());
			}
		} catch (Exception e) {
			System.out.println("The CSV file '" + fileName + "' is invalid.");
		}
		
		
		return events;
	}
}
