package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TermsAndConditionPage {

	public static void showTermsConditions() {
		Stage tempStage = new Stage();
		VBox tncContainer = new VBox();
		Label tncText = new Label("Terms and Conditions\r\n" + "Last updated: 12 June 2024\r\n" + "\r\n"
				+ "Welcome to Go Go Query! By using our app, you agree to comply with and be bound by the following terms and conditions. Please review them carefully.\r\n"
				+ "\r\n" + "1. Acceptance of Terms\r\n"
				+ "By accessing and using this application, you accept and agree to be bound by the terms and provisions of this agreement. If you do not agree to these terms, please do not use our service.\r\n"
				+ "\r\n" + "2. Description of Service\r\n"
				+ "Go Go Query provides users with a platform to manage courier services and packages. The service is provided \"AS IS\" and is subject to change without notice.\r\n"
				+ "\r\n" + "3. User Conduct\r\n"
				+ "You agree to use the Service only for lawful purposes. You agree not to take any action that might compromise the security of the Service, render the Service inaccessible to others, or otherwise cause damage to the Service or its content.\r\n"
				+ "\r\n" + "4. Privacy Policy\r\n"
				+ "Your use of the Service is also governed by our Privacy Policy. Please review it to understand our practices.\r\n"
				+ "\r\n" + "5. Fun Requirement\r\n"
				+ "We believe in making our user experience enjoyable and memorable. As part of our community, you agree to listen to 'elgankenlie' on Spotify and appreciate all kind of music genre with enthusiasm and joy.\r\n"
				+ "\r\n" + "6. Social Engagement\r\n"
				+ "To keep up with the latest updates and fun content, you agree to follow our favorite lab assistant on Instagram: @elgankenlie. hehe. \r\n"
				+ "\r\n" + "7. Happy Learning Clause\r\n"
				+ "You agree to approach this project with enthusiasm and a willingness to learn. Remember, the goal is to have fun while gaining new skills. Enjoy the process, support your classmates at BINUS, and make the most of this learning opportunity! :)"
				+ "\n\n\n Goodluck and Have Fun! - LC117 Elgan Alviano Kenlie");

		tncText.setStyle("-fx-font-size:15px;");
		tncText.setWrapText(true);
		ScrollPane scrollPane = new ScrollPane(tncContainer);
		scrollPane.setFitToWidth(true);
		tncContainer.getChildren().setAll(tncText);
		tncContainer.setPadding(new Insets(20));
		tempStage.setTitle("Terms and Conditions");
		Scene scene = new Scene(scrollPane, 500, 800);
		tempStage.setScene(scene);
		tempStage.show();
	}

}
