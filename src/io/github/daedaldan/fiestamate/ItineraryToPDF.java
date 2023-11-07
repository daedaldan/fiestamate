package io.github.daedaldan.fiestamate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * This class contains a single method that allows the user to 
 * export his/her festival event itinerary to a PDF file.
 * 
 * @author danielwang
 *
 */
public class ItineraryToPDF {
	/**
	 * Creates a PDF with the user's festival itinerary on the local computer.
	 * 
	 * @param itinerary String representing user's festival itinerary
	 * @param filePath String representing path where PDF file will be created
	 */
	public static void export(String itinerary, String filePath) {        
        try {
        	// Initialize the PDDocument object.
        	PDDocument doc = new PDDocument();
        	// Initialize the first page of the document.
        	PDPage page = new PDPage();
        	// Add the first page to the document.
        	doc.addPage(page);
        	// Create a new content stream with the newly created document and page.
        	PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        	
        	
        	// Set the font of the text to 12 pt Helvetica.
        	contentStream.setFont(PDType1Font.HELVETICA, 12);
        	// Set the starting position of the text.
        	float startX = 50;
        	float startY = page.getMediaBox().getHeight() - 50;
        	
        	// Split the itinerary into multiple lines.
        	String[] lines = itinerary.split("\n");
        	
        	// Write each line of the itinerary to the PDF.
        	for (String line : lines) {
        		// Begin the content stream.
            	contentStream.beginText();
            	// Set the starting position of the text.
            	contentStream.newLineAtOffset(startX, startY);
            	
            	// Write the itinerary information to the PDF.
            	contentStream.showText(line);
            	
            	// End the text content and close the content stream.
            	contentStream.endText();
            	
            	// Adjust the vertical starting position for the next line.
            	startY -= 15;
        	}
        	
        	// Close the content stream.
        	contentStream.close();
        	
        	// Save the document and close it.
        	doc.save(filePath);
        	doc.close();
        } catch (Exception e) {
        	System.out.println(e);
        }
    }
}
