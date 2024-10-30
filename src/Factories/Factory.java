package Factories;

import java.util.Optional;

import Entities.DatabaseEntities.Item;
import Entities.DatabaseEntities.User;
import Entities.ModifiedEntities.FetchedCart;
import Handlers.CartHandler;
import Handlers.CredentialHandler;
import Main.Main;
import View.CustomerCartPage;
import View.CustomerDashboardPage;
import View.ItemDetailPage;
import View.LoginPage;
import View.RegisterPage;
import View.SelectRolePage;
import View.StaffAddItemPage;
import View.StaffManageQueuePage;
import View.TermsAndConditionPage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Factory {

	private static int formWidth = 500;

	public static Label createLabel(String text, int size) {
		Label newLabel = new Label(text);
		newLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, size));
		newLabel.setTextFill(Color.GREY);
		newLabel.setTranslateY(6);
		return newLabel;
	}

	public static VBox createRegisterForm(Stage primaryStage) {
		VBox registerWindow = new VBox();
		registerWindow.setSpacing(10);
		registerWindow.setAlignment(Pos.CENTER_LEFT);
		registerWindow
				.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(-20))));
		registerWindow.setMaxWidth(350);
		registerWindow.setMinHeight(200);

		Label titleLabel = new Label("Register");
		titleLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));

		Rectangle divider = new Rectangle(formWidth / 2, 2);

		TextField emailField = new TextField();

		PasswordField passField = new PasswordField();

		PasswordField confirmPassField = new PasswordField();

		DatePicker dobField = new DatePicker();

		RadioButton maleRadioButton = new RadioButton("Male");
		RadioButton femaleRadioButton = new RadioButton("Female");
		RadioButton otherRadioButton = new RadioButton("Toyota Agya");

		ToggleGroup genderToggleGroup = new ToggleGroup();
		HBox radioGroup = new HBox();
		radioGroup.setSpacing(10);
		maleRadioButton.setToggleGroup(genderToggleGroup);
		femaleRadioButton.setToggleGroup(genderToggleGroup);
		otherRadioButton.setToggleGroup(genderToggleGroup);
		radioGroup.getChildren().setAll(maleRadioButton, femaleRadioButton);

		HBox tncBox = new HBox();
		CheckBox termsCheckbox = new CheckBox("I accept the");
		Hyperlink tncLink = new Hyperlink("Terms and Conditions");
		tncLink.setOnAction(e -> TermsAndConditionPage.showTermsConditions());
		tncBox.setAlignment(Pos.BASELINE_LEFT);
		tncBox.getChildren().addAll(termsCheckbox, tncLink);

		HBox linkContainer = new HBox();
		Label signInLabel = new Label("Already have an account? Sign in ");
		signInLabel.setTextFill(Color.GRAY);
		Hyperlink registerLink = new Hyperlink("Here!");
		registerLink.setTranslateX(-3);
		registerLink.setOnAction(e -> LoginPage.initLoginPage(primaryStage));
		linkContainer.getChildren().addAll(signInLabel, registerLink);
		linkContainer.setAlignment(Pos.CENTER);

		Button registerButton = createButton("Register");
		registerButton.setMaxWidth(formWidth);

		registerButton.setOnAction(e -> {

			CredentialHandler.handleRegister(primaryStage, emailField.getText(), passField.getText(),
					confirmPassField.getText(), dobField.getValue(), genderToggleGroup.getSelectedToggle(),
					termsCheckbox.isSelected());
		});

		registerWindow.getChildren().addAll(titleLabel, divider, createLabel("Email", 14), emailField,
				createLabel("Password", 14), passField, createLabel("Confirm Password", 14), confirmPassField,
				createLabel("Date of Birth", 14), dobField, createLabel("Gender", 14), radioGroup, tncBox,
				registerButton, linkContainer);

		registerWindow.setTranslateY(-60);

		registerWindow.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				CredentialHandler.handleRegister(primaryStage, emailField.getText(), passField.getText(),
						confirmPassField.getText(), dobField.getValue(), genderToggleGroup.getSelectedToggle(),
						termsCheckbox.isSelected());
			}
		});

		return registerWindow;
	}

	public static VBox createLoginForm(Stage primaryStage) {

		VBox loginWindow = new VBox();
		loginWindow.setSpacing(10);
		loginWindow.setAlignment(Pos.CENTER_LEFT);
		loginWindow
				.setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(10), new Insets(-20))));
		loginWindow.setMaxWidth(350);
		loginWindow.setMinHeight(200);

		Label titleLabel = new Label("Login");
		titleLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));

		Rectangle divider = new Rectangle(formWidth / 2, 2);

		TextField emailField = new TextField();
		emailField.setMinHeight(30);
		PasswordField passField = new PasswordField();
		passField.setMinHeight(30);

		HBox linkContainer = new HBox();
		Label registerLabel = new Label("Are you new? Register ");
		registerLabel.setTextFill(Color.GRAY);

		Hyperlink registerLink = new Hyperlink("Here!");
		registerLink.setTranslateX(-3);
		registerLink.setOnAction(e -> RegisterPage.initRegisterPage(primaryStage));
		linkContainer.getChildren().addAll(registerLabel, registerLink);
		linkContainer.setAlignment(Pos.CENTER);

		Button loginButton = createButton("Login");
		loginButton.setMaxWidth(formWidth);

		loginButton.setOnAction(e -> {
			CredentialHandler.handleLogin(primaryStage, emailField.getText(), passField.getText());
		});

		loginWindow.getChildren().addAll(titleLabel, divider, createLabel("Email", 14), emailField,
				createLabel("Password", 14), passField, loginButton, linkContainer);

		loginWindow.setTranslateY(-60);

		loginWindow.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				CredentialHandler.handleLogin(primaryStage, emailField.getText(), passField.getText());
