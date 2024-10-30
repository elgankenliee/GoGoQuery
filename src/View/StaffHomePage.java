package View;

import Factories.Factory;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StaffHomePage {

	public StaffHomePage() {
		// TODO Auto-generated constructor stub
	}

	public static void initStaffHome() {
		Stage staffStage = new Stage();

		BorderPane root = new BorderPane();
		root.setTop(Factory.createAdminMenuBar(staffStage));

		Label welcomeMessage = new Label("Welcome to GoGoQuery Manager 2.0");
		welcomeMessage.setStyle("-fx-font-size: 24px; -fx-text-fill: #333;");
//		welcomeMessage.setFont(Font.font("Arial", FontWeight.BOLD, 24));
//		welcomeMessage.setTextFill(Color.Bl);

		// Set the welcome message in the center of the BorderPane
		root.setCenter(welcomeMessage);

		// Create the scene with the root layout
		Scene scene = new Scene(root, 1000, 600);

		// Set the stage
		staffStage.setTitle("GoGoQuery Manager 2.0");
		staffStage.setScene(scene);
		staffStage.show();
	}

}