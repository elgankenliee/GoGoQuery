package View;

import Entities.DatabaseEntities.Item;
import Factories.Factory;
import Handlers.CartHandler;
import Main.Main;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ItemDetailPage {

	public static void initCustomerItemDetailPage(Stage primaryStage, Item item) {
		VBox screen = new VBox();
		screen.setBackground(Main.defaultBg);
		screen.setAlignment(Pos.TOP_CENTER);
		screen.setSpacing(80);

		HBox content = new HBox();
		int contentSpacing = 0;
		content.setAlignment(Pos.TOP_CENTER);
		content.setSpacing(contentSpacing);
//		content.setBackground(new Background(new BackgroundFill(Color.PINK, null, null)));
		content.setMaxWidth(Main.contentWidth);
		content.setMinHeight(Main.viewPortHeight - 300);

		VBox leftContent = new VBox();
		leftContent.setAlignment(Pos.TOP_CENTER);
		leftContent.setMinWidth((Main.contentWidth * 0.3));
		leftContent.setMaxWidth((Main.contentWidth * 0.3));
//		leftContent.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));

		Rectangle picture = new Rectangle(350, 350, Color.GRAY);
		picture.setArcHeight(30);
		picture.setArcWidth(30);

//		String filePath = "./messageImage_538549.jpg";
//		Image image = new Image("file:" + filePath);
//		ImageView imageView = new ImageView(image);
//		imageView.setFitWidth(350);
//		imageView.setFitHeight(350);
//		imageView.setStyle("-fx-background-radius : 20");

		VBox middleContent = new VBox();
		middleContent.setMinWidth((Main.contentWidth * 0.3));
//		middleContent.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		VBox topMiddleContent = new VBox();
		topMiddleContent.setMinHeight(230);
		topMiddleContent.setMaxHeight(230);
//		topMiddleContent.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

		VBox namePriceContainer = new VBox();

//		Label itemTitle = new Label(
//				"this is a long text that needs to be wordwrapped. This text is used only for testing purposes, please remove when application is done");
		Label itemTitle = new Label(item.getItemName());
		itemTitle.setWrapText(true);
		itemTitle.setPrefWidth(400);
		itemTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 28));
		itemTitle.setTextFill(Color.web(Main.themeWhite));

		Label itemPriceLabel = new Label("$" + item.getItemPrice());
		itemPriceLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 45));
		itemPriceLabel.setTextFill(Color.web(Main.themeOrange));

		HBox categoryContainer = new HBox();
		categoryContainer.getChildren().addAll(Factory.createSrchMsgLbl("Category : ", "#8a8da1"),
				Factory.createSrchMsgLbl(item.getItemCategory(), Main.themeOrange));

		namePriceContainer.getChildren().addAll(itemTitle, itemPriceLabel);
		topMiddleContent.getChildren().addAll(namePriceContainer, categoryContainer);

		VBox bottomMiddleContent = new VBox();
		bottomMiddleContent.setMinHeight(400);
		bottomMiddleContent.setSpacing(5);
//		bottomMiddleContent.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));

		Label detailLabel = new Label("Item Detail");
		detailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		detailLabel.setTextFill(Color.web(Main.themeOrange));

		Rectangle divider = new Rectangle(400, 1, Color.web(Main.themeOrange));

		Label itemDescLabel = new Label(item.getItemDesc());
		itemDescLabel.setWrapText(true);
		itemDescLabel.setPrefWidth(400);

		itemDescLabel.setFont(Font.font("Helvetica", FontWeight.NORMAL, 17));
		itemDescLabel.setTextFill(Color.web(Main.themeWhite));

		bottomMiddleContent.getChildren().addAll(detailLabel, divider, itemDescLabel);

		VBox rightContent = new VBox();
		rightContent.setAlignment(Pos.TOP_LEFT);
		rightContent.setMinWidth((Main.contentWidth * 0.3));
		rightContent.setMaxWidth((Main.contentWidth * 0.3));
		rightContent.setSpacing(20);
//		rightContent.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, null, null)));
//		content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

		int qtySelectorWidth = 250;
		VBox saleContainer = new VBox();
		saleContainer.setAlignment(Pos.CENTER_LEFT);
		saleContainer.setPadding(new Insets(0, 0, 0, 15));
		saleContainer.setMaxWidth(qtySelectorWidth);
		saleContainer.setMinWidth(287);
		saleContainer.setMinHeight(50);
		saleContainer.setStyle(
				"-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, #b968e8, #2f3269); -fx-background-radius :10");

		Label saleLabel = new Label("Best Seller!");
		saleLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		saleLabel.setTextFill(Color.WHITE);

		saleContainer.getChildren().addAll(saleLabel);

		VBox qtySelector = new VBox();
		qtySelector.setSpacing(20);
		qtySelector.setPadding(new Insets(15, 15, 15, 15));
//		qtySelector.setStyle("-fx-background-radius: 10px; -fx-background-color : #4d4e61");
		qtySelector.setMinHeight(200);
		qtySelector.setMaxWidth(qtySelectorWidth);

		qtySelector.setStyle("-fx-border-color: #71717c; " + "-fx-border-width: 3px; " + "-fx-border-radius: 10px; "
				+ "-fx-background-radius: 10px; " + "-fx-background-color: #373745;");

		qtySelector.setOnMouseEntered(e -> {
			qtySelector.setStyle("-fx-border-color: " + Main.themeOrange + "; " + "-fx-border-width: 3px; "
					+ "-fx-border-radius: 10px; " + "-fx-background-radius: 10px; " + "-fx-background-color: #373745;");
		});

		qtySelector.setOnMouseExited(e -> {
			qtySelector.setStyle("-fx-border-color: #71717c; " + "-fx-border-width: 3px; " + "-fx-border-radius: 10px; "
					+ "-fx-background-radius: 10px; " + "-fx-background-color: #373745;");
		});

		Label selectorLabel = new Label("Set item quantity");
		selectorLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
		selectorLabel.setTextFill(Color.web("#cecfde"));

		HBox spinnerStockContainer = new HBox();
		spinnerStockContainer.setSpacing(15);
		Spinner<Integer> qtySpinner = Factory.createSpinner(1, item.getItemStock(), 1);

		HBox labelContainer = new HBox();

		Label stockLabel = new Label("Stock : ");
		stockLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 19));
		stockLabel.setTextFill(Color.web(Main.themeWhite));

		Label stockQtyLabel = new Label(Integer.toString(item.getItemStock()));
		stockQtyLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 19));
		stockQtyLabel.setTextFill(Color.web(Main.themeOrange));

		Button addToCartButton = Factory.createButton("Add to Cart");
		addToCartButton.setMinWidth(qtySelectorWidth);
		addToCartButton.setTranslateY(23);
		addToCartButton.setOnAction(e -> {
			CartHandler.addToCart(item, qtySpinner.getValue());
		});

		labelContainer.getChildren().addAll(stockLabel, stockQtyLabel);

		spinnerStockContainer.getChildren().addAll(qtySpinner, labelContainer);

		qtySelector.getChildren().addAll(selectorLabel, spinnerStockContainer, addToCartButton);

		rightContent.getChildren().addAll(saleContainer, qtySelector);
		middleContent.getChildren().addAll(topMiddleContent, bottomMiddleContent);
		leftContent.getChildren().addAll(picture);
		content.getChildren().addAll(leftContent, middleContent, rightContent);
		screen.getChildren().addAll(Factory.createNavbar(primaryStage, "Search Items in GoGoQuery Store"), content);

		Scene scene = new Scene(screen, Main.viewPortWidth, Main.viewPortHeight);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
