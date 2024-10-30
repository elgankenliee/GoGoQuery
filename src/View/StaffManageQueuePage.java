package View;

import Entities.ModifiedEntities.TransactionInfo;
import Factories.Factory;
import Handlers.TransactionHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import jfxtras.labs.scene.control.window.Window;

public class StaffManageQueuePage {

	public static void initManageQueuePage(Stage staffStage) {
		// Title Label
		Label pageTitleLabel = new Label("Queue Manager");
		pageTitleLabel.setFont(new Font("Arial", 28));
		pageTitleLabel.setStyle("-fx-text-fill: #333;");

		// Create TableView and columns
		TableView<TransactionInfo> tableView = new TableView<>();
		TableColumn<TransactionInfo, Integer> idColumn = new TableColumn<>("Transaction ID");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("transactionID"));

		TableColumn<TransactionInfo, Integer> userIDColumn = new TableColumn<>("Customer ID");
		userIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));

		TableColumn<TransactionInfo, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("dateCreated"));

		TableColumn<TransactionInfo, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		TableColumn<TransactionInfo, Double> totalPriceColumn = new TableColumn<>("Total");
		totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

		TableColumn<TransactionInfo, Double> userEmailColumn = new TableColumn<>("Customer Email");
		userEmailColumn.setCellValueFactory(new PropertyValueFactory<>("customerEmail"));
		tableView.getColumns().clear();
		tableView.getColumns().addAll(idColumn, userIDColumn, userEmailColumn, dateColumn, totalPriceColumn,
				statusColumn);

		// Dummy data for demonstration
		ObservableList<TransactionInfo> transactions = FXCollections
				.observableArrayList(TransactionHandler.fetchTransactionInfo());
		tableView.setItems(transactions);
		tableView.setStyle("-fx-font-size: 12px;");

		// Button to mark transaction as ready to send
		Button sendPackageButton = new Button("Send Package");
		sendPackageButton.setStyle(
				"-fx-background-color: #5cb85c; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-border-radius: 5px; -fx-background-radius: 5px;");
		sendPackageButton.setOnAction(event -> {
			TransactionInfo selectedTransaction = tableView.getSelectionModel().getSelectedItem();
			if (selectedTransaction != null) {
				selectedTransaction.setStatus("Sent");
				// Update the backend or database here
				tableView.refresh();
			} else {
				Alert alert = Factory.createError("Reference Error", "Reference error due to no transaction selected",
						"Please select a transaction");
				alert.showAndWait();
			}
		});

		// Layout containers
		VBox buttonContainer = new VBox(sendPackageButton);
		buttonContainer.setSpacing(10);
		buttonContainer.setPadding(new Insets(10, 0, 0, 0));
		buttonContainer.setMinHeight(80);

		VBox tableContainer = new VBox(pageTitleLabel, tableView, buttonContainer);
		tableContainer.setSpacing(20);
		tableContainer.setPadding(new Insets(20));
		tableContainer.setMinWidth(900);

		// Using JFXtras Window
		Window window = new Window();
		window.getContentPane().getChildren().add(tableContainer);
		window.setMaxHeight(500);
		window.setMaxWidth(800);
		window.setTitle("");

		// Main layout
		BorderPane mainLayout = new BorderPane();
		mainLayout.setTop(Factory.createAdminMenuBar(staffStage)); // Assuming Factory.createAdminMenuBar is a method
																	// that returns a MenuBar
		mainLayout.setCenter(window);

		// Scene setup
		Scene scene = new Scene(mainLayout, 1000, 600);
		staffStage.setScene(scene);
		staffStage.show();
	}

}