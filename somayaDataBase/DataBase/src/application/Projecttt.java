package application;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Projecttt extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			LogInUserInterface();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void LogInUserInterface() {
		Stage primaryStage = new Stage();
		String imagePath = "logo.png";
		File file = new File(imagePath);
		Image logoImg = new Image(file.toURI().toString());
		ImageView logoView = new ImageView(logoImg);

		logoView.setFitHeight(100);
		logoView.setFitWidth(100);

		// Image mainImg = new Image("result (2).png");
//		ImageView imgVeiw = new ImageView(mainImg);
//		imgVeiw.setFitHeight(100);
//		imgVeiw.setFitWidth(100);
		VBox imgvbox = new VBox(15);
		// imgvbox.getChildren().add(imgVeiw);
		String userImagePath = "user.png";
		String passwordImagePath = "password.png";

		Image userImage = new Image(new File(userImagePath).toURI().toString());
		Image passwordImage = new Image(new File(passwordImagePath).toURI().toString());
		ImageView userImgVeiw = new ImageView(userImage);

		ImageView passwordImgVeiw = new ImageView(passwordImage);
		userImgVeiw.setFitHeight(35);
		userImgVeiw.setFitWidth(35);
		passwordImgVeiw.setFitHeight(30);
		passwordImgVeiw.setFitWidth(35);
		HBox userhbox = new HBox(10);
		userhbox.setAlignment(Pos.CENTER);
		HBox passwdHbox = new HBox(10);
		passwdHbox.setAlignment(Pos.CENTER);
		TextField userNameTF = new TextField();
		userNameTF.setPromptText(" Enter User Name");
		userNameTF.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight:bold; -fx-background-radius: 15px");
		PasswordField passwordNameTF = new PasswordField();
		passwordNameTF.setPromptText(" Enter Password");
		passwordNameTF.setStyle("-fx-font-family: 'Times New Roman'; -fx-font-weight:bold;-fx-background-radius: 15px");
		Button loginButton = new Button("  LOG IN");
		loginButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-text-fill:White; -fx-background-color:Green; -fx-background-radius: 15px");
		passwordNameTF.setMaxSize(200, 200);
		userNameTF.setMaxSize(200, 200);
		loginButton.setMaxSize(200, 200);
		VBox mainVbox = new VBox(15);
		Button cancelButton = new Button("Cancel");
		cancelButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-text-fill:White; -fx-background-color:red; -fx-background-radius: 15px");
		VBox cancelVbox = new VBox(15);
		cancelVbox.setAlignment(Pos.BASELINE_LEFT);
		cancelVbox.getChildren().add(cancelButton);
		imgvbox.setAlignment(Pos.CENTER);
		userhbox.getChildren().addAll(userImgVeiw, userNameTF);
		passwdHbox.getChildren().addAll(passwordImgVeiw, passwordNameTF);
		mainVbox.getChildren().addAll(imgvbox, userhbox, passwdHbox, loginButton, cancelButton);
		cancelButton.setMaxSize(200, 200);
		mainVbox.setAlignment(Pos.CENTER);
		HBox hbox = new HBox(15);
		hbox.setAlignment(Pos.CENTER);
		// logoVeiw,
		hbox.getChildren().addAll(mainVbox);
		hbox.setStyle(
				"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
		Scene scene = new Scene(hbox, 390, 350);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Login Form");
		loginButton.setOnAction(e -> {
			String userName = userNameTF.getText().trim();
			String paswword = passwordNameTF.getText().trim();
			if (userName.equals("root") && paswword.equals("1234EE")) {
				// Show the Entity Page
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection connection = DriverManager
							.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", userName, paswword);
					ConectGui(connection, primaryStage);
				} catch (Exception ex) {
					System.out.println(ex);
				}

			} else if (paswword.equals("1210712") && !userName.equals("root")) {
				// Falied Connection Wrong user name
				VBox warning = new VBox(10);
				VBox noteswarning = new VBox(2);

				Label label1 = new Label("Incorrect username");
				label1.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:black;-fx-font-size:25px;-fx-font-weight:bold");

				Label label2 = new Label("the username you enterd does'nt  ");
				Label label3 = new Label("appear to belong to an account");
				Label label4 = new Label("please check your username and try again");
				label2.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				label3.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				label4.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				warning.setStyle(
						"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");

				Button tryAgainbutton = new Button("Try Again");
				warning.getChildren().addAll(label1, noteswarning, tryAgainbutton);
				noteswarning.getChildren().addAll(label2, label3, label4);
				noteswarning.setAlignment(Pos.CENTER);
				warning.setAlignment(Pos.CENTER);
				Stage warriningstage = new Stage();
				Scene warriningscene = new Scene(warning, 260, 200);
				warriningstage.setScene(warriningscene);
				warriningstage.setTitle("Login Form");
				warriningstage.show();
				tryAgainbutton.setMaxSize(200, 200);
				cancelButton.setOnAction(E -> {
					primaryStage.close();

				});
				tryAgainbutton.setOnAction(event -> {
					warriningstage.close();

				});
				tryAgainbutton.setStyle(
						"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-text-fill:White; -fx-background-color:Green; -fx-background-radius: 15px");

			} else if (!paswword.equals("1210712") && userName.equals("root")) {
				// Falied Connection Wrong password
				VBox warning = new VBox(10);
				VBox noteswarning = new VBox(2);
				Label label1 = new Label("Incorrect Password");
				label1.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:black;-fx-font-size:25px;-fx-font-weight:bold");

				Label label2 = new Label("the password you enterd does'nt  ");
				Label label3 = new Label("appear to belong to an account");
				Label label4 = new Label("please check your password and try again");
				label2.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				label3.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				label4.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				warning.setStyle(
						"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");

				Button tryAgainbutton = new Button("Try Again");
				warning.getChildren().addAll(label1, noteswarning, tryAgainbutton);
				noteswarning.getChildren().addAll(label2, label3, label4);
				noteswarning.setAlignment(Pos.CENTER);

				warning.setAlignment(Pos.CENTER);
				Stage warriningstage = new Stage();
				Scene warriningscene = new Scene(warning, 260, 200);
				warriningstage.setScene(warriningscene);
				warriningstage.setTitle("Wrong Password");
				warriningstage.show();
				tryAgainbutton.setMaxSize(200, 200);
				tryAgainbutton.setOnAction(event -> {
					warriningstage.close();

				});
				tryAgainbutton.setStyle(
						"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-text-fill:White; -fx-background-color:Green; -fx-background-radius: 15px");

			} else {
				// Wrong informtion
				VBox warning = new VBox(10);
				VBox noteswarning = new VBox(2);
				Label label1 = new Label("Incorrect Information");
				label1.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:black;-fx-font-size:25px;-fx-font-weight:bold");

				Label label2 = new Label("the password and username you enterd does'nt  ");
				Label label3 = new Label("appear to belong to an account");
				Label label4 = new Label("please check your information and try again");
				label2.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				label3.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				label4.setStyle(
						"-fx-font-family: 'Times New Roman';-fx-text-fill:Gray;-fx-font-size:12px;-fx-font-weight:bold");
				warning.setStyle(
						"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");

				Button tryAgainbutton = new Button("Try Again");
				warning.getChildren().addAll(label1, noteswarning, tryAgainbutton);
				noteswarning.getChildren().addAll(label2, label3, label4);
				noteswarning.setAlignment(Pos.CENTER);

				warning.setAlignment(Pos.CENTER);
				Stage warriningstage = new Stage();
				Scene warriningscene = new Scene(warning, 260, 200);
				warriningstage.setScene(warriningscene);
				warriningstage.setTitle("Wrong Information");
				warriningstage.show();
				tryAgainbutton.setMaxSize(200, 200);
				tryAgainbutton.setOnAction(event -> {
					warriningstage.close();

				});
				tryAgainbutton.setStyle(
						"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-text-fill:White; -fx-background-color:Green; -fx-background-radius: 15px");

			}

		});
		primaryStage.show();

	}

	public void InsuranceButton(Connection connection, Stage hidestage) throws MalformedURLException {
		hidestage.hide();

		String styleOfLabel = "-fx-font-family:'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold;";
		String textFieldStyle = "-fx-font-family:'Times New Roman';-fx-background-color: #F0F0F0; -fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 12px;";
		Label insuranceIdLabel = new Label("Insurance ID:");
		insuranceIdLabel.setStyle(styleOfLabel);
		Label InsuranceCompnyLabel = new Label("Insurance Company:");
		InsuranceCompnyLabel.setStyle(styleOfLabel);
		Label StartLabel = new Label("Start Date:");
		StartLabel.setStyle(styleOfLabel);
		Label EndLabel = new Label("End Date:");
		EndLabel.setStyle(styleOfLabel);
		TextField insuranceTextField = new TextField();
		insuranceTextField.setPromptText("Enter Insurance ID");
		insuranceTextField.setStyle(textFieldStyle);
		TextField InsuranceCompanyTextField = new TextField();
		InsuranceCompanyTextField.setPromptText("Enter Insurance Company ");
		InsuranceCompanyTextField.setStyle(textFieldStyle);

		TextField StartDateTextField = new TextField();
		StartDateTextField.setPromptText("Enter Start Date");
		StartDateTextField.setStyle(textFieldStyle);

		TextField EndDateTextField = new TextField();
		EndDateTextField.setPromptText("Enter End Date");
		EndDateTextField.setStyle(textFieldStyle);

		VBox leftPane = new VBox(20);
		leftPane.getChildren().addAll(insuranceIdLabel, InsuranceCompnyLabel, StartDateTextField, EndLabel);
		leftPane.setAlignment(Pos.CENTER);
		VBox rightPane = new VBox(15);
		rightPane.getChildren().addAll(insuranceTextField, InsuranceCompanyTextField, StartDateTextField,
				EndDateTextField);
		rightPane.setAlignment(Pos.CENTER);
		HBox mainPane = new HBox(20);
		mainPane.getChildren().addAll(leftPane, rightPane);
		String addImagePath = "add.png";
		String deleteImagePath = "delete.jpg";
		String previousImagePath = "prev1.png";
		String fullscreenImagePath = "full-screen.png";

		ImageView addImageView = new ImageView(new Image(new File(addImagePath).toURI().toURL().toString()));
		ImageView deleteImageView = new ImageView(new Image(new File(deleteImagePath).toURI().toURL().toString()));
		ImageView previousImageView = new ImageView(new Image(new File(previousImagePath).toURI().toURL().toString()));
		ImageView fullscreenImageView = new ImageView(
				new Image(new File(fullscreenImagePath).toURI().toURL().toString()));

		addImageView.setFitWidth(20);
		addImageView.setFitHeight(20);
		deleteImageView.setFitWidth(20);
		deleteImageView.setFitHeight(20);
		fullscreenImageView.setFitWidth(20);
		fullscreenImageView.setFitHeight(20);
		previousImageView.setFitWidth(20);
		previousImageView.setFitHeight(20);
		String showImagePath = "show.png";
		ImageView showImageView = new ImageView(new Image(new File(showImagePath).toURI().toURL().toString()));
		showImageView.setFitWidth(20);
		showImageView.setFitHeight(20);
		Button UpdateButton = new Button("Update", previousImageView);
		Button ShowButton = new Button("Show", previousImageView);
		ShowButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		Button fullscreenButton = new Button(" Full Screen", fullscreenImageView);
		fullscreenButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		UpdateButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		Button addButton = new Button("Add", addImageView);
		addButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color:Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		Button deleteButton = new Button("Delete", deleteImageView);
		deleteButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		HBox buttonPane = new HBox(10);
		buttonPane.getChildren().addAll(ShowButton, UpdateButton, fullscreenButton, addButton, deleteButton);

		VBox scenePane = new VBox(20);
		buttonPane.setAlignment(Pos.CENTER);
		scenePane.setAlignment(Pos.CENTER);

		HBox hbox = new HBox(15);
		hbox.setStyle("-fx-border-color: green; -fx-border-width: 2px;-fx-background-Color:white");
		Scene scene = new Scene(hbox, 1200, 600);

		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		fullscreenButton.setOnAction(event -> {
			stage.setFullScreen(true);
		});
		TableColumn<Insurance, String> InsuranceIdCol = new TableColumn<Insurance, String>("Insurance Id");
		TableColumn<Insurance, String> InsuranceCompanyCol = new TableColumn<Insurance, String>("Insurance Company");
		TableColumn<Insurance, String> InsuranceStartDateCol = new TableColumn<Insurance, String>(
				"Insurance Start Date");
		TableColumn<Insurance, String> InsuranceEndDateCol = new TableColumn<Insurance, String>("Insurance End Date");
		InsuranceIdCol.setCellValueFactory(new PropertyValueFactory<Insurance, String>("InsuranceId"));
		InsuranceCompanyCol.setCellValueFactory(new PropertyValueFactory<Insurance, String>("InsuranceCompany"));
		InsuranceStartDateCol.setCellValueFactory(new PropertyValueFactory<Insurance, String>("PolicyStartDate"));
		InsuranceEndDateCol.setCellValueFactory(new PropertyValueFactory<Insurance, String>("PolicyEndDate"));
		// Data Set
		ObservableList<Insurance> patients = FXCollections.observableArrayList();
		// TableView
		TableView<Insurance> tv = new TableView<Insurance>();
		tv.getColumns().addAll(InsuranceIdCol, InsuranceCompanyCol, InsuranceStartDateCol, InsuranceEndDateCol);
		// Add TableView to scenePane
		scenePane.getChildren().addAll(tv, buttonPane);
		hbox.getChildren().addAll(scenePane, mainPane);
		ShowButton.setOnAction(e -> {
			try {
				ObservableList<Insurance> Oblist = showPreviouslyExistDataInsurance(connection);
				tv.setItems(Oblist);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		deleteButton.setOnAction(e -> {
			Insurance selectedInsurance = tv.getSelectionModel().getSelectedItem();
			if (selectedInsurance != null) {
				try {
					tv.getItems().remove(selectedInsurance);
					deleteInsuranceFromDatabase(connection, selectedInsurance);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});
		UpdateButton.setOnAction(e1 -> {

			try {
				UpdateButtonInsurance(connection, tv);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

		});
		addButton.setOnAction(e -> {
			try {
				String insuranceId = insuranceTextField.getText();
				String insuranceCompany = InsuranceCompanyTextField.getText();
				String startDate = StartDateTextField.getText();
				String EndDate = EndDateTextField.getText();
				Insurance insurance = new Insurance(insuranceId, insuranceCompany, startDate, EndDate);

				if (!isExistingInsurance(insurance, connection)) {
					insertInsuranceInDataBase(connection, insurance);
					patients.add(insurance);
					tv.setItems(patients);
				} else {
					handleDuplicateId();
				}
			} catch (NumberFormatException e1) {
				handleNumberFormateExciption();
				System.out.println(e1);
			} catch (DateTimeParseException e3) {
				System.out.println(e3);
				handleInValidDateFormateExciption();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}

	public void PatientButton(Connection connection, Stage hidestage) throws SQLException, MalformedURLException {
		hidestage.hide();
		String styleOfLabel = "-fx-font-family:'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold;";
		String textFieldStyle = "-fx-font-family:'Times New Roman';-fx-background-color: #F0F0F0; -fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 12px;";
		Label patientIdLabel = new Label("Patient ID:");
		patientIdLabel.setStyle(styleOfLabel);
		Label firstNameLabel = new Label("First Name:");
		firstNameLabel.setStyle(styleOfLabel);
		Label middleNameLabel = new Label("Middle Name:");
		middleNameLabel.setStyle(styleOfLabel);
		Label lastNameLabel = new Label("Last Name:");
		lastNameLabel.setStyle(styleOfLabel);
		Label genderLabel = new Label("Gender:");
		genderLabel.setStyle(styleOfLabel);
		Label ageLabel = new Label("Age:");
		ageLabel.setStyle(styleOfLabel);
		Label dobLabel = new Label("Date of Birth:");
		dobLabel.setStyle(styleOfLabel);
		Label addressLabel = new Label("Address:");
		addressLabel.setStyle(styleOfLabel);
		Label phoneLabel = new Label("Phone Number:");
		phoneLabel.setStyle(styleOfLabel);
		Label medicalHistoryLabel = new Label("Medical History:");
		medicalHistoryLabel.setStyle(styleOfLabel);
		Label insuranceIdLabel = new Label("Insurance ID:");
		insuranceIdLabel.setStyle(styleOfLabel);
		TextField patientIdTextField = new TextField();
		patientIdTextField.setPromptText("Enter Patient ID");
		patientIdTextField.setStyle(textFieldStyle);
		TextField firstNameTextField = new TextField();
		firstNameTextField.setPromptText("Enter First Name");
		firstNameTextField.setStyle(textFieldStyle);
		TextField middleNameTextField = new TextField();
		middleNameTextField.setPromptText("Enter Middle Name");
		middleNameTextField.setStyle(textFieldStyle);
		TextField lastNameTextField = new TextField();
		lastNameTextField.setPromptText("Enter Last Name");
		lastNameTextField.setStyle(textFieldStyle);
		TextField genderTextField = new TextField();
		genderTextField.setPromptText("Enter Gender");
		genderTextField.setStyle(textFieldStyle);
		TextField ageTextField = new TextField();
		ageTextField.setPromptText("Enter Age");
		ageTextField.setStyle(textFieldStyle);
		TextField dobTextField = new TextField();
		dobTextField.setPromptText("Enter Date of Birth");
		dobTextField.setStyle(textFieldStyle);
		TextField addressTextField = new TextField();
		addressTextField.setPromptText("Enter Address");
		addressTextField.setStyle(textFieldStyle);
		TextField phoneTextField = new TextField();
		phoneTextField.setPromptText("Enter Phone Number");
		phoneTextField.setStyle(textFieldStyle);
		TextField medicalHistoryTextField = new TextField();
		medicalHistoryTextField.setPromptText("Enter Medical History");
		medicalHistoryTextField.setStyle(textFieldStyle);
		TextField insuranceIdTextField = new TextField();
		insuranceIdTextField.setPromptText("Enter Insurance ID");
		insuranceIdTextField.setStyle(textFieldStyle);

		VBox leftPane = new VBox(20);
		leftPane.getChildren().addAll(patientIdLabel, firstNameLabel, middleNameLabel, lastNameLabel, genderLabel,
				ageLabel, dobLabel, addressLabel, phoneLabel, medicalHistoryLabel, insuranceIdLabel);
		leftPane.setAlignment(Pos.CENTER);
		VBox rightPane = new VBox(15);
		rightPane.getChildren().addAll(patientIdTextField, firstNameTextField, middleNameTextField, lastNameTextField,
				genderTextField, ageTextField, dobTextField, addressTextField, phoneTextField, medicalHistoryTextField,
				insuranceIdTextField);
		rightPane.setAlignment(Pos.CENTER);
		HBox mainPane = new HBox(20);
		mainPane.getChildren().addAll(leftPane, rightPane);

		String addImagePath = "add.png";
		String deleteImagePath = "delete.jpg";
		String previousImagePath = "prev1.png";
		String fullscreenImagePath = "full-screen.png";

		ImageView addImageView = new ImageView(new Image(new File(addImagePath).toURI().toURL().toString()));
		ImageView deleteImageView = new ImageView(new Image(new File(deleteImagePath).toURI().toURL().toString()));
		ImageView previousImageView = new ImageView(new Image(new File(previousImagePath).toURI().toURL().toString()));
		ImageView fullscreenImageView = new ImageView(
				new Image(new File(fullscreenImagePath).toURI().toURL().toString()));

		addImageView.setFitWidth(20);
		addImageView.setFitHeight(20);
		deleteImageView.setFitWidth(20);
		deleteImageView.setFitHeight(20);
		fullscreenImageView.setFitWidth(20);
		fullscreenImageView.setFitHeight(20);
		previousImageView.setFitWidth(20);
		previousImageView.setFitHeight(20);
		String showImagePath = "show.png";
		ImageView showImageView = new ImageView(new Image(new File(showImagePath).toURI().toURL().toString()));
		showImageView.setFitWidth(20);
		showImageView.setFitHeight(20);
		Button UpdateButton = new Button("Update", previousImageView);
		Button ShowButton = new Button("Show", previousImageView);
		ShowButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		Button fullscreenButton = new Button(" Full Screen", fullscreenImageView);
		fullscreenButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		UpdateButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		Button addButton = new Button("Add", addImageView);
		addButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color:Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		Button deleteButton = new Button("Delete", deleteImageView);
		deleteButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		HBox buttonPane = new HBox(10);
		buttonPane.getChildren().addAll(ShowButton, UpdateButton, fullscreenButton, addButton, deleteButton);

		VBox scenePane = new VBox(20);
		buttonPane.setAlignment(Pos.CENTER);
		scenePane.setAlignment(Pos.CENTER);

		HBox hbox = new HBox(15);
		hbox.setStyle("-fx-border-color: green; -fx-border-width: 2px;-fx-background-Color:white");
		Scene scene = new Scene(hbox, 1200, 600);

		Stage stage = new Stage();
		fullscreenButton.setOnAction(event -> {
			stage.setFullScreen(true);
		});

		stage.setScene(scene);
		stage.show();
		TableColumn<Patient, String> firstNamCol = new TableColumn<Patient, String>("FirstName");
		TableColumn<Patient, String> MiddleNameCol = new TableColumn<Patient, String>("MiddleName");
		TableColumn<Patient, String> LastNameCol = new TableColumn<Patient, String>("LastName");
		TableColumn<Patient, String> AdderssCol = new TableColumn<Patient, String>("Address");
		TableColumn<Patient, String> GenderCol = new TableColumn<Patient, String>("Genger");
		TableColumn<Patient, String> AgeCol = new TableColumn<Patient, String>("Age");
		TableColumn<Patient, String> phoneNumberCol = new TableColumn<Patient, String>("PhoneNumber");
		TableColumn<Patient, String> InsuranceIdcol = new TableColumn<Patient, String>("InsuranceId");
		TableColumn<Patient, String> MedicalHistoryCol = new TableColumn<Patient, String>("MedicalHistory");
		TableColumn<Patient, String> PatientIdcol = new TableColumn<Patient, String>("PatientId");
		TableColumn<Patient, String> DateOfBirthcol = new TableColumn<Patient, String>("DateOfBirth");
		firstNamCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("firstName"));
		MiddleNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("middleName"));
		LastNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("lastName"));
		AdderssCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("address"));
		GenderCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("gender"));
		AgeCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("age"));
		phoneNumberCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("phoneNumber"));
		MedicalHistoryCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("medicalHistory"));
		InsuranceIdcol.setCellValueFactory(new PropertyValueFactory<Patient, String>("InsuranceId"));
		PatientIdcol.setCellValueFactory(new PropertyValueFactory<Patient, String>("patientId"));
		DateOfBirthcol.setCellValueFactory(new PropertyValueFactory<Patient, String>("dateOfBirth"));
		// Data Set
		ObservableList<Patient> patients = FXCollections.observableArrayList();
//		// TableView
		TableView<Patient> tv = new TableView<Patient>();
		tv.getColumns().addAll(PatientIdcol, firstNamCol, MiddleNameCol, LastNameCol, GenderCol, AgeCol, DateOfBirthcol,
				AdderssCol, MedicalHistoryCol, phoneNumberCol, InsuranceIdcol);
		// tv.setItems(patientlist);

		// Add TableView to scenePane
		scenePane.getChildren().addAll(tv, buttonPane);

		// Set style for TableView
		tv.setStyle(
				"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
		ShowButton.setOnAction(e -> {
			try {
				ObservableList<Patient> Oblist = showPreviouslyExistDataPatient(connection);
				tv.setItems(Oblist);

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		hbox.getChildren().addAll(scenePane, mainPane);

		deleteButton.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					tv.getItems().remove(selectedPatient);
					deletePatientFromDatabase(connection, selectedPatient);
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});

		UpdateButton.setOnAction(e1 -> {

			try {
				UpdateButton(connection, tv);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

		});

		addButton.setOnAction(e -> {
			try {
				handleEmptyTextField(medicalHistoryTextField, patientIdTextField, addressTextField, firstNameTextField,
						middleNameTextField, lastNameTextField, phoneTextField, genderTextField, insuranceIdTextField);
				String patientIdString = patientIdTextField.getText();
				String firstName = firstNameTextField.getText();
				String middleName = middleNameTextField.getText();
				String lastName = lastNameTextField.getText();
				String gender = genderTextField.getText();
				int age = Integer.parseInt(ageTextField.getText());
				String dateOfBirth = dobTextField.getText();
				String address = addressTextField.getText();
				String phoneNumber = phoneTextField.getText();
				String medicalHistory = medicalHistoryTextField.getText();
				String insuranceId = insuranceIdTextField.getText();
				Patient patient = new Patient(patientIdString, firstName, middleName, lastName, gender, age,
						dateOfBirth, address, phoneNumber, medicalHistory, insuranceId);

				if (!isExistingPatient(patient, connection)) {
					insertPatientInDataBase(connection, patient);
					patients.add(patient);
					tv.setItems(patients);
				} else {
					handleDuplicateId();
				}
			} catch (NumberFormatException e1) {
				handleNumberFormateExciption();
				System.out.println(e1);
			} catch (DateTimeParseException e3) {
				System.out.println(e3);
				handleInValidDateFormateExciption();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
	}

	private static void deletePatientFromDatabase(Connection connection, Patient patient) throws SQLException {
		String deleteQuery = "DELETE FROM patient WHERE patientId = ?";

		try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
			statement.setString(1, patient.getPatientId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void deleteInsuranceFromDatabase(Connection connection, Insurance insurance) throws SQLException {
		String deleteQuery = "DELETE FROM healthInsurance WHERE InsuranceId = ?";

		try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
			statement.setString(1, insurance.getInsuranceId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void insertPatientInDataBase(Connection connection, Patient patient) throws SQLException {
		String addQuery = "INSERT INTO patient (patientId, firstName, middleName, lastName, age, gender, phoneNumber, DateOfBirth, Address, MedicalHistory, insuranceId) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		if (!isExistingPatient(patient, connection)) {
			try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
				statement.setString(1, patient.getPatientId());
				statement.setString(2, patient.getFirstName());
				statement.setString(3, patient.getMiddleName());
				statement.setString(4, patient.getLastName());
				statement.setInt(5, patient.getAge());
				statement.setString(6, patient.getGender());
				statement.setString(7, patient.getPhoneNumber());
				statement.setString(8, patient.getDateOfBirth());
				statement.setString(9, patient.getAddress());
				statement.setString(10, patient.getMedicalHistory());
				statement.setString(11, patient.getInsuranceId());
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

	private static void insertInsuranceInDataBase(Connection connection, Insurance insurance) throws SQLException {
		String addQuery = "INSERT INTO healthInsurance (InsuranceId, InsuranceCompany, PolicyStartDate, PolicyEndDate) "
				+ "VALUES (?, ?, ?, ?)";

		if (!isExistingInsurance(insurance, connection)) {
			try (PreparedStatement statement = connection.prepareStatement(addQuery)) {
				statement.setString(1, insurance.getInsuranceId());
				statement.setString(2, insurance.getInsuranceCompany());
				statement.setString(3, insurance.getPolicyStartDate());
				statement.setString(4, insurance.getPolicyEndDate());

				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			return;
		}
	}

	private static ObservableList<Patient> showPreviouslyExistDataPatient(Connection connection) throws SQLException {
		ObservableList<Patient> list = FXCollections.observableArrayList();
		String showQuery = "SELECT patientId, firstName, middleName, lastName, gender, age, dateOfBirth, address, phoneNumber, medicalHistory, InsuranceId FROM patient";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(showQuery)) {

			while (resultSet.next()) {
				String patientId = resultSet.getString("patientId");
				String firstName = resultSet.getString("firstName");
				String middleName = resultSet.getString("middleName");
				String lastName = resultSet.getString("lastName");
				String gender = resultSet.getString("gender");
				int age = resultSet.getInt("age");
				String dateOfBirth = resultSet.getString("dateOfBirth");

				String address = resultSet.getString("address");
				String phoneNumber = resultSet.getString("phoneNumber");
				String medicalHistory = resultSet.getString("medicalHistory");
				String insuranceId = resultSet.getString("InsuranceId");

				Patient patient = new Patient(patientId, firstName, middleName, lastName, gender, age, dateOfBirth,
						phoneNumber, address, medicalHistory, insuranceId);
				list.add(patient);

				// Logging retrieved data
				System.out.println("Retrieved Patient: " + patient);
			}
		} catch (SQLException e) {
			// Handle SQL exceptions
			System.err.println("SQL Exception: " + e.getMessage());
			throw e; // Re-throwing the exception for the caller to handle
		}

		return list;
	}

	private static ObservableList<Insurance> showPreviouslyExistDataInsurance(Connection connection)
			throws SQLException {
		ObservableList<Insurance> list = FXCollections.observableArrayList();
		String showQuery = "SELECT InsuranceId, InsuranceCompany, PolicyStartDate, PolicyEndDate FROM healthInsurance";

		try (Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(showQuery)) {

			while (resultSet.next()) {
				String insuranceId = resultSet.getString("InsuranceId");
				String insuranceCompny = resultSet.getString("InsuranceCompany");
				String startDate = resultSet.getString("PolicyStartDate");
				String endDate = resultSet.getString("PolicyEndDate");

				Insurance insurance = new Insurance(insuranceId, insuranceCompny, startDate, endDate);
				list.add(insurance);

			}
		} catch (SQLException e) {
			System.err.println("SQL Exception: " + e.getMessage());
			throw e; // Re-throwing the exception for the caller to handle
		}

		return list;
	}

	private void handleEmptyTextField(TextField medicalHistoryTextField, TextField patientIdTextField,
			TextField addressTextField, TextField firstNameTextField, TextField middleNameTextField,
			TextField lastNameTextField, TextField phoneTextField, TextField genderTextField,
			TextField insuranceIdTextField) {
//if one of them is Empty return true ...
		// else false
		if (medicalHistoryTextField.getText().isEmpty() || patientIdTextField.getText().isEmpty()
				|| addressTextField.getText().isEmpty() || firstNameTextField.getText().isEmpty()
				|| middleNameTextField.getText().isEmpty() || lastNameTextField.getText().isEmpty()
				|| medicalHistoryTextField.getText().isEmpty() || phoneTextField.getText().isEmpty()
				|| insuranceIdTextField.getText().isEmpty() || genderTextField.getText().isEmpty()) {
			Stage missingStage = new Stage();
			missingStage.setTitle("Missing Exciption");
			Label missingLabel = new Label("Missing Information");
			Button okButton = new Button("Ok");

			okButton.setMinSize(100, 20);
			okButton.setOnAction(e -> {
				missingStage.close();
			});
			okButton.setStyle(
					"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

			missingLabel.setStyle("-fx-font-family:'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold;");
			VBox missingVbox = new VBox(10);
			missingVbox.setAlignment(Pos.CENTER);
			missingVbox.setStyle(
					"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
			missingVbox.getChildren().addAll(missingLabel, okButton);
			Scene numberFormateSc = new Scene(missingVbox, 250, 150);
			missingStage.setScene(numberFormateSc);
			missingStage.show();

		} else {

		}

	}

	private void handleNumberFormateExciption() {
		Stage numberFormatStage = new Stage();
		numberFormatStage.setTitle("Number Formate Exciption");
		Label numberFormatLabel = new Label("The Age must be  Just Number");
		Button okButton = new Button("Ok");

		okButton.setMinSize(100, 20);
		okButton.setOnAction(e -> {
			numberFormatStage.close();
		});
		okButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		numberFormatLabel.setStyle("-fx-font-family:'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold;");
		VBox numberFormatVbox = new VBox(10);
		numberFormatVbox.setAlignment(Pos.CENTER);
		numberFormatVbox.setStyle(
				"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
		numberFormatVbox.getChildren().addAll(numberFormatLabel, okButton);
		Scene numberFormateSc = new Scene(numberFormatVbox, 250, 150);
		numberFormatStage.setScene(numberFormateSc);
		numberFormatStage.show();

	}

	private static void handleDuplicateId() {
		Stage DuplicateIdStage = new Stage();
		DuplicateIdStage.setTitle("Date Formate Exciption");
		Label DuplicateIdLabel = new Label("Its Already Exist :this Id number Exist in DataBase");
		Button okButton = new Button("Ok");
		okButton.setMinSize(100, 20);
		okButton.setOnAction(e -> {
			DuplicateIdStage.close();
		});
		okButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");
		DuplicateIdLabel.setStyle("-fx-font-family:'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold;");
		VBox DuplicateIdVbox = new VBox(10);
		DuplicateIdVbox.setAlignment(Pos.CENTER);
		DuplicateIdVbox.setStyle(
				"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
		DuplicateIdVbox.getChildren().addAll(DuplicateIdLabel, okButton);
		Scene numberFormateSc = new Scene(DuplicateIdVbox, 370, 150);
		DuplicateIdStage.setScene(numberFormateSc);
		DuplicateIdStage.show();

	}

	private void handleInValidDateFormateExciption() {
		Stage DateFormateStage = new Stage();

		DateFormateStage.setTitle("Date Formate Exciption");
		Label DateFormateLabel = new Label("InValid Formate For Date Must be :2003-12-06");
		Button okButton = new Button("Ok");

		okButton.setMinSize(100, 20);
		okButton.setOnAction(e -> {
			DateFormateStage.close();
		});
		okButton.setStyle(
				"-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;");

		DateFormateLabel.setStyle("-fx-font-family:'Times New Roman'; -fx-font-size: 16px; -fx-font-weight: bold;");
		VBox DateFormateVbox = new VBox(10);
		DateFormateVbox.setAlignment(Pos.CENTER);
		DateFormateVbox.setStyle(
				"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
		DateFormateVbox.getChildren().addAll(DateFormateLabel, okButton);
		Scene numberFormateSc = new Scene(DateFormateVbox, 350, 150);
		DateFormateStage.setScene(numberFormateSc);
		DateFormateStage.show();

	}

	private void UpdateButton(Connection connection, TableView<Patient> tv) throws SQLException {
		Stage stage = new Stage();
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		Button button1 = new Button("Patient Id");
		Button button2 = new Button("First Name");
		Button button3 = new Button("Middle Name");
		Button button4 = new Button("Last Name");
		Button button5 = new Button("Medical History");
		Button button6 = new Button("Insurance Id");
		Button button7 = new Button("Gender");
		Button button8 = new Button("DateOfBirth");
		Button button9 = new Button("Address");
		Button button10 = new Button("Phone Number");
		Button button11 = new Button("Age");
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		TextField tf3 = new TextField();
		TextField tf4 = new TextField();
		TextField tf5 = new TextField();
		TextField tf6 = new TextField();
		TextField tf7 = new TextField();
		TextField tf8 = new TextField();
		TextField tf9 = new TextField();
		TextField tf10 = new TextField();
		TextField tf11 = new TextField();

		// Set styles for buttons
		String buttonStyle = "-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;";
		button1.setStyle(buttonStyle);
		button2.setStyle(buttonStyle);
		button3.setStyle(buttonStyle);
		button4.setStyle(buttonStyle);
		button5.setStyle(buttonStyle);
		button6.setStyle(buttonStyle);
		button7.setStyle(buttonStyle);
		button8.setStyle(buttonStyle);
		button9.setStyle(buttonStyle);
		button10.setStyle(buttonStyle);
		button11.setStyle(buttonStyle);
		// Add buttons to the grid pane
		gridPane.add(button1, 0, 0);
		gridPane.add(button2, 0, 1);
		gridPane.add(button3, 0, 2);
		gridPane.add(button4, 0, 3);
		gridPane.add(button5, 0, 4);
		gridPane.add(button6, 0, 5);
		gridPane.add(button7, 0, 6);
		gridPane.add(button8, 0, 7);
		gridPane.add(button9, 0, 8);
		gridPane.add(button10, 0, 9);
		gridPane.add(button11, 0, 10);
		String textFieldStyle = "-fx-font-family:'Times New Roman';-fx-background-color: #F0F0F0; -fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 12px;";

		// Add text fields to the grid pane
		gridPane.add(tf1, 1, 0);
		gridPane.add(tf2, 1, 1);
		gridPane.add(tf3, 1, 2);
		gridPane.add(tf4, 1, 3);
		gridPane.add(tf5, 1, 4);
		gridPane.add(tf6, 1, 5);
		gridPane.add(tf7, 1, 6);
		gridPane.add(tf8, 1, 7);
		gridPane.add(tf9, 1, 8);
		gridPane.add(tf10, 1, 9);
		gridPane.add(tf11, 1, 10);
		tf1.setStyle(textFieldStyle);
		tf2.setStyle(textFieldStyle);
		tf3.setStyle(textFieldStyle);
		tf4.setStyle(textFieldStyle);
		tf5.setStyle(textFieldStyle);
		tf6.setStyle(textFieldStyle);
		tf7.setStyle(textFieldStyle);
		tf8.setStyle(textFieldStyle);
		tf9.setStyle(textFieldStyle);
		tf10.setStyle(textFieldStyle);
		tf11.setStyle(textFieldStyle);
		button1.setOnAction(e -> {

		});

		button2.setOnAction(e ->

		{
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setFirstName(tf2.getText());
					updatePatientFirstName(connection, selectedPatient.getPatientId(), tf2.getText());

					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});
		button3.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setMiddleName(tf3.getText());
					updatePatientMiddleName(connection, selectedPatient.getPatientId(), tf3.getText());

					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});
		button4.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setLastName(tf4.getText());
					updatePatientLastName(connection, selectedPatient.getPatientId(), tf4.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});
		button5.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setLastName(tf5.getText());
					updatePatientMedicalHistory(connection, selectedPatient.getPatientId(), tf5.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});
		button6.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setInsuranceId(tf6.getText());
					updatePatientInsuranceId(connection, selectedPatient.getPatientId(), tf6.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});
		button7.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setGender(tf7.getText());
					updatePatientGender(connection, selectedPatient.getPatientId(), tf7.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});
		button8.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				selectedPatient.setDateOfBirth(tf8.getText());
				updatePatientDateOfBirth(connection, selectedPatient.getPatientId(), tf8.getText());
				tv.refresh();
			} else {
			}

		});
		button9.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setAddress(tf9.getText());
					updatePatientAddress(connection, selectedPatient.getPatientId(), tf9.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});
		button10.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setPhoneNumber(tf10.getText());
					updatePatientPhoneNumber(connection, selectedPatient.getPatientId(), tf10.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});
		button11.setOnAction(e -> {
			Patient selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					String str = tf11.getText();
					int age = Integer.parseInt(str);
					selectedPatient.setAge(age);
					updatePatientAge(connection, selectedPatient.getPatientId(), age);
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});

		gridPane.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-width:2px");
		Scene scene = new Scene(gridPane, 300, 500);
		stage.setScene(scene);
		stage.setTitle("Update Scene with Buttons");
		stage.show();
	}

	/**/
	private void UpdateButtonInsurance(Connection connection, TableView<Insurance> tv) throws SQLException {
		Stage stage = new Stage();
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(20));
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		Button button1 = new Button("Insurance Id");
		Button button2 = new Button("Insurance Company");
		Button button3 = new Button("Insurance Start Date");
		Button button4 = new Button("Insurance END  Date");

		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		TextField tf3 = new TextField();
		TextField tf4 = new TextField();

		// Set styles for buttons
		String buttonStyle = "-fx-font-family: 'Times New Roman'; -fx-font-weight: bold; -fx-background-color: Green; -fx-text-fill: white; -fx-background-radius: 5px;";
		button1.setStyle(buttonStyle);
		button2.setStyle(buttonStyle);
		button3.setStyle(buttonStyle);
		button4.setStyle(buttonStyle);

		// Add buttons to the grid pane
		gridPane.add(button1, 0, 0);
		gridPane.add(button2, 0, 1);
		gridPane.add(button3, 0, 2);
		gridPane.add(button4, 0, 3);

		String textFieldStyle = "-fx-font-family:'Times New Roman';-fx-background-color: #F0F0F0; -fx-border-color: green; -fx-border-width: 2px; -fx-border-radius: 12px;";

		// Add text fields to the grid pane
		gridPane.add(tf1, 1, 0);
		gridPane.add(tf2, 1, 1);
		gridPane.add(tf3, 1, 2);
		gridPane.add(tf4, 1, 3);

		tf1.setStyle(textFieldStyle);
		tf2.setStyle(textFieldStyle);
		tf3.setStyle(textFieldStyle);
		tf4.setStyle(textFieldStyle);

		button1.setOnAction(e ->

		{
			Insurance selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setInsuranceId(tf1.getText());
					updatePatientInsuranceiD(connection, selectedPatient.getInsuranceId(), tf1.getText());

					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}

		});
		button2.setOnAction(e -> {
			Insurance selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setInsuranceCompany(tf2.getText());
					updatePatientInsuranceCompany(connection, selectedPatient.getInsuranceCompany(), tf2.getText());

					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});
		button3.setOnAction(e -> {
			Insurance selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setPolicyStartDate(tf3.getText());
					updatePatientInsuranceStartDate(connection, selectedPatient.getPolicyStartDate(), tf3.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});
		button4.setOnAction(e -> {
			Insurance selectedPatient = tv.getSelectionModel().getSelectedItem();
			if (selectedPatient != null) {
				try {
					selectedPatient.setPolicyEndDate(tf4.getText());
					updatePatientInsuranceEndDate(connection, selectedPatient.getPolicyEndDate(), tf4.getText());
					tv.refresh();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			} else {
			}
		});

		gridPane.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-width:2px");
		Scene scene = new Scene(gridPane, 300, 500);
		stage.setScene(scene);
		stage.setTitle("Update Scene with Buttons");
		stage.show();
	}

	private void updatePatientDateOfBirth(Connection connection, String patientId, String text) {
		String updateQuery = "UPDATE patient SET dateOfBirth = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, text);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Name updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientFirstName(Connection connection, String patientId, String newName) throws SQLException {
		String updateQuery = "UPDATE patient SET firstname = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Name updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientMiddleName(Connection connection, String patientId, String newName) throws SQLException {
		String updateQuery = "UPDATE patient SET middleName = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Name updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void updatePatientInsuranceId(Connection connection, String patientId, String newName) throws SQLException {
		String updateQuery = "UPDATE patient SET InsuranceId  = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, patientId);
			int affectedRows = preparedStatement.executeUpdate();
			if (affectedRows > 0) {
				System.out.println("Name updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void updatePatientLastName(Connection connection, String patientId, String newName) throws SQLException {
		String updateQuery = "UPDATE patient SET lastName = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Name updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientAddress(Connection connection, String patientId, String newAddress) throws SQLException {
		String updateQuery = "UPDATE patient SET address = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newAddress);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Address updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientGender(Connection connection, String patientId, String newGender) throws SQLException {
		String updateQuery = "UPDATE patient SET gender = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newGender);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Gender updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientPhoneNumber(Connection connection, String patientId, String newPhoneNumber)
			throws SQLException {
		String updateQuery = "UPDATE patient SET phoneNumber = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newPhoneNumber);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Phone number updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientMedicalHistory(Connection connection, String patientId, String newMedicalHistory)
			throws SQLException {
		String updateQuery = "UPDATE patient SET medicalHistory = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newMedicalHistory);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Medical history updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientAge(Connection connection, String patientId, int newAge) throws SQLException {
		String updateQuery = "UPDATE patient SET age = ? WHERE patientId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setInt(1, newAge);
			preparedStatement.setString(2, patientId);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
				System.out.println("Age updated successfully for patient with ID: " + patientId);
			} else {
				System.out.println("No such patient with ID: " + patientId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientInsuranceiD(Connection connection, String insuanceId, String newid) throws SQLException {
		String updateQuery = "UPDATE healthInsurance SET InsuranceId  = ? WHERE InsuranceId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newid);
			preparedStatement.setString(2, insuanceId);
			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientInsuranceCompany(Connection connection, String insuancecompany, String newcompany)
			throws SQLException {
		String updateQuery = "UPDATE healthInsurance SET InsuranceCompany = ? WHERE InsuranceId = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newcompany);
			preparedStatement.setString(2, insuancecompany);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientInsuranceStartDate(Connection connection, String insuanceStartDate, String newStartDate)
			throws SQLException {
		String updateQuery = "UPDATE healthInsurance SET PolicyStartDate = ? WHERE  InsuranceId= ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newStartDate);
			preparedStatement.setString(2, insuanceStartDate);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void updatePatientInsuranceEndDate(Connection connection, String insuanceEndDate, String newEndDate)
			throws SQLException {
		String updateQuery = "UPDATE healthInsurance SET PolicyEndDate = ? WHERE   InsuranceId= ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
			preparedStatement.setString(1, newEndDate);
			preparedStatement.setString(2, insuanceEndDate);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows > 0) {
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean isExistingPatient(Patient patient, Connection connection) {
		String isExistsql = "SELECT COUNT(*) FROM patient WHERE PatientId = ?";
		try {
			try (PreparedStatement statement = connection.prepareStatement(isExistsql)) {
				statement.setString(1, patient.getPatientId());
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private static boolean isExistingInsurance(Insurance insurance, Connection connection) {
		String isExistsql = "SELECT COUNT(*) FROM healthInsurance WHERE InsuranceId = ?";
		try {
			try (PreparedStatement statement = connection.prepareStatement(isExistsql)) {
				statement.setString(1, insurance.getInsuranceId());
				try (ResultSet resultSet = statement.executeQuery()) {
					if (resultSet.next()) {
						int count = resultSet.getInt(1);
						return count > 0;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void ConectGui(Connection connection, Stage stage) throws MalformedURLException {
		stage.hide();
		// Image userImage = new
		// Image(getClass().getResource("/images/user.png").toExternalForm());

		ImageView PatientImgVeiw = createImageView("Patient.png");
		ImageView ExitImgVeiw = createImageView("Exit.png");
		ImageView appointmentsImgVeiw = createImageView("appointments.png");
		ImageView doctorImgVeiw = createImageView("Doctors.png");
		ImageView BillsImgVeiw = createImageView("Bills.png");
		ImageView HealthInsuranceImgVeiw = createImageView("HealthInsurance.png");
		ImageView TreatmentsImgVeiw = createImageView("Tretement.png");

		PatientImgVeiw.setFitHeight(35);
		PatientImgVeiw.setFitWidth(35);
		doctorImgVeiw.setFitHeight(35);
		doctorImgVeiw.setFitWidth(35);
		ExitImgVeiw.setFitHeight(35);
		ExitImgVeiw.setFitWidth(35);
		appointmentsImgVeiw.setFitHeight(35);
		appointmentsImgVeiw.setFitWidth(35);
		BillsImgVeiw.setFitHeight(35);
		BillsImgVeiw.setFitWidth(35);
		HealthInsuranceImgVeiw.setFitHeight(35);
		HealthInsuranceImgVeiw.setFitWidth(35);
		TreatmentsImgVeiw.setFitHeight(35);
		TreatmentsImgVeiw.setFitWidth(35);
		Button Patientbutton = new Button("Patient", PatientImgVeiw);
		Button appointmentbutton = new Button(" appointment", appointmentsImgVeiw);
		Button Billsbutton = new Button("Bills", BillsImgVeiw);
		Button doctorbutton = new Button("doctor", doctorImgVeiw);
		Button HealthInsurancebutton = new Button("Health Insurance", HealthInsuranceImgVeiw);
		Button Treatmentsbutton = new Button("Treatments", TreatmentsImgVeiw);
		Button Exitbutton = new Button("Exit", ExitImgVeiw);
		Patientbutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color:Green; -fx-border-radius: 15px; -fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:white");
		appointmentbutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color: Green;-fx-border-radius: 15px; -fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:White");
		Billsbutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color: Green; -fx-border-radius: 15px;-fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:White");
		doctorbutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color: Green; -fx-border-radius: 15px;-fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:White");
		HealthInsurancebutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color: Green; -fx-border-radius: 15px;-fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:White");
		Treatmentsbutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color:  Green; -fx-border-radius: 15px;-fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:White");
		Exitbutton.setStyle(
				"-fx-font-family: 'Times New Roman';-fx-font-weight: bold;-fx-font-size:17px;-fx-text-fill:White;-fx-background-color: Green;-fx-border-radius: 15px; -fx-background-radius: 15px;-fx-border-width:2.5px;-fx-border-color:White");
		Patientbutton.setMaxSize(150, 150);
		appointmentbutton.setMaxSize(200, 150);
		Billsbutton.setMaxSize(150, 150);
		HealthInsurancebutton.setMaxSize(200, 150);
		Treatmentsbutton.setMaxSize(150, 150);
		Exitbutton.setMaxSize(150, 150);
		doctorbutton.setMaxSize(150, 150);
		// Patientbutton.setStyle("");
		VBox leftvbox = new VBox(20);
		leftvbox.getChildren().addAll(doctorbutton, Patientbutton, appointmentbutton);
		leftvbox.setAlignment(Pos.CENTER);
		VBox rightvbox = new VBox(15);
		rightvbox.setAlignment(Pos.CENTER);
		rightvbox.getChildren().addAll(Treatmentsbutton, Billsbutton, HealthInsurancebutton);
		VBox ExitVbox = new VBox(15);
		ExitVbox.setAlignment(Pos.CENTER);
		HBox frontHbox = new HBox(20);
		frontHbox.getChildren().addAll(leftvbox, rightvbox);
		ExitVbox.getChildren().addAll(frontHbox, Exitbutton);
		ExitVbox.setStyle(
				"-fx-border-color:black;-fx-border-width:2px;-fx-background-radius: 15px;-fx-border-radius: 15px;-fx-background-color:white");
		frontHbox.setAlignment(Pos.CENTER);
		Stage primaryStage = new Stage();
		Scene scene = new Scene(ExitVbox, 390, 370);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Conect Form");
		primaryStage.show();
		doctorbutton.setOnAction(e -> {
			Main doctor = new Main();
			Scene secnce = new Scene(doctor.getDectorPage());
			Stage Stage = new Stage();
			Stage.setScene(secnce);
			Stage.show();
		});
		Patientbutton.setOnAction(e -> {
			try {
				PatientButton(connection, primaryStage);
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		appointmentbutton.setOnAction(e -> {
			AppointmentApp b = new AppointmentApp();
			Stage newStage = new Stage();

			try {
				b.start(newStage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

		Billsbutton.setOnAction(e -> {

			BillApp b = new BillApp();
			Stage newStage = new Stage();

			try {
				b.start(newStage);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		});

		HealthInsurancebutton.setOnAction(e -> {
			try {
				InsuranceButton(connection, stage);
			} catch (MalformedURLException e1) {

				e1.printStackTrace();
			}
		});

		Exitbutton.setOnAction(e -> {
		System.exit(0);

		});

		Treatmentsbutton.setOnAction(e -> {
			Main treatment = new Main();
			Scene secnce = new Scene(treatment.getTratmentsPage());
			Stage Stage = new Stage();
			Stage.setScene(secnce);
			Stage.show();
		});
	}

	private ImageView createImageView(String imageName) throws MalformedURLException {
		String imagePath = "C:\\Users\\Lenovo\\AppData\\Local\\Temp\\[Your_Temp_Folder]\\DataBaseProject\\" + imageName;
		Image image = new Image(new File(imagePath).toURI().toURL().toString());
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(35);
		imageView.setFitWidth(35);
		return imageView;
	}

	public static void main(String[] args) {
		launch(args);
	}
}