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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FiestaMate extends Application {
	private Festival harvestDay = new Festival("/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/events.csv",
												"/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/festival.txt");
	private ListView<Event> festivalListView;
	private ObservableList<Event> festivalList;
	private ListView<Event> itineraryListView;
	private ObservableList<Event> itineraryList;
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		System.out.println(harvestDay);
		
		primaryStage.setTitle("Fiesta Mate");
		
		// Create UI components
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
		
		// Create button
        Button addButton = new Button("Add to My Events");
        addButton.setOnAction(e -> addToItinerary());
        addButton.setPrefWidth(200);
		
		// Add components to containers
		listContainer.getChildren().addAll(titleText, descriptionText, 
											festivalListView, addButton,
											itineraryText, itineraryListView);
		
		mainPane.setCenter(listContainer);
		
		Scene scene = new Scene(mainPane, 700, 700);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void addToItinerary() {
		ObservableList<Event> selectedItems = festivalListView.getSelectionModel().getSelectedItems();
		itineraryList.addAll(selectedItems);
	}
}
