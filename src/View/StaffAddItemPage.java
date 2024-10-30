package View;

import Factories.Factory;
import Handlers.StaffHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;

public class StaffAddItemPage {

	public static EventHandler<ActionEvent> initAddItemPage(Stage staffStage) {

		return event -> {

			GridPane formLayout = new GridPane();
			formLayout.setPadding(new Insets(20));
			formLayout.setHgap(10);
			formLayout.setVgap(5);

			Label pageTitleLabel = new Label("Add Item");
			Label nameLabel = new Label("Item Name:");
			TextField nameField = new TextField();
			Label descLabel = new Label("Item Desc:");
			TextArea descArea = new TextArea();
			Label categoryLabel = new Label("Item Category:");
			TextField categoryField = new TextField();
			Label priceLabel = new Label("Item Price:");
			TextField priceField = new TextField();
			Label quantityLabel = new Label("Quantity:");
			Spinner<Integer> quantitySpinner = new Spinner<>(1, 300, 1);
			Button submitButton = Factory.createButton("Add Item");

			formLayout.add(pageTitleLabel, 0, 0, 2, 1);
			formLayout.add(nameLabel, 0, 1);
			formLayout.add(nameField, 1, 1);
			formLayout.add(descLabel, 0, 2);
			formLayout.add(descArea, 1, 2);
			formLayout.add(categoryLabel, 0, 3);
			formLayout.add(categoryField, 1, 3);
			formLayout.add(priceLabel, 0, 4);
			formLayout.add(priceField, 1, 4);
			formLayout.add(quantityLabel, 0, 5);
			formLayout.add(quantitySpinner, 1, 5);
			formLayout.add(submitButton, 1, 6);

			pageTitleLabel.setStyle("-fx-font-size: 28px; -fx-text-fill: #333;");
			nameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
			descLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
			categoryLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
			priceLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
			quantityLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #333;");
			nameField.setStyle(
					"-fx-pref-height: 20px; -fx-font-size: 14px; -fx-pref-width: 250px; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 5px; -fx-background-radius: 5px;");
			descArea.setStyle(
					"-fx-pref-height: 20px; -fx-font-size: 14px;-fx-pref-width: 250px; -fx-pref-height: 250px; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 5px; -fx-background-radius: 5px;");
			categoryField.setStyle(
					"-fx-pref-height: 20px; -fx-font-size: 14px;-fx-pref-width: 250px; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 5px; -fx-background-radius: 5px;");
			priceField.setStyle(
					"-fx-pref-height: 20px; -fx-font-size: 14px;-fx-pref-height: 20px; -fx-font-size: 10px;-fx-pref-width: 250px; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 5px; -fx-background-radius: 5px;");
			quantitySpinner.setStyle(
					"-fx-pref-height: 20px; -fx-font-size: 14px;-fx-pref-width: 250px; -fx-padding: 5px; -fx-border-color: #ddd; -fx-border-radius: 5px; -fx-background-radius: 5px;");
			submitButton.setStyle(
					"-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
			submitButton.setOnMouseEntered(e -> submitButton.setStyle(
					"-fx-background-color: #4cae4c; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));
			submitButton.setOnMouseExited(e -> submitButton.setStyle(
					"-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;"));

//			descLabel.setTranslateY(-115);
			descArea.setWrapText(true);

			submitButton.setOnAction(e -> {
				StaffHandler.handleAddItem(nameField, descArea, categoryField, priceField, quantitySpinner);
			});

			Window window = new Window();
			window.getContentPane().getChildren().add(formLayout);
			window.setMaxHeight(500);
			window.setMaxWidth(800);
			window.setTitle("");

			BorderPane bp = new BorderPane();
			bp.setTop(Factory.createAdminMenuBar(staffStage));
			bp.setCenter(window);

			// Create a new scene for the "Add Item" page
			Scene addItemScene = new Scene(bp, 1000, 600);

			// Set the new scene on the primary stage
			staffStage.setScene(addItemScene);
		};

	}

}
