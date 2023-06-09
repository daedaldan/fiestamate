package io.github.daedaldan.fiestamate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FiestaMate extends Application {
	private ListView<Event> festivalListView;
	private ObservableList<Event> festivalList;
	private Festival harvestDay = new Festival("/Users/danielwang/git/fiestamate/src/io/github/daedaldan/fiestamate/events.csv");
	
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
		listContainer.setSpacing(10);
		
		// Create list view for festival events
		festivalListView = new ListView<>();
		festivalListView.setPrefHeight(400);
		festivalListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		festivalList = FXCollections.observableArrayList(harvestDay.getFestivalEvents());
		festivalListView.setItems(festivalList);
		
		// Add components to containers
		listContainer.getChildren().addAll(festivalListView);
		
		mainPane.setCenter(listContainer);
		
		Scene scene = new Scene(mainPane, 700, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
