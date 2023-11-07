package io.github.daedaldan.fiestamate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FiestaMate is a desktop GUI application that allows the user to browse festival event details loaded from a CSV file.
 * 
 * The user can add events from the festival to his/her itinerary and export his/her itinerary as a PDF.
 * 
 * @author danielwang
 *
 */
public class FiestaMate extends Application {
	// Create Festival object using CSV and TXT file paths.
	private static Festival harvestDay = new Festival("/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/events.csv",
												"/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/festival.txt");
	
	// Create ListView and ObservableList objects for the event details and user itinerary.
	private static ObservableList<Event> eventsList = FXCollections.observableArrayList(harvestDay.getFestivalEvents());	
	private static ObservableList<Event> itineraryList = FXCollections.observableArrayList();
	ListView<Event> eventsListView;
	ListView<Event> itineraryListView;
	
	/*
	 * Start the JavaFX application by calling launch in the main method.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Display the festival events and user itinerary events in separate ListViews,
	 * with buttons allowing the user to add selected events to his/her itinerary and export
	 * his/her itinerary as a PDF.
	 */
	public void start(Stage primaryStage) {
		// Set the title of the window.
		primaryStage.setTitle("FiestaMate");
		
		// Create a BorderPane for the root of the Scene with padding on each side.
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(10));
		
		// Create a VBox to store the ListViews and Buttons.
		VBox listContainer = new VBox();
		// Horizontally center the child widgets.
		listContainer.setAlignment(Pos.CENTER);
		// Set the vertical spacing between the child widgets.
		listContainer.setSpacing(10);
		
		// Create text for the title.
		Text titleText = new Text(this.harvestDay.getTitle());
		titleText.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		
		// Create text for the description.
		Text descriptionText = new Text(this.harvestDay.getDescription());
		descriptionText.setFont(Font.font("Helvetica", 16));
		descriptionText.setWrappingWidth(680);
		descriptionText.setTextAlignment(TextAlignment.CENTER);
				
		// Initialize the ListView for the events list.
		eventsListView = new ListView<>(eventsList);
		eventsListView.setPrefHeight(500);
		// Allow the user to select multiple events from the list.
		eventsListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		// Create heading text for the itinerary.
		Text itineraryText = new Text("My Events");
		itineraryText.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		
		// Initialize the ListView for the itinerary list.
		itineraryListView = new ListView<>(itineraryList);
		itineraryListView.setPrefHeight(300);
		
		// Create a button to add events to the itinerary.
		Button addButton = new Button("Add to My Events");
		addButton.setOnAction(e -> addToItinerary());
		addButton.setPrefWidth(200);
		
		// Create a button to export itinerary events.
		Button exportButton = new Button("Export My Events to Downloads");
		exportButton.setOnAction(e -> exportItinerary());
		exportButton.setPrefWidth(200);
		
		// Add widgets to containers.
		listContainer.getChildren().addAll(titleText, descriptionText, eventsListView, addButton, 
											itineraryText, itineraryListView, exportButton);
		mainPane.setCenter(listContainer);
		
		// Set the scene of the stage to the mainPane and show the stage.
		primaryStage.setScene(new Scene(mainPane, 700, 700));
		primaryStage.show();
	}
	
	/**
	 * Adds the selected items in the events ListView to the itineraryList ObservableList.
	 */
	private void addToItinerary() {
		// Get the selected items in the events ListView's selection model.
		ObservableList<Event> selectedItems = eventsListView.getSelectionModel().getSelectedItems();
		// Add the selected Event items to itineraryList.
		itineraryList.addAll(selectedItems);
	}
	
	/**
	 * Exports the itinerary events as a PDF to the user's Downloads folder using the ItineraryToPDF class.
	 */
	private void exportItinerary() {
		String itineraryString = getItineraryAsString();
		ItineraryToPDF.export(itineraryString, "/Users/danielwang/Downloads/festival_events.PDF");
	}
	
	/**
	 * Returns String with text for each itinerary event separated by new line.
	 * 
	 * @return String with text for each itinerary event separated by new line.
	 */
	private String getItineraryAsString() {
		 StringBuffer sb = new StringBuffer();

	     for (Event item : itineraryList) {
	    	 sb.append(item).append("\n");
	     }

	     return sb.toString();
	}
}
