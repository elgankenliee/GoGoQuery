package View;

import Factories.Factory;
import Main.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RegisterPage {

	public static void initRegisterPage(Stage primaryStage) {
		VBox titleContainer = Factory.createLogo();

		VBox parentContainer = new VBox();
		parentContainer.setAlignment(Pos.CENTER);
		parentContainer.setSpacing(10);

		parentContainer.setBackground(Main.defaultBg);

		parentContainer.getChildren().addAll(titleContainer, Factory.createRegisterForm(primaryStage));

		Scene scene = new Scene(parentContainer, Main.viewPortWidth, Main.viewPortHeight);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
