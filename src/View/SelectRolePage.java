package View;

import Entities.DatabaseEntities.User;
import Factories.Factory;
import Main.Main;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectRolePage {

	public static void initSelectRolePage(Stage primaryStage, User newUser) {

		VBox titleContainer = Factory.createLogo();

		VBox parentContainer = new VBox();
		parentContainer.setAlignment(Pos.CENTER);
		parentContainer.setSpacing(10);

		parentContainer.setBackground(Main.defaultBg);

		HBox cardContainer = new HBox();
		cardContainer.setMinWidth(Main.viewPortWidth);
		cardContainer.setMaxWidth(Main.viewPortWidth);
		cardContainer.setMinHeight(500);

		VBox managerButton = Factory.createRoleSelectionCard(primaryStage, "Manager",
				"Manage products and deliveries, be the ruler!", newUser);

		VBox customerButton = Factory.createRoleSelectionCard(primaryStage, "Shopper",
				"Search products, manage your cart, go shopping!", newUser);

		cardContainer.getChildren().setAll(managerButton, customerButton);
		cardContainer.setAlignment(Pos.CENTER);
		cardContainer.setSpacing(90);
		cardContainer.setTranslateY(-80);
		parentContainer.getChildren().addAll(titleContainer, cardContainer);

		Scene scene = new Scene(parentContainer, Main.viewPortWidth, Main.viewPortHeight);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
