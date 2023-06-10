package io.github.daedaldan.fiestamate;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ItineraryToPDF {
	public static void export(String itinerary, String filePath) {
        try {
            // Create a new PDF document
            PDDocument document = new PDDocument();

            // Create a page and add it to the document
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            // Create a content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set the font and font size for the text
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

            // Set the starting position for the text
            float startX = 50;
            float startY = page.getMediaBox().getHeight() - 50;

            // Split the shopping list into separate lines
            String[] lines = itinerary.split("\n");

            // Write each line of the shopping list to the PDF
            for (String line : lines) {
                contentStream.beginText();
                contentStream.newLineAtOffset(startX, startY);
                contentStream.showText(line);
                contentStream.endText();
                startY -= 15; // Adjust the vertical position for the next line
            }

            // Close the content stream and save the document
            contentStream.close();
            document.save(filePath);
            document.close();

            System.out.println("Shopping list exported to: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
