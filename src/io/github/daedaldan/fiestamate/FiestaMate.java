package io.github.daedaldan.fiestamate;

import java.time.LocalDateTime;

public class FiestaMate {
	public static void main(String[] args) {
		Festival harvestDay = new Festival("/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/events.csv");
		
		System.out.println(harvestDay);
		
		Concert swiftEra = new Concert("Eras Tour", "Description", "Wells Fargo Center", LocalDateTime.now(), "Taylor Swift", "Pop");
		
		System.out.println(swiftEra);
		System.out.println(swiftEra.getEventID());
		System.out.println(swiftEra.getTitle());
	}
}
