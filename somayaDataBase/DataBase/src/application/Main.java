
package application;

import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	private ObservableList<Dector> data = FXCollections.observableArrayList();
	// TableView<Dector> table = new TableView<>();
	private ObservableList<Dector> filteredObservableData = FXCollections.observableArrayList();
	private ObservableList<Tretments> data2 = FXCollections.observableArrayList();
	private TableView<Tretments> table2 = new TableView<>();
	private ObservableList<Tretments> filteredObservableData2 = FXCollections.observableArrayList();
	private Connection connections;

	@Override

	public void start(Stage primaryStage) {
		try {
			connections = new Connection();

			BorderPane root = new BorderPane();
			Scene scene = new Scene(getTratmentsPage());
			// primaryStage.setMaximized(true);
			primaryStage.setMaximized(true);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public GridPane getDectorPage() {
		GridPane pane = new GridPane();
		BackgroundImage pr1 = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\R.jpg"), BackgroundRepeat.REPEAT,
				BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		Background pr4 = new Background(pr1);
		pane.setBackground(pr4);
		// Create four Column in TableView
		TableColumn<Dector, Integer> Doctor_IDCol = new TableColumn<>("Doctor_ID"); // first column with date
		TableColumn<Dector, String> First_NameCol = new TableColumn<>("First_Name");
		TableColumn<Dector, String> Middle_NameCol = new TableColumn<>("Middle_Name");
		TableColumn<Dector, String> Last_NameCol = new TableColumn<>("Last_Name");
		TableColumn<Dector, String> SpeciallyCol = new TableColumn<>("Specially");
		// TableColumn<Dector, String> GenderCol = new TableColumn<>("Gender");
		TableColumn<Dector, Long> Phone_NumberCol = new TableColumn<>("Phone_Number");
		TableColumn<Dector, String> StartTimeCol = new TableColumn<>("StartTime");
		TableColumn<Dector, String> EndTimeCol = new TableColumn<>("EndTime");
		TableColumn<Dector, String> EmailCol = new TableColumn<>("Email");
		TableColumn<Dector, String> DiagnosisCo = new TableColumn<>("Diagnosis");

		// set width for all column
		Doctor_IDCol.setPrefWidth(100);
		First_NameCol.setPrefWidth(100);
		Middle_NameCol.setPrefWidth(100);
		Last_NameCol.setPrefWidth(100);
		SpeciallyCol.setPrefWidth(100);
		// GenderCol.setPrefWidth(100);
		Phone_NumberCol.setPrefWidth(100);
		StartTimeCol.setPrefWidth(100);
		EndTimeCol.setPrefWidth(100);
		EmailCol.setPrefWidth(100);
		DiagnosisCo.setPrefWidth(200);
		TableView<Dector> table = new TableView<Dector>();
		// add column to the TableView
		table.getColumns().addAll(Doctor_IDCol, First_NameCol, Middle_NameCol, Last_NameCol, SpeciallyCol,
				Phone_NumberCol, StartTimeCol, EndTimeCol, EmailCol, DiagnosisCo);

		// fill the columns
		Doctor_IDCol.setCellValueFactory(cellData -> cellData.getValue().Doctor_IDProperty().asObject());
		First_NameCol.setCellValueFactory(cellData -> cellData.getValue().First_NameProperty());
		Middle_NameCol.setCellValueFactory(cellData -> cellData.getValue().Middle_NameProperty());
		Last_NameCol.setCellValueFactory(cellData -> cellData.getValue().Last_NameProperty());
		SpeciallyCol.setCellValueFactory(cellData -> cellData.getValue().SpeciallyProperty());
		// GenderCol.setCellValueFactory(cellData ->
		// cellData.getValue().GenderProperty());
		Phone_NumberCol.setCellValueFactory(cellData -> cellData.getValue().Phone_NumberProperty().asObject());
		StartTimeCol.setCellValueFactory(cellData -> cellData.getValue().Work_HouresProperty());
		EndTimeCol.setCellValueFactory(cellData -> cellData.getValue().EndTimeProperty());
		EmailCol.setCellValueFactory(cellData -> cellData.getValue().EmailProperty());
		DiagnosisCo.setCellValueFactory(cellData -> cellData.getValue().DiagnosisProperty());
		fillTableViewFromSQL(table);
		// Set the table items
		table.setItems(data);

		// Add the table to the pane

		System.out.println("fulll");
		// TextFields and Labels
		Label lab1 = new Label("Doctor ID : ");
		lab1.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		TextField doctor = new TextField();
		doctor.setPromptText("Enter Doctor ID");

		Label lab2 = new Label("First Name : ");
		lab2.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		TextField doctor1 = new TextField();
		doctor1.setPromptText("Enter First Name");

		Label lab3 = new Label("Middle Name : ");
		lab3.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField doctor2 = new TextField();
		doctor2.setPromptText("Enter Middle Name");

		Label lab4 = new Label("Last Name : ");
		lab4.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField doctor3 = new TextField();
		doctor3.setPromptText("Enter Last Name");

		Label lab5 = new Label("Specialty : ");
		lab5.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField specialty = new TextField();
		specialty.setPromptText("Enter Specialty");

		Label lab6 = new Label("Gender : ");
		lab6.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		ComboBox<String> box = new ComboBox<>();
		box.setPromptText("Choose Gender");
		box.getItems().addAll("Female", "Male");

		Label lab7 = new Label("Phone Number : ");
		lab7.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField phoneNumber = new TextField();
		phoneNumber.setPromptText("Enter Phone Number");

		Label lab8 = new Label("Start Time : ");
		lab8.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField startTime = new TextField();
		startTime.setPromptText("Enter Start Time");

		Label lab9 = new Label("End Time : ");
		lab9.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField endTime = new TextField();
		endTime.setPromptText("Enter End Time");

		Label lab10 = new Label("Email : ");
		lab10.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField email = new TextField();
		email.setPromptText("Enter Email");

		Label lab11 = new Label("Diagnosis : ");
		lab11.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField diagnosis = new TextField();
		diagnosis.setPromptText("Enter Diagnosis");
		diagnosis.setPrefHeight(70);

		Label lab12 = new Label("Select The Doctor You Want To Delete: ");
		lab12.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField searchDoctor = new TextField();
		searchDoctor.setPromptText("Enter Doctor ID");

		// Set label color to white
		lab1.setTextFill(Color.WHITE);
		lab2.setTextFill(Color.WHITE);
		lab3.setTextFill(Color.WHITE);
		lab4.setTextFill(Color.WHITE);
		lab5.setTextFill(Color.WHITE);
		lab6.setTextFill(Color.WHITE);
		lab7.setTextFill(Color.WHITE);
		lab8.setTextFill(Color.WHITE);
		lab9.setTextFill(Color.WHITE);
		lab10.setTextFill(Color.WHITE);
		lab11.setTextFill(Color.WHITE);
		lab12.setTextFill(Color.WHITE);
		searchDoctor.setPromptText("Search The Doctor");

		// Button
		Button add = new Button("   ");
		add.setPrefHeight(50);
		add.setMaxWidth(50);
		BackgroundImage ppp = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-add-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background ppp4 = new Background(ppp);
		add.setBackground(ppp4);
		Button deleteButton = new Button(" ");
		deleteButton.setPrefHeight(50);
		deleteButton.setMaxWidth(50);
		BackgroundImage pp = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-delete-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background pp4 = new Background(pp);
		deleteButton.setBackground(pp4);
		Button search = new Button(" ");
		search.setPrefHeight(48);
		search.setMaxWidth(48);
		BackgroundImage p = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-search-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background p4 = new Background(p);

		Button update = new Button("  ");
		update.setPrefHeight(48);
		update.setMaxWidth(48);
		BackgroundImage p2 = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-update-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background p44 = new Background(p2);
		update.setBackground(p44);
		search.setBackground(p4);
		HBox flow = new HBox();
		flow.getChildren().addAll(add, deleteButton, update);

		pane.add(lab1, 1, 1);
		pane.add(doctor, 2, 1);
		pane.add(lab2, 1, 2);
		pane.add(doctor1, 2, 2);
		pane.add(lab3, 1, 3);
		pane.add(doctor2, 2, 3);
		pane.add(lab4, 1, 4);
		pane.add(doctor3, 2, 4);
		pane.add(lab5, 1, 5);
		pane.add(specialty, 2, 5);
		pane.add(lab7, 1, 6);
		pane.add(phoneNumber, 2, 6);
		pane.add(lab8, 1, 7);
		pane.add(startTime, 2, 7);
		pane.add(lab9, 1, 8);
		pane.add(endTime, 2, 8);
		pane.add(lab10, 1, 9);
		pane.add(email, 2, 9);
		pane.add(lab11, 1, 10);
		pane.add(diagnosis, 2, 10);
		pane.add(add, 1, 11);
		pane.add(deleteButton, 2, 11);
		pane.add(update, 3, 11);
		pane.add(search, 1, 12);
		pane.add(searchDoctor, 2, 12);
		pane.add(table, 7, 11);
		pane.setAlignment(Pos.TOP_CENTER);
		add.setOnAction(e -> {
			data.add(new Dector(Integer.parseInt(doctor.getText()), doctor1.getText(), doctor2.getText(),
					doctor3.getText(), specialty.getText(), Long.parseLong(phoneNumber.getText()), startTime.getText(),
					add.getText(), email.getText(), diagnosis.getText()));
			// Establish a connection to the database
			try {
				java.sql.Connection connection = connections.getConnection();

				// Create the SQL INSERT statement
				String insertQuery = "INSERT INTO Doctor (doctorId, FirstName, middleName, LastName, phoneNumber, Specialty, startTime, endTime, email, diagnosis) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				// Create a PreparedStatement object
				PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

				// Set the values for the parameters
				preparedStatement.setInt(1, Integer.parseInt(doctor.getText()));
				preparedStatement.setString(2, doctor1.getText());
				preparedStatement.setString(3, doctor2.getText());
				preparedStatement.setString(4, doctor3.getText());
				preparedStatement.setLong(5, Long.parseLong(phoneNumber.getText()));
				preparedStatement.setString(6, specialty.getText());
				preparedStatement.setString(7, startTime.getText());
				preparedStatement.setString(8, endTime.getText());
				preparedStatement.setString(9, email.getText());
				preparedStatement.setString(10, diagnosis.getText());

				// Execute the INSERT statement
				int rowsAffected = preparedStatement.executeUpdate();

				// Check the number of rows affected
				if (rowsAffected > 0) {
					System.out.println("Record inserted successfully into Doctor table.");
				} else {
					System.out.println("Failed to insert record into Doctor table.");
				}

				// Close the PreparedStatement and Connection
				preparedStatement.close();
				connection.close();

				doctor.clear();
				doctor1.clear();
				doctor2.clear();
				doctor3.clear();
				specialty.clear();
				// box.sets
				phoneNumber.clear();
				startTime.clear();
				endTime.clear();
				email.clear();
				diagnosis.clear();

			} catch (SQLException ex) {
				ex.printStackTrace();
			}

		});
		deleteButton.setOnAction(e -> {
			Dector selectedDoctor = table.getSelectionModel().getSelectedItem();
			if (selectedDoctor != null) {
				// Remove the Electricity object from the main data list
				data.remove(selectedDoctor);
				try {
					// Establish a connection to the database
					java.sql.Connection connection = connections.getConnection();
					// Create the SQL DELETE statement
					String deleteQuery = "DELETE FROM Doctor WHERE doctorId = ?";

					// Create a PreparedStatement object
					PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

					// Set the value for the parameter
					preparedStatement.setInt(1, selectedDoctor.getDoctor_ID());

					// Execute the DELETE statement
					int rowsAffected = preparedStatement.executeUpdate();

					// Check the number of rows affected
					if (rowsAffected > 0) {
						System.out.println("Record deleted successfully from Doctor table.");
					} else {
						System.out.println("Failed to delete record from Doctor table.");
					}

					// Close the PreparedStatement and Connection
					preparedStatement.close();
					connection.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		search.setOnAction(e -> {
			for (Dector datas : data) {
				if (datas.getFirst_Name().equals(searchDoctor.getText())) {
					filteredObservableData.add(datas);
				}
			}

			// Update the table with filtered data
			table.setItems(filteredObservableData);
			table.refresh();

		});

		update.setOnAction(e -> {
			Dector selectedDoctor = table.getSelectionModel().getSelectedItem();
			if (selectedDoctor != null) {
				try {
					// Remove the selected doctor from the data list
					data.remove(selectedDoctor);

					// Update the selectedDoctor object with the new values from the text fields
					selectedDoctor.setDoctor_ID(Integer.parseInt(doctor.getText()));
					selectedDoctor.setFirst_Name(doctor1.getText());
					selectedDoctor.setMiddle_Name(doctor2.getText());
					selectedDoctor.setLast_Name(doctor3.getText());
					selectedDoctor.setSpecially(specialty.getText());
					selectedDoctor.setPhone_Number(Long.parseLong(phoneNumber.getText()));
					selectedDoctor.setWork_Houres(startTime.getText());
					selectedDoctor.setEndTime(endTime.getText());
					selectedDoctor.setEmail(email.getText());
					selectedDoctor.setDiagnosis(diagnosis.getText());

					// Establish a connection to the database
					java.sql.Connection connection = connections.getConnection();

					// Create the SQL UPDATE statement
					String updateQuery = "UPDATE Doctor SET doctorId = ?, FirstName = ?, middleName = ?, LastName = ?, "
							+ "phoneNumber = ?, Specialty = ?, startTime = ?, endTime = ?, email = ?, diagnosis = ? WHERE doctorId = ?";

					// Create a PreparedStatement object
					PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

					// Set the values for the parameters
					preparedStatement.setInt(1, selectedDoctor.getDoctor_ID());
					preparedStatement.setString(2, selectedDoctor.getFirst_Name());
					preparedStatement.setString(3, selectedDoctor.getMiddle_Name());
					preparedStatement.setString(4, selectedDoctor.getLast_Name());
					preparedStatement.setLong(5, selectedDoctor.getPhone_Number());
					preparedStatement.setString(6, selectedDoctor.getSpecially());
					preparedStatement.setString(7, selectedDoctor.getstartTime());
					preparedStatement.setString(8, selectedDoctor.getendTime());
					preparedStatement.setString(9, selectedDoctor.getEmail());
					preparedStatement.setString(10, selectedDoctor.getDiagnosis());
					preparedStatement.setInt(11, selectedDoctor.getDoctor_ID());

					// Execute the UPDATE statement
					int rowsAffected = preparedStatement.executeUpdate();

					// Check the number of rows affected
					if (rowsAffected > 0) {
						System.out.println("Record updated successfully in Doctor table.");
						// Add the updated doctor back to the data list
						data.add(new Dector(Integer.parseInt(doctor.getText()), doctor1.getText(), doctor2.getText(),
								doctor3.getText(), specialty.getText(), Long.parseLong(phoneNumber.getText()),
								startTime.getText(), add.getText(), email.getText(), diagnosis.getText()));
					} else {
						System.out.println("Failed to update record in Doctor table.");
					}

					// Close the PreparedStatement and Connection
					preparedStatement.close();
					connection.close();

				} catch (SQLException ex) {
					ex.printStackTrace();

				}
			}

		});

		return pane;

	}

	private void fillTableViewFromSQL(TableView<Dector> table) {
		// ObservableList<Doctor> data = FXCollections.observableArrayList();
		table = new TableView<>();

		try {
			 java.sql.Connection connection = DriverManager.getConnection(
	                    "jdbc:mysql://127.0.0.1:3306/clinicmanagementsystem?sslmode=require",
	                    "root", "1234EE");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Doctor");

			while (resultSet.next()) {
				int doctorId = resultSet.getInt("doctorId");
				String firstName = resultSet.getString("FirstName");
				String middleName = resultSet.getString("middleName");
				String lastName = resultSet.getString("LastName");
				long phoneNumber = resultSet.getLong("phoneNumber");
				String specialty = resultSet.getString("Specialty");
				String startTime = resultSet.getString("startTime");
				String endTime = resultSet.getString("endTime");
				String email = resultSet.getString("email");
				String diagnosis = resultSet.getString("diagnosis");

				Dector doctor = new Dector(doctorId, firstName, middleName, lastName, specialty, phoneNumber, startTime,
						endTime, email, diagnosis);
				data.add(doctor);
				table.setItems(data);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public GridPane getTratmentsPage() {
		BackgroundImage p1 = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\5be9788fc3b17.jpg"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background p4 = new Background(p1);

		GridPane pane2 = new GridPane();
		pane2.setBackground(p4);
		// Create four Column in TableView

		TableColumn<Tretments, Integer> Tretment_IDCol = new TableColumn<>("Tretment_ID");
		TableColumn<Tretments, String> Tretment_NameCol = new TableColumn<>("Tretment_Name");
		TableColumn<Tretments, String> DescriptionCol = new TableColumn<>("Description");
		TableColumn<Tretments, Double> CostCol = new TableColumn<>("Cost");
		TableColumn<Tretments, LocalDate> dateofServiceCol = new TableColumn<>("dateofService");
		TableColumn<Tretments, Integer> Patient_IDCol = new TableColumn<>("Patient_ID"); // first column with date
		TableColumn<Tretments, Integer> Doctor_IDsCol = new TableColumn<>("Doctor_ID"); // first column with date

		// set width for all column
		Patient_IDCol.setPrefWidth(100);
		Tretment_IDCol.setPrefWidth(100);
		Tretment_NameCol.setPrefWidth(100);
		DescriptionCol.setPrefWidth(100);
		CostCol.setPrefWidth(100);
		dateofServiceCol.setPrefWidth(100);
		Doctor_IDsCol.setPrefWidth(100);

		// add column to the TableView
		table2.getColumns().addAll(Tretment_IDCol, Tretment_NameCol, DescriptionCol, CostCol, dateofServiceCol,
				Patient_IDCol, Doctor_IDsCol);

		// fill the columns
		Tretment_IDCol.setCellValueFactory(cellData -> cellData.getValue().Tretment_IDProperty().asObject());
		Tretment_NameCol.setCellValueFactory(cellData -> cellData.getValue().Tretment_NameProperty());
		DescriptionCol.setCellValueFactory(cellData -> cellData.getValue().DescriptionProperty());
		CostCol.setCellValueFactory(cellData -> cellData.getValue().CostProperty().asObject());
		dateofServiceCol.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		Patient_IDCol.setCellValueFactory(cellData -> cellData.getValue().Patient_IDProperty().asObject());
		Doctor_IDsCol.setCellValueFactory(cellData -> cellData.getValue().Dector_IDProperty().asObject());
		fillTableViewFromSQL2(table2);
		table2.setItems(data2);
		// TextFields And Labels
		// TextFields And Labels

		Label lab2 = new Label("Treatment ID : ");
		lab2.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		TextField tretment = new TextField();
		tretment.setPromptText("Enter Treatment ID");

		Label lab3 = new Label("Treatment Name : ");
		lab3.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField tretment_name = new TextField();
		tretment_name.setPromptText("Enter Treatment Name");

		Label lab4 = new Label("Description : ");
		lab4.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField desc = new TextField();
		desc.setPromptText("Enter Description");

		Label lab5 = new Label("Cost : ");
		lab5.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		TextField cost = new TextField();
		cost.setPromptText("Enter Cost");

		Label lab6 = new Label("Date of Service : ");
		lab6.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));
		DatePicker DateofService = new DatePicker();
		DateofService.setPromptText("Select Date of Service");

		TextField Work_Houres = new TextField();

		Label lab9 = new Label("Select The Doctor You Want To Delete It");
		lab9.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 15));

		Label lab1 = new Label("Patient ID : ");
		lab1.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		TextField pastient = new TextField();
		pastient.setPromptText("Enter Patient ID");

		Label lab11 = new Label("Doctor ID : ");
		lab11.setFont(Font.font("Times New Roman", FontWeight.MEDIUM, FontPosture.ITALIC, 14));
		TextField dector = new TextField();
		dector.setPromptText("Enter Doctor ID");
		TextField sear = new TextField();
		sear.setPromptText("Search The pastient");
		// Button
		Button add = new Button("   ");
		add.setPrefHeight(50);
		add.setMaxWidth(50);
		BackgroundImage ppp = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-add-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background ppp4 = new Background(ppp);
		add.setBackground(ppp4);
		Button deleteButton = new Button(" ");
		deleteButton.setPrefHeight(50);
		deleteButton.setMaxWidth(50);
		BackgroundImage pp = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-delete-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background pp4 = new Background(pp);
		deleteButton.setBackground(pp4);
		Button search = new Button(" ");
		search.setPrefHeight(48);
		search.setMaxWidth(48);
		BackgroundImage p = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-search-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background p41 = new Background(p);

		Button update = new Button("  ");
		update.setPrefHeight(48);
		update.setMaxWidth(48);
		BackgroundImage p2 = new BackgroundImage(
				new Image("C:\\Users\\LENOVO\\Desktop\\Data Structer\\DataBase\\icons8-update-48.png"),
				BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				BackgroundSize.DEFAULT);
		Background p44 = new Background(p2);
		update.setBackground(p44);
		search.setBackground(p41);
		Button search2=new Button("Search The Name of ");

//Set the content in the tables
		pane2.add(lab1, 1, 1);
		pane2.add(pastient, 2, 1);
		pane2.add(lab11, 1, 2);
		pane2.add(dector, 2, 2);
		pane2.add(lab2, 1, 3);
		pane2.add(tretment, 2, 3);
		pane2.add(lab3, 1, 4);
		pane2.add(tretment_name, 2, 4);
		pane2.add(lab4, 1, 5);
		pane2.add(desc, 2, 5);
		pane2.add(lab5, 1, 6);
		pane2.add(cost, 2, 6);
		pane2.add(lab6, 1, 7);
		pane2.add(DateofService, 2, 7);
		pane2.add(add, 1, 10);
		pane2.add(update, 2, 10);
		pane2.add(deleteButton, 2, 11);
		pane2.add(search, 1, 12);
		pane2.add(sear, 2, 12);
		pane2.add(table2, 10, 14);
		// LocalDate date=DateofService.getValue();

		add.setOnAction(e -> {
			LocalDate dateOfService = DateofService.getValue();

			data2.add(new Tretments(Integer.parseInt(tretment.getText()), tretment_name.getText(), desc.getText(),
					DateofService.getValue(), Double.parseDouble(cost.getText()), Integer.parseInt(pastient.getText()),
					Integer.parseInt(dector.getText())));

			insertDataIntoDatabase(new Tretments(Integer.parseInt(tretment.getText()), tretment_name.getText(),
					desc.getText(), DateofService.getValue(), Double.parseDouble(cost.getText()),
					Integer.parseInt(pastient.getText()), Integer.parseInt(dector.getText())));

			table2.setItems(data2);

			tretment.clear();
			pastient.clear();
			tretment_name.clear();
			desc.clear();
			cost.clear();
			DateofService.setValue(null);
		});
		deleteButton.setOnAction(e ->

		{
			Tretments selectedTetermints = table2.getSelectionModel().getSelectedItem();
			if (selectedTetermints != null) {
				// Remove the Electricity object from the main data list
				data2.remove(selectedTetermints);
				try {
					// Establish a connection to the database
					java.sql.Connection connection = connections.getConnection();
					// Create the SQL DELETE statement
					String deleteQuery = "DELETE FROM Treatment WHERE treatmentId = ?";

					// Create a PreparedStatement object
					PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

					// Set the value for the parameter
					preparedStatement.setInt(1, selectedTetermints.getTretment_ID());

					// Execute the DELETE statement
					int rowsAffected = preparedStatement.executeUpdate();

					// Check the number of rows affected
					if (rowsAffected > 0) {
						System.out.println("Record deleted successfully from Doctor table.");
					} else {
						System.out.println("Failed to delete record from Doctor table.");
					}

					// Close the PreparedStatement and Connection
					preparedStatement.close();
					connection.close();

				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		sear.setOnAction(e -> {
			for (Tretments data : data2) {
				if (data.getPatient_ID() == Integer.parseInt(pastient.getText())) {
					filteredObservableData2.add(data);

					// Update the table with filtered data
					table2.setItems(filteredObservableData2);
					table2.refresh();
				}
			}

			// Update the table with filtered data
			table2.setItems(filteredObservableData2);
			table2.refresh();

		});
		// Update button event handler
		update.setOnAction(e -> {
		    Tretments selectedTretments = table2.getSelectionModel().getSelectedItem();
		    if (selectedTretments != null) {
		        // Remove the selected treatment from the data list
		        data2.remove(selectedTretments);
		        
		        // Update the selected Tretments with the new values
		        selectedTretments.setTretment_ID(Integer.parseInt(tretment.getText()));
		        selectedTretments.setTretment_Name(tretment_name.getText());
		        selectedTretments.setDescription(desc.getText());
		        selectedTretments.setCost(Double.parseDouble(cost.getText()));
		        selectedTretments.setDateofService(DateofService.getValue());
		        selectedTretments.setPatient_ID(Integer.parseInt(pastient.getText()));
		        selectedTretments.setDector_ID(Integer.parseInt(dector.getText()));
		        
		        // Update the data in the database
		        updateTretmentDataInDatabase(selectedTretments);
		        
		        // Refresh the TableView
		        table2.refresh();
		        
		        // Show a confirmation message
		        showConfirmationDialog("Update Successful", "Tretments data has been updated.");
		    } else {
		        // Show a warning if no row is selected
		        showAlert("No Selection", "Please select a Tretments data to update.");
		    }
		});
		search2.setOnAction(e -> {
		    String searchName = search2.getText();

		    String selectQuery = "SELECT * FROM treatments WHERE Tretment_Name LIKE ?";
		    try (java.sql.Connection connection = connections.getConnection()) {
		        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
		            preparedStatement.setString(1, "%" + searchName + "%");

		            try (ResultSet rs = preparedStatement.executeQuery()) {
		                while (rs.next()) {
		                    // Retrieve treatment data
		                }
		            }
		        }
		    } catch (SQLException ex) {
		        ex.printStackTrace();
		    }
		});

		return pane2;

	}

	private void fillTableViewFromSQL2(TableView<Tretments> table) {
		// ObservableList<Tretments> data = FXCollections.observableArrayList();

		try {
			java.sql.Connection  connection = DriverManager.getConnection(
	                    "jdbc:mysql://127.0.0.1:3306/clinicmanagementsystem?sslmode=require",
	                    "root", "1234EE");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Treatment");

			while (resultSet.next()) {
				int treatmentId = resultSet.getInt("treatmentId");
				String treatmentName = resultSet.getString("treatmentName");
				String description = resultSet.getString("descriptions");
				java.sql.Date dateOfService = resultSet.getDate("dateofServise");
				double cost = resultSet.getDouble("cost");
				int patientId = resultSet.getInt("patientId");
				int doctorId = resultSet.getInt("DoctorId");
				LocalDate localDateOfService = dateOfService.toLocalDate();
				Tretments treatment = new Tretments(treatmentId, treatmentName, description, localDateOfService, cost,
						patientId, doctorId);
				data2.add(treatment);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		table2.setItems(data2);
	}

	private void insertDataIntoDatabase(Tretments TretmentsData) {
		try {
			java.sql.Connection connection = connections.getConnection();
			String insertQuery = "INSERT INTO Treatment (treatmentId, treatmentName, descriptions, dateofServise, cost, patientId, DoctorId) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
				preparedStatement.setInt(1, TretmentsData.getTretment_ID());
				preparedStatement.setString(2, TretmentsData.getTretment_Name());
				preparedStatement.setString(3, TretmentsData.getDescription());
				LocalDate dateOfService = TretmentsData.getsetDateofService();
				if (dateOfService != null) {
					preparedStatement.setDate(4, java.sql.Date.valueOf(dateOfService));
				} else {
					preparedStatement.setNull(4, java.sql.Types.DATE);
				}
				preparedStatement.setDouble(5, TretmentsData.getCost());
				preparedStatement.setInt(6, TretmentsData.getPatient_ID());
				preparedStatement.setInt(7, TretmentsData.getDector_ID());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void updateTretmentDataInDatabase(Tretments tretmentData) {
	    try {
	        java.sql.Connection connection = connections.getConnection();
	        String sql = "UPDATE Treatment SET treatmentId=?,treatmentName=?, descriptions=?, dateofServise=?, cost=?, patientId=?, DoctorId=? ;";
	        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        	 preparedStatement.setInt(1, tretmentData.getTretment_ID());
	            preparedStatement.setString(2, tretmentData.getTretment_Name());
	            preparedStatement.setString(3, tretmentData.getDescription());
	            LocalDate dateOfService = tretmentData.getsetDateofService();
	            if (dateOfService != null) {
	                preparedStatement.setDate(4, java.sql.Date.valueOf(dateOfService));
	            } else {
	                preparedStatement.setNull(4, java.sql.Types.DATE);
	            }
	            preparedStatement.setDouble(5, tretmentData.getCost());
	            preparedStatement.setInt(6, tretmentData.getPatient_ID());
	            preparedStatement.setInt(7, tretmentData.getDector_ID());
	           

	            preparedStatement.executeUpdate();
	        }
	    } catch (SQLException | NumberFormatException e) {
	        e.printStackTrace();
	        showAlert("Error", "Failed to update tretment data in the database.");
	    }
	}
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	private void showConfirmationDialog(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
