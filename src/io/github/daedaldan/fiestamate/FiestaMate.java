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

public class FiestaMate extends Application {
	// Create Festival object using CSV and TXT file paths
	private Festival harvestDay = new Festival("/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/events.csv",
												"/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/festival.txt");
	
	// Create ListView and ObservableList objects for festival events and itinerary events
	private ListView<Event> festivalListView;
	private ObservableList<Event> festivalList;
	private ListView<Event> itineraryListView;
	private ObservableList<Event> itineraryList;
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		// set title of application
		primaryStage.setTitle("Fiesta Mate");
		
		// Create main UI components
		BorderPane mainPane = new BorderPane();
		mainPane.setPadding(new Insets(10));
		
		VBox listContainer = new VBox();
		listContainer.setAlignment(Pos.CENTER);
		listContainer.setSpacing(10);
		
		// Create title text element
        Text titleText = new Text(this.harvestDay.getTitle());
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        // Create description text element
        Text descriptionText = new Text(this.harvestDay.getDescription());
        descriptionText.setFont(Font.font("Arial", 16));
        descriptionText.setWrappingWidth(680);
        descriptionText.setTextAlignment(TextAlignment.CENTER);
        
		// Create list view for festival events
		festivalListView = new ListView<>();
		festivalListView.setPrefHeight(400);
		festivalListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		festivalList = FXCollections.observableArrayList(harvestDay.getFestivalEvents());
		festivalListView.setItems(festivalList);
		
		// Create heading text for itinerary
        Text itineraryText = new Text("My Events");
        itineraryText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		
		// Create list view for itinerary events
		itineraryListView = new ListView<>();
		itineraryListView.setPrefHeight(200);
		itineraryList = FXCollections.observableArrayList();
		itineraryListView.setItems(itineraryList);
		
		// Create button to add events to itinerary
        Button addButton = new Button("Add to My Events");
        addButton.setOnAction(e -> addToItinerary());
        addButton.setPrefWidth(200);
        
        // Create button to export itinerary events
        Button exportButton = new Button("Export My Events to Downloads");
        exportButton.setOnAction(e -> exportItinerary());
        exportButton.setPrefWidth(200);
		
		// Add components to containers
		listContainer.getChildren().addAll(titleText, descriptionText, 
											festivalListView, addButton,
											itineraryText, itineraryListView, exportButton);
		
		mainPane.setCenter(listContainer);
		
		Scene scene = new Scene(mainPane, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// Add selected events from festivalListView to itineraryList
	private void addToItinerary() {
		ObservableList<Event> selectedItems = festivalListView.getSelectionModel().getSelectedItems();
		itineraryList.addAll(selectedItems);
	}
	
	//  Export itinerary events as PDF using ItineraryToPDF class
	private void exportItinerary() {
		String itineraryString = getItineraryAsString();
		ItineraryToPDF.export(itineraryString, "/Users/danielwang/Downloads/festival_events.PDF");
	}
	
	// Returns String with text for each itinerary event separated by new line
	private String getItineraryAsString() {
		 StringBuffer sb = new StringBuffer();

	        for (Event item : itineraryList) {
	            sb.append(item).append("\n");
	        }

	        return sb.toString();
	}
}