//				CustomerDashboardPage.initCustomerDashboard(primaryStage, "", "Search Items in GoGoQuery Store");
			}
		});

		return loginWindow;
	}

	public static VBox createLogo() {
		Label title = new Label("Go Go");
		Label titleAccent = new Label("Query");
		title.setStyle("-fx-text-fill: white;-fx-font-size:85px; -fx-font-weight:bold; -fx-font-style: italic;");
		titleAccent.setStyle("-fx-text-fill: " + Main.themeOrange
				+ ";-fx-font-size:120px; -fx-font-weight:bold; -fx-font-style: italic;");

		VBox titleContainer = new VBox();
		titleContainer.setAlignment(Pos.CENTER);
		titleContainer.getChildren().addAll(title, titleAccent);

		title.setTranslateY(90);
		title.setTranslateX(-80);

		titleContainer.setTranslateY(-80);
		return titleContainer;
	}

	public static VBox createRoleSelectionCard(Stage primaryStage, String roleTitle, String roleDesc, User newUser) {
		VBox roleCard = new VBox();
		roleCard.setAlignment(Pos.CENTER);
		roleCard.setMaxHeight(400);
		roleCard.setMinWidth(600);
		roleCard.setStyle("-fx-background-color: #ffffff; -fx-border-color: #FFFFFF; -fx-border-width: 5px; ");
		roleCard.setOnMouseEntered(e -> {
			roleCard.setStyle("-fx-border-color: #fcaf42; -fx-background-color: #ffffff; -fx-border-width: 5px");
		});
		roleCard.setOnMouseExited(e -> {
			roleCard.setStyle("-fx-background-color: #ffffff; -fx-border-color: #FFFFFF; -fx-border-width: 5px");
		});

		int pfpCircleRadius = 68;
		int yOffset = 10;

		Circle pfpCircle = new Circle(pfpCircleRadius);

		if (roleTitle.equalsIgnoreCase("Manager")) {
			pfpCircle.setFill(Color.web("#415261"));
		} else {
			pfpCircle.setFill(Color.web("#7A425C"));
		}

		pfpCircle.setStroke(Color.LIGHTGRAY);
		pfpCircle.setStrokeWidth(4);

		Text pfpContent = new Text(String.valueOf(Character.toLowerCase(roleTitle.charAt(0))));
		pfpContent.setFont(Font.font("Arial", FontWeight.NORMAL, 47));
		pfpContent.setFill(Color.WHITE);
		pfpContent.setTranslateY(-5);

		StackPane pfp = new StackPane();
		pfp.setTranslateY(-50 + yOffset);

		Circle activeStatus = new Circle(13, Color.LIME);
		activeStatus.setTranslateX(pfpCircleRadius / 1.5);
		activeStatus.setTranslateY(pfpCircleRadius / 1.5);

		Label roleTitleLabel = new Label(roleTitle);
		roleTitleLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 26));
		roleTitleLabel.setTextFill(Color.GREY);
		roleTitleLabel.setTranslateY(-34 + yOffset);

		Label roleDescLabel = new Label(roleDesc);
		roleDescLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 15));
		roleDescLabel.setTextFill(Color.GREY);
		roleDescLabel.setTranslateY(-12 + yOffset);

		pfp.getChildren().addAll(pfpCircle, pfpContent, activeStatus);

		Rectangle divider = new Rectangle(400, 2);
		divider.setFill(Color.LIGHTGRAY);
		divider.setTranslateY(-25 + yOffset);

		Button selectRoleButton = createButton("Register as " + roleTitle);
		selectRoleButton.setMaxWidth(400);
		selectRoleButton.setMinHeight(45);

		selectRoleButton.setOnAction(e -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Register Confirmation");
			alert.setHeaderText("Are you sure you want to register as " + roleTitle + "?");
			alert.setContentText("Please confirm your choice.");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.CANCEL) {
				SelectRolePage.initSelectRolePage(primaryStage, newUser);
			} else {
				CredentialHandler.registerUser(newUser, roleTitle);
				Alert registerAlert = new Alert(AlertType.INFORMATION);
				registerAlert.setTitle("Register Information");
				registerAlert.setHeaderText("Register success!");
				registerAlert.setContentText("Please log in with your newly created account.");
				registerAlert.showAndWait();
				LoginPage.initLoginPage(primaryStage);
			}
		});
		selectRoleButton.setTranslateY(10 + yOffset);
		roleCard.getChildren().addAll(pfp, roleTitleLabel, divider, roleDescLabel, selectRoleButton);
		return roleCard;
	}

	public static VBox createNavbar(Stage primaryStage, String searchBarText) {

		LinearGradient navbarGradient = new LinearGradient(0, 0, 1, 0, true, javafx.scene.paint.CycleMethod.REFLECT,
				new Stop(1, Color.web("#3D3F56")), new Stop(0, Color.web("#33333B")));
		Background navbarBg = new Background(new BackgroundFill(navbarGradient, CornerRadii.EMPTY, Insets.EMPTY));

		VBox navbarContainer = new VBox();
		navbarContainer.setAlignment(Pos.BOTTOM_CENTER);
		navbarContainer.setBackground(Main.defaultBg);
		navbarContainer.setMinHeight(180);
		navbarContainer.setMinWidth(Main.viewPortWidth);

		int navbarContentSpacing = 30;
		int navbarHeight = 110;
		HBox navbar = new HBox();
		navbar.setAlignment(Pos.CENTER_LEFT);
		navbar.setSpacing(80);
//		navbar.setBackground(Main.defaultBg);
		navbar.setMaxWidth(Main.viewPortWidth - 400);
		navbar.setMinHeight(navbarHeight);
		navbar.setStyle("-fx-background-radius : 60; -fx-background-color:" + Main.navbarGrey + "");
//		navbar.setBackground(navbarBg);

		TextField searchBar = new TextField(searchBarText);
		double searchBarHeight = 40;
		searchBar.setMinWidth(Main.viewPortWidth / 2.3);
		searchBar.setMinHeight(searchBarHeight);
		searchBar.setStyle(
				"-fx-background-color : #545877; -fx-text-fill : #F3F3F3; -fx-font-weight : bold; -fx-font-size : 14px");

		Button searchButton = createNavbarButton("Search");
		searchButton.setOnAction(e -> {
			CustomerDashboardPage.initCustomerDashboard(primaryStage, searchBar.getText(), searchBar.getText());
		});
		searchButton.setStyle(
				"-fx-background-color: #7278B2; -fx-text-fill: #F3F3F3; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family : helvetica");

		searchButton.setOnMouseEntered(e -> {
			searchButton.setStyle(
					"-fx-background-color: #646A9B; -fx-text-fill: #F3F3F3; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family : helvetica");
		});
		searchButton.setOnMouseExited(e -> {
			searchButton.setStyle(
					"-fx-background-color: #7278B2; -fx-text-fill: #F3F3F3; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family : helvetica");
		});

		searchButton.setMinHeight(searchBarHeight - 2);
		searchButton.setMinWidth(100);
		searchButton.setTranslateX(-1 * navbarContentSpacing - 101);
		searchButton.setTranslateY(-1);

		navbarContainer.setOnKeyPressed(event -> {
			if (event.getCode() == KeyCode.ENTER) {
				CustomerDashboardPage.initCustomerDashboard(primaryStage, searchBar.getText(), searchBar.getText());
			}
		});

		VBox navbarLogo = createNavbarLogo();
		navbarLogo.setOnMouseClicked(e -> {
			CustomerDashboardPage.initCustomerDashboard(primaryStage, "", "Search Items in GoGoQuery Store");
		});

		HBox leftNavbarContents = new HBox();
		leftNavbarContents.setSpacing(navbarContentSpacing);
		leftNavbarContents.setAlignment(Pos.CENTER_LEFT);
		leftNavbarContents.getChildren().addAll(navbarLogo, searchBar, searchButton);
		leftNavbarContents.setTranslateX(50);

		HBox rightNavbarContents = new HBox();
		rightNavbarContents.setAlignment(Pos.CENTER_LEFT);
		rightNavbarContents.setTranslateX(-80);
		rightNavbarContents.setSpacing(navbarContentSpacing / 1.2);

		Rectangle divider = new Rectangle(2, 0.7 * navbarHeight);
		divider.setFill(Color.web("#545877"));

		Button cartButton = createNavbarButton("My Cart");
		cartButton.setMinHeight(searchBarHeight);
		cartButton.setOnAction(e -> {
			CustomerCartPage.initCustomerCart(primaryStage, "", "Search Items in GoGoQuery Store");
		});

		Button logoutButton = createButton("Log Out");
		logoutButton.setMinHeight(searchBarHeight);
		logoutButton.setStyle(
				"-fx-background-color: #ff2121; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

		logoutButton.setOnMouseEntered(e -> {
			logoutButton.setStyle(
					"-fx-background-color: #c91a1a; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
		});
		logoutButton.setOnMouseExited(e -> {
			logoutButton.setStyle(
					"-fx-background-color: #ff2121; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
		});

		logoutButton.setOnAction(e -> {
			LoginPage.initLoginPage(primaryStage);
		});

		rightNavbarContents.getChildren().addAll(divider, cartButton, logoutButton);

		navbar.getChildren().addAll(leftNavbarContents, rightNavbarContents);
		navbarContainer.getChildren().addAll(navbar);

		return navbarContainer;
	}

	private static Button createNavbarButton(String buttonText) {
		Button newButton = new Button(buttonText);
		newButton.setStyle(
				"-fx-background-color: transparent; -fx-text-fill: #F3F3F3; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family : helvetica");

		newButton.setOnMouseEntered(e -> {
			newButton.setStyle(
					"-fx-background-color: transparent; -fx-text-fill: #d6d6d6; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family : helvetica");
		});
		newButton.setOnMouseExited(e -> {
			newButton.setStyle(
					"-fx-background-color: transparent; -fx-text-fill: #F3F3F3; -fx-font-size: 16px; -fx-font-weight: bold; -fx-font-family : helvetica");
		});
		return newButton;
	}

	public static Button createButton(String buttonText) {
		Button newButton = new Button(buttonText);

		newButton.setStyle("-fx-background-color: " + Main.themeOrange
				+ "; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");

		newButton.setOnMouseEntered(e -> {
			newButton.setStyle(
					"-fx-background-color: #C67025; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
		});
		newButton.setOnMouseExited(e -> {
			newButton.setStyle("-fx-background-color: " + Main.themeOrange
					+ "; -fx-text-fill: white; -fx-font-size: 16px; -fx-font-weight: bold;");
		});
		return newButton;
	}

	public static VBox createNavbarLogo() {
		Label title = new Label("Go Go");
		Label titleAccent = new Label("Query");
		title.setStyle("-fx-text-fill: white	;-fx-font-size:30px; -fx-font-weight:bold; -fx-font-style: italic;");
		titleAccent.setStyle("-fx-text-fill: " + Main.themeOrange
				+ ";-fx-font-size:45px; -fx-font-weight:bold; -fx-font-style: italic;");

		VBox titleContainer = new VBox();
		titleContainer.setAlignment(Pos.CENTER);
		titleContainer.getChildren().addAll(title, titleAccent);
		titleContainer.setSpacing(-35);

//		title.setTranslateY(90);
		title.setTranslateX(-25);

//		titleContainer.setTranslateY(-80);

		return titleContainer;
	}

	public static Alert createError(String title, String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		return alert;
	}

	public static VBox createDashboardItemBox(Stage primaryStage, Item item) {
		VBox itemBox = new VBox();
		itemBox.setAlignment(Pos.CENTER);
		itemBox.setBackground(new Background(new BackgroundFill(Color.web(Main.navbarGrey), null, null)));
		itemBox.setMaxWidth(700);
		itemBox.setMinHeight(170);
		itemBox.setOnMouseClicked(e -> {
			ItemDetailPage.initCustomerItemDetailPage(primaryStage, item);
		});

		itemBox.setOnMouseEntered(e -> {
			itemBox.setBackground(new Background(new BackgroundFill(Color.web("#252633"), null, null)));
		});
		itemBox.setOnMouseExited(e -> {
			itemBox.setBackground(new Background(new BackgroundFill(Color.web(Main.navbarGrey), null, null)));
		});

		HBox content = new HBox();
		content.setSpacing(20);
		content.setAlignment(Pos.CENTER);

		Rectangle picture = new Rectangle(130, 130, Color.GRAY);
		picture.setArcHeight(10);
		picture.setArcWidth(10);

		VBox itemDetail = new VBox();
		int itemDetailHeight = 120;
		itemDetail.setMaxHeight(itemDetailHeight);
		itemDetail.setMinWidth(500);
		itemDetail.setMaxWidth(500);

		VBox titleContainer = new VBox();
		titleContainer.setMinHeight(itemDetailHeight * 0.5);
//		titleContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		titleContainer.setAlignment(Pos.CENTER_LEFT);

		Label itemTitle = new Label(item.getItemName());
		itemTitle.setWrapText(true);
		itemTitle.setPrefWidth(450);
		itemTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 23));
		itemTitle.setTextFill(Color.web(Main.themeWhite));

		titleContainer.getChildren().addAll(itemTitle);

		HBox priceStockContainer = new HBox();
		priceStockContainer.setAlignment(Pos.CENTER_LEFT);
		priceStockContainer.setSpacing(10);

		Label itemPriceLabel = new Label("$" + item.getItemPrice());
		itemPriceLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		itemPriceLabel.setTextFill(Color.web(Main.themeOrange));

		Button stockLeftLabel = new Button(item.getItemStock() + " Left");
		stockLeftLabel.setMinHeight(20);
		stockLeftLabel.setMinWidth(50);
		stockLeftLabel.setStyle(
				"-fx-background-color : #ff2121; -fx-font-family : Helvetica; -fx-font-weight : bold; -fx-font-size : 15px; -fx-text-fill : white");

		priceStockContainer.getChildren().addAll(itemPriceLabel, stockLeftLabel);

		itemDetail.getChildren().addAll(titleContainer, priceStockContainer);

		content.getChildren().addAll(picture, itemDetail);
		itemBox.getChildren().addAll(content);
		return itemBox;
	}

	public static Label createSrchMsgLbl(String message, String color) {
		Label resultMsgLabel = new Label(message);

		resultMsgLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

		resultMsgLabel.setTextFill(Color.web(color));
		return resultMsgLabel;
	}

	public static Spinner createSpinner(int minVal, int maxVal, int initVal) {
		Spinner<Integer> qtySpinner = new Spinner<>();
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minVal, maxVal,
				initVal);
		qtySpinner.setValueFactory(valueFactory);
		qtySpinner.setPrefWidth(100);
		qtySpinner.setPrefHeight(26);

		qtySpinner.setStyle(
				"-fx-background-color : #545877; -fx-text-fill : #F3F3F3; -fx-font-weight : bold; -fx-font-size : 14px");

		return qtySpinner;
	}

	public static HBox createCartItemBox(Stage primaryStage, FetchedCart cartRow, ScrollPane cartPageScrollPane) {
		HBox itemBox = new HBox();
		itemBox.setAlignment(Pos.CENTER_LEFT);
		itemBox.setBackground(new Background(new BackgroundFill(Color.web(Main.navbarGrey), null, null)));
		itemBox.setMaxWidth(710);
		itemBox.setMinHeight(170);
		itemBox.setOnMouseClicked(e -> {
			ItemDetailPage.initCustomerItemDetailPage(primaryStage, cartRow.getCartItem());
		});

		itemBox.setOnMouseEntered(e -> {
			itemBox.setBackground(new Background(new BackgroundFill(Color.web("#252633"), null, null)));
		});
		itemBox.setOnMouseExited(e -> {
			itemBox.setBackground(new Background(new BackgroundFill(Color.web(Main.navbarGrey), null, null)));
		});

		HBox content = new HBox();
		content.setSpacing(20);
		content.setAlignment(Pos.CENTER_LEFT);
//		content.setBackground(Main.defaultBg2);
		content.setTranslateX(20);

		Rectangle picture = new Rectangle(130, 130, Color.GRAY);
		picture.setArcHeight(10);
		picture.setArcWidth(10);

		VBox itemDetail = new VBox();
		int itemDetailHeight = 120;
		itemDetail.setMaxHeight(itemDetailHeight);
		itemDetail.setMinWidth(520);
		itemDetail.setMaxWidth(520);

		VBox titleContainer = new VBox();
		titleContainer.setMinHeight(itemDetailHeight * 0.5);
//		titleContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		titleContainer.setAlignment(Pos.CENTER_LEFT);

		Label itemTitle = new Label(cartRow.getCartItem().getItemName());
		itemTitle.setWrapText(true);
		itemTitle.setPrefWidth(450);
		itemTitle.setFont(Font.font("Helvetica", FontWeight.BOLD, 23));
		itemTitle.setTextFill(Color.web(Main.themeWhite));

		titleContainer.getChildren().addAll(itemTitle);

		HBox priceStockContainer = new HBox();
		priceStockContainer.setAlignment(Pos.CENTER_LEFT);
		priceStockContainer.setSpacing(10);

		Label itemPriceLabel = new Label("$" + cartRow.getCartItem().getItemPrice());
		itemPriceLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
		itemPriceLabel.setTextFill(Color.web(Main.themeOrange));
		itemPriceLabel.setMinWidth(300);

		Button removeButton = new Button(" x ");
//		removeButton.setTranslateY(-84);
//		removeButton.setTranslateX(120);
		removeButton.setMinHeight(20);
		removeButton.setMinWidth(50);
		removeButton.setTranslateX(-20);
		removeButton.setTranslateY(20);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Item Removal Confirmation");
		alert.setHeaderText("Do you want to remove this item from your cart?");
		alert.setContentText("Please confirm your choice.");

		removeButton.setOnAction(e -> {

			Optional<ButtonType> result = alert.showAndWait();
			if (result.isPresent() && result.get() == ButtonType.OK) {
				CartHandler.removeItemFromCart(cartRow);
				CustomerCartPage.initCustomerCart(primaryStage, "", "Search Items in GoGoQuery Store");
			}
		});
		removeButton.setStyle(
				"-fx-background-color : #ff2121; -fx-font-family : Helvetica; -fx-font-weight : bold; -fx-font-size : 15px; -fx-text-fill : white");
		removeButton.setOnMouseEntered(e -> {
			removeButton.setStyle(
					"-fx-background-color : #c91a1a; -fx-font-family : Helvetica; -fx-font-weight : bold; -fx-font-size : 15px; -fx-text-fill : white");
		});

		removeButton.setOnMouseExited(e -> {
			removeButton.setStyle(
					"-fx-background-color : #ff2121; -fx-font-family : Helvetica; -fx-font-weight : bold; -fx-font-size : 15px; -fx-text-fill : white");
		});

		HBox stockLabelContainer = new HBox();
		stockLabelContainer.setAlignment(Pos.CENTER);

		Label stockLabel = new Label("Quantity ");
		stockLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 19));
		stockLabel.setTextFill(Color.web("#717489"));

		Label stockQtyLabel = new Label(Integer.toString(cartRow.getCartItem().getItemStock()));
		stockQtyLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 19));
		stockQtyLabel.setTextFill(Color.web(Main.themeOrange));

		stockLabelContainer.getChildren().addAll(stockLabel, stockQtyLabel);

		Spinner<Integer> qtySpinner = createSpinner(0, cartRow.getCartItem().getItemStock(), cartRow.getQuantity());

		qtySpinner.setPrefWidth(100);
		qtySpinner.setTranslateX(-20);
		qtySpinner.setTranslateY(84);

		qtySpinner.setOnMouseClicked(e -> {

			if (qtySpinner.getValue() < 1) {
				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					CartHandler.removeItemFromCart(cartRow);
					CustomerCartPage.initCustomerCart(primaryStage, "", "Search Items in GoGoQuery Store");
				} else {
					qtySpinner.getValueFactory().setValue(1);
				}
			} else {
				CartHandler.updateItemQuantity(cartRow, qtySpinner.getValue());
				CartHandler.updateSubtotal();
				CustomerCartPage.cartTotalPriceLabel.setText(String.format("$%.2f", CustomerCartPage.cartSubtotal));
			}

		});

		VBox rightContent = new VBox();
		rightContent.setAlignment(Pos.TOP_RIGHT);
		rightContent.setMinHeight(170);
		rightContent.setMinWidth(80);
		rightContent.getChildren().addAll(removeButton, qtySpinner);

		priceStockContainer.getChildren().addAll(itemPriceLabel);

		itemDetail.getChildren().addAll(titleContainer, priceStockContainer);

		content.getChildren().addAll(picture, itemDetail);
		itemBox.getChildren().addAll(content, rightContent);
		return itemBox;
	}

	public static MenuBar createAdminMenuBar(Stage staffStage) {
		MenuBar menuBar = new MenuBar();

		Menu itemMenu = new Menu("Menu");

		MenuItem addItem = new MenuItem("Add Item");
		MenuItem logout = new MenuItem("Log Out");
		addItem.setOnAction(StaffAddItemPage.initAddItemPage(staffStage));
		MenuItem manageQueue = new MenuItem("Manage Queue");
		manageQueue.setOnAction(e -> {
			StaffManageQueuePage.initManageQueuePage(staffStage);
		});

		itemMenu.getItems().addAll(addItem, manageQueue, logout);

		menuBar.getMenus().add(itemMenu);

		return menuBar;
	}

}
