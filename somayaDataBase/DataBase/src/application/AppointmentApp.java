package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.*;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public class AppointmentApp extends Application {

	private ObservableList<Appointment> tableData = FXCollections.observableArrayList();
	private static Connection con;

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, SQLException, MalformedURLException {

		try {
			// Initialize the Connection variable
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root", "1210712");
			Statement st = con.createStatement();
			ResultSet sr = st.executeQuery("SELECT * FROM Appointment ");
		} catch (Exception ex) {
			System.out.println(ex);
		}

		// Create a GridPane layout
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		TextField appointmentIDTextField = new TextField();
		TextField patientIDTextField = new TextField();
		TextField doctorIDTextField = new TextField();
		DatePicker appointmentDatePicker = new DatePicker();
		TextField appointmentTimeTextField = new TextField();
		TextField durationTextField = new TextField();
		TextField appointmentTypeTextField = new TextField();
		TextField paymentStatusTextField = new TextField();
		TextField commentsTextField = new TextField();
		
		appointmentIDTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		patientIDTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		doctorIDTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		appointmentDatePicker.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		appointmentTimeTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		durationTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		appointmentTypeTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		paymentStatusTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		commentsTextField.setStyle("-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;");
		String textFieldStyle = "-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50;";
		String focusedTextFieldStyle = "-fx-background-color: #ECF0F1; -fx-text-fill: #2C3E50; -fx-border-color: #27AE60; -fx-border-radius: 10;";
		
		
		appointmentIDTextField.setStyle(textFieldStyle);
		patientIDTextField.setStyle(textFieldStyle);
		doctorIDTextField.setStyle(textFieldStyle);
		appointmentDatePicker.setStyle(textFieldStyle);
		appointmentTimeTextField.setStyle(textFieldStyle);
		durationTextField.setStyle(textFieldStyle);
		appointmentTypeTextField.setStyle(textFieldStyle);
		paymentStatusTextField.setStyle(textFieldStyle);
		commentsTextField.setStyle(textFieldStyle);

			appointmentIDTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        appointmentIDTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        appointmentIDTextField.setStyle(textFieldStyle);
		    }
		});

		patientIDTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        patientIDTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        patientIDTextField.setStyle(textFieldStyle);
		    }
		});

		doctorIDTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        doctorIDTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        doctorIDTextField.setStyle(textFieldStyle);
		    }
		});

	
		appointmentTimeTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        appointmentTimeTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        appointmentTimeTextField.setStyle(textFieldStyle);
		    }
		});

		durationTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        durationTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        durationTextField.setStyle(textFieldStyle);
		    }
		});

		appointmentTypeTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        appointmentTypeTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        appointmentTypeTextField.setStyle(textFieldStyle);
		    }
		});

		paymentStatusTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        paymentStatusTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        paymentStatusTextField.setStyle(textFieldStyle);
		    }
		});

		commentsTextField.focusedProperty().addListener((obs, oldVal, newVal) -> {
		    if (newVal) {
		        commentsTextField.setStyle(focusedTextFieldStyle);
		    } else {
		        commentsTextField.setStyle(textFieldStyle);
		    }
		});




		String imagePath = "C:\\Users\\Lenovo\\AppData\\Local\\Temp\\Rar$DIa0.106\\appoint.jfif";
		File file = new File(imagePath);
		String imageUrl = file.toURI().toURL().toString();
		Image image = new Image(imageUrl);

		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(200);
		imageView.setFitHeight(200);

		// Buttons
		Button searchButton = new Button("Search");
		Button addButton = new Button("Add");
		Button deleteButton = new Button("Delete");
		Button updateButton = new Button("Update");

		// DatePicker for Service Date
		DatePicker serviceDatePicker = new DatePicker();
		serviceDatePicker.setPromptText("Select appointment Date");

		// Add DatePicker to the GridPane

		double buttonWidth = 100;
		double buttonHeight = 50;

		searchButton.setPrefSize(buttonWidth, buttonHeight);
		addButton.setPrefSize(buttonWidth, buttonHeight);
		deleteButton.setPrefSize(buttonWidth, buttonHeight);
		updateButton.setPrefSize(buttonWidth, buttonHeight);
		
		
		

		gridPane.addRow(0, new Label("Appointment ID:"), appointmentIDTextField);
		gridPane.addRow(1, new Label("Patient ID:"), patientIDTextField);
		gridPane.addRow(2, new Label("Doctor ID:"), doctorIDTextField);
		gridPane.addRow(3, new Label("Appointment Date:"), appointmentDatePicker);
		gridPane.addRow(4, new Label("Appointment Time:"), appointmentTimeTextField);
		gridPane.addRow(5, new Label("Duration:"), durationTextField);
		gridPane.addRow(6, new Label("Appointment Type:"), appointmentTypeTextField);
		gridPane.addRow(7, new Label("Payment Status:"), paymentStatusTextField);
		gridPane.addRow(8, new Label("Comments:"), commentsTextField);

		gridPane.add(imageView, 1, 11);
		GridPane.setColumnSpan(imageView, 2);

		// Add Buttons to an HBox
		HBox buttonsHBox = new HBox(searchButton, addButton, deleteButton, updateButton);

		// Create columns for TableView
		// Create TableView
		TableView<Appointment> tableView = new TableView<>();

		TableColumn<Appointment, String> appointmentIDColumn = new TableColumn<>("Appointment ID");
		appointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));

		TableColumn<Appointment, String> patientIDColumn = new TableColumn<>("Patient ID");
		patientIDColumn.setCellValueFactory(new PropertyValueFactory<>("patientID"));

		TableColumn<Appointment, String> doctorIDColumn = new TableColumn<>("Doctor ID");
		doctorIDColumn.setCellValueFactory(new PropertyValueFactory<>("doctorID"));

		TableColumn<Appointment, LocalDate> appointmentDateColumn = new TableColumn<>("Appointment Date");
		appointmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));

		TableColumn<Appointment, String> appointmentTimeColumn = new TableColumn<>("Appointment Time");
		appointmentTimeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentTime"));

		TableColumn<Appointment, String> durationColumn = new TableColumn<>("Duration");
		durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));

		TableColumn<Appointment, String> appointmentTypeColumn = new TableColumn<>("Appointment Type");
		appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));

		TableColumn<Appointment, String> paymentStatusColumn = new TableColumn<>("Payment Status");
		paymentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("paymentStatus"));

		TableColumn<Appointment, String> commentsColumn = new TableColumn<>("Comments");
		commentsColumn.setCellValueFactory(new PropertyValueFactory<>("comments"));

		fillAppointmentTableViewFromSQL(tableView);
		// Add columns to the TableView
		tableView.getColumns().addAll(appointmentIDColumn, patientIDColumn, doctorIDColumn, appointmentDateColumn,
				appointmentTimeColumn, durationColumn, appointmentTypeColumn, paymentStatusColumn, commentsColumn);

		double columnWidth = 100;
		appointmentIDColumn.setPrefWidth(columnWidth);
		patientIDColumn.setPrefWidth(columnWidth);
		doctorIDColumn.setPrefWidth(columnWidth);
		appointmentDateColumn.setPrefWidth(columnWidth);
		appointmentTimeColumn.setPrefWidth(columnWidth);
		durationColumn.setPrefWidth(columnWidth);
		appointmentTypeColumn.setPrefWidth(columnWidth);
		paymentStatusColumn.setPrefWidth(columnWidth);
		commentsColumn.setPrefWidth(columnWidth);

		HBox mainHBox = new HBox(gridPane, tableView);
		mainHBox.setSpacing(40);
		mainHBox.setPadding(new Insets(20, 0, 0, 0));
		buttonsHBox.setSpacing(40);
		buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);

		// Add everything to a VBox with a gap between mainHBox and buttonsHBox
		VBox rootVBox = new VBox(mainHBox, buttonsHBox);
		rootVBox.setSpacing(80);
		rootVBox.setPadding(new Insets(0, 0, 10, 0));

		Scene scene = new Scene(rootVBox, 1400, 750);
		primaryStage.setTitle("Appotiment System");

		rootVBox.setStyle("-fx-background-color: #CDEED8;"); 

		String buttonColor = "#2ECC71"; // أخضر فاتح

		searchButton.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 15px;");
		addButton.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 15px;");
		deleteButton.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 15px;");
		updateButton.setStyle("-fx-background-color: " + buttonColor + "; -fx-background-radius: 15px;");
		
		addButton.setOnAction(event -> {
			String appointmentID = appointmentIDTextField.getText();
			String patientID = patientIDTextField.getText();
			String doctorID = doctorIDTextField.getText();
			LocalDate appointmentDate = appointmentDatePicker.getValue();
			String appointmentTimeStr = appointmentTimeTextField.getText();
			String durationStr = durationTextField.getText();
			String appointmentType = appointmentTypeTextField.getText();
			String paymentStatus = paymentStatusTextField.getText();
			String comments = commentsTextField.getText();

			try {
				Time appointmentTime = Time.valueOf(appointmentTimeStr);
				Time duration = Time.valueOf(durationStr);

				Appointment newAppointment = new Appointment(appointmentID, patientID, doctorID, appointmentDate,
						appointmentTime, duration, appointmentType, paymentStatus, comments);
				insertAppointmentIntoDatabase(newAppointment);

				tableData.add(newAppointment);
				tableView.setItems(tableData);
			} catch (IllegalArgumentException e) {
				showAlert("Error", "Invalid time format for Appointment Time or Duration.");
			}
		});

		// --------------------------------------------------------------

		searchButton.setOnAction(event -> {
			// Create a TextInputDialog to prompt the user for Appointment ID
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search by Appointment ID");
			dialog.setHeaderText(null);
			dialog.setContentText("Please enter Appointment ID:");

			// Show the dialog and wait for the user's input
			Optional<String> result = dialog.showAndWait();

			// Check if the user entered a value
			result.ifPresent(searchTerm -> {
				// Search for the Appointment with matching Appointment ID
				boolean found = false;

				for (Appointment appointment : tableData) {
					if (appointment.getAppointmentID().equals(searchTerm)) {
						found = true;
						break;
					}
				}

				if (found) {
					// Display a message if Appointment ID is found
					showConfirmationDialog("Success", "The Appointment ID is found.");
				} else {
					// Display a message if Appointment ID is not found
					showAlert("Appointment ID not found", "The provided Appointment ID was not found.");
				}
			});
		});

		// --------------------------------------------------------------

		deleteButton.setOnAction(event -> {
			Appointment selectedAppointment = tableView.getSelectionModel().getSelectedItem();

			if (selectedAppointment != null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Delete");
				alert.setHeaderText("Delete Confirmation");
				alert.setContentText("Are you sure you want to delete the selected appointment?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					// Remove from the TableView
					tableData.remove(selectedAppointment);

					// Remove from the Database
					deleteAppointmentFromDatabase(selectedAppointment);

					showConfirmationDialog("Appointment Deleted", "Selected appointment has been deleted.");
				}
			} else {
				showInformationDialog("No Selection", "Please select an appointment to delete.");
			}
		});
		// --------------------------------------------------------------
		updateButton.setOnAction(event -> {
			// Check if a row is selected
			Appointment selectedAppointment = tableView.getSelectionModel().getSelectedItem();

			if (selectedAppointment != null) {
				// Create a dialog to prompt the user for updated data
				Dialog<Appointment> dialog = new Dialog<>();
				dialog.setTitle("Update Appointment");
				dialog.setHeaderText("Enter updated appointment data:");

				// Set the button types (OK and Cancel)
				ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);
				TextField updatedAppointmentIDTextField = new TextField();
				TextField updatedPatientIDTextField = new TextField();
				TextField updatedDoctorIDTextField = new TextField();
				DatePicker updatedAppointmentDatePicker = new DatePicker();
				TextField updatedAppointmentTimeTextField = new TextField();
				TextField updatedDurationTextField = new TextField();
				TextField updatedAppointmentTypeTextField = new TextField();
				TextField updatedPaymentStatusTextField = new TextField();
				TextField updatedCommentsTextField = new TextField();

				GridPane updateGrid = new GridPane();
				updateGrid.setHgap(10); // Horizontal gap
				updateGrid.setVgap(10);
				updateGrid.add(new Label("Appointment ID:"), 0, 0);
				updateGrid.add(updatedAppointmentIDTextField, 1, 0);
				updateGrid.add(new Label("Patient ID:"), 0, 1);
				updateGrid.add(updatedPatientIDTextField, 1, 1);
				updateGrid.add(new Label("Doctor ID:"), 0, 2);
				updateGrid.add(updatedDoctorIDTextField, 1, 2);
				updateGrid.add(new Label("Appointment Date:"), 0, 3);
				updateGrid.add(updatedAppointmentDatePicker, 1, 3);
				updateGrid.add(new Label("Appointment Time:"), 0, 4);
				updateGrid.add(updatedAppointmentTimeTextField, 1, 4);
				updateGrid.add(new Label("Duration:"), 0, 5);
				updateGrid.add(updatedDurationTextField, 1, 5);
				updateGrid.add(new Label("Appointment Type:"), 0, 6);
				updateGrid.add(updatedAppointmentTypeTextField, 1, 6);
				updateGrid.add(new Label("Payment Status:"), 0, 7);
				updateGrid.add(updatedPaymentStatusTextField, 1, 7);
				updateGrid.add(new Label("Comments:"), 0, 8);
				updateGrid.add(updatedCommentsTextField, 1, 8);
				dialog.getDialogPane().setContent(updateGrid);

				// Populate the dialog with current values
				updatedAppointmentIDTextField.setText(selectedAppointment.getAppointmentID());
				updatedPatientIDTextField.setText(selectedAppointment.getPatientID());
				updatedDoctorIDTextField.setText(selectedAppointment.getDoctorID());
				updatedAppointmentDatePicker.setValue(selectedAppointment.getAppointmentDate());
				updatedAppointmentTimeTextField.setText(selectedAppointment.getAppointmentTime().toString());
				updatedDurationTextField.setText(selectedAppointment.getDuration().toString());
				updatedAppointmentTypeTextField.setText(selectedAppointment.getAppointmentType());
				updatedPaymentStatusTextField.setText(selectedAppointment.getPaymentStatus());
				updatedCommentsTextField.setText(selectedAppointment.getComments());

				dialog.setResultConverter(dialogButton -> {
					if (dialogButton == updateButtonType) {
						try {
							// Convert String to Time
							Time updatedAppointmentTime = Time.valueOf(updatedAppointmentTimeTextField.getText());
							Time updatedDuration = Time.valueOf(updatedDurationTextField.getText());

							return new Appointment(updatedAppointmentIDTextField.getText(),
									updatedPatientIDTextField.getText(), updatedDoctorIDTextField.getText(),
									updatedAppointmentDatePicker.getValue(), updatedAppointmentTime, updatedDuration,
									updatedAppointmentTypeTextField.getText(), updatedPaymentStatusTextField.getText(),
									updatedCommentsTextField.getText());
						} catch (IllegalArgumentException e) {
							showAlert("Error", "Invalid time format for Appointment Time or Duration.");
							return null;
						}
					}
					return null;
				});

				// Show the dialog and wait for the user's input
				Optional<Appointment> result = dialog.showAndWait();

				result.ifPresent(updatedData -> {
					// Update the selected Appointment with the new values
					selectedAppointment.setPatientID(updatedData.getPatientID());
					selectedAppointment.setDoctorID(updatedData.getDoctorID());
					selectedAppointment.setAppointmentDate(updatedData.getAppointmentDate());
					selectedAppointment.setAppointmentTime(updatedData.getAppointmentTime());
					selectedAppointment.setDuration(updatedData.getDuration());
					selectedAppointment.setAppointmentType(updatedData.getAppointmentType());
					selectedAppointment.setPaymentStatus(updatedData.getPaymentStatus());
					selectedAppointment.setComments(updatedData.getComments());

					// Update in the Database
					updateAppointmentInDatabase(selectedAppointment);

					// Refresh the TableView
					tableView.refresh();

					// Show a confirmation message
					showConfirmationDialog("Update Successful", "Appointment data has been updated.");
				});
			} else {
				// Show a warning if no row is selected
				showAlert("No Selection", "Please select an appointment to update.");
			}
		});

		primaryStage.setScene(scene);

		// Show the Stage
		primaryStage.show();
	}
	// Rest of the methods (showConfirmationDialog, showAlert,
	// showInformationDialog) remain unchanged

	private void showConfirmationDialog(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// Method to display an alert dialog with an error message
	private void showAlert(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	// Method to display an information dialog with a message
	private void showInformationDialog(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void fillAppointmentTableViewFromSQL(TableView<Appointment> table) {

		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712");
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM Appointment ")) {

			while (resultSet.next()) {
				String AppointmentID = resultSet.getString("appointmentID");
				String PatientID = resultSet.getString("patientID");
				String DoctorID = resultSet.getString("doctorID");
				LocalDate AppointmentDate = resultSet.getDate("appointmentDate").toLocalDate();
				Time AppointmentTime = resultSet.getTime("appointmentTime");
				Time Duration = resultSet.getTime("duration");
				String AppointmentType = resultSet.getString("appointmentType");
				String PaymentStatus = resultSet.getString("paymentStatus");
				String Comments = resultSet.getString("comments");

				Appointment appointment = new Appointment(AppointmentID, PatientID, DoctorID, AppointmentDate,
						AppointmentTime, Duration, AppointmentType, PaymentStatus, Comments);

				tableData.add(appointment);
			}

			// Move this line outside the while loop
			table.setItems(tableData);

		} catch (SQLException e) {
			e.printStackTrace(); // Log the exception or handle it based on your application's requirements
			showAlert("Error", "Failed to retrieve appointment data from the database.");
		}
	}

	private void insertAppointmentIntoDatabase(Appointment appointment) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712");
				PreparedStatement preparedStatement = con
						.prepareStatement("INSERT INTO Appointment VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

			preparedStatement.setString(1, appointment.getAppointmentID());
			preparedStatement.setString(2, appointment.getPatientID());
			preparedStatement.setString(3, appointment.getDoctorID());
			preparedStatement.setDate(4, Date.valueOf(appointment.getAppointmentDate()));
			preparedStatement.setTime(5, appointment.getAppointmentTime());
			preparedStatement.setTime(6, appointment.getDuration());
			preparedStatement.setString(7, appointment.getAppointmentType());
			preparedStatement.setString(8, appointment.getPaymentStatus());
			preparedStatement.setString(9, appointment.getComments());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			showAlert("Error", "Failed to insert appointment data into the database.");
		}
	}

	private void deleteAppointmentFromDatabase(Appointment appointment) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712")) {
			try (PreparedStatement preparedStatement = con
					.prepareStatement("DELETE FROM Appointment WHERE appointmentID = ?")) {
				preparedStatement.setString(1, appointment.getAppointmentID());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			showAlert("Error", "Failed to delete appointment data from the database.");
		}
	}

	private void updateAppointmentInDatabase(Appointment appointment) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712")) {
			try (PreparedStatement preparedStatement = con.prepareStatement(
					"UPDATE Appointment SET patientID = ?, doctorID = ?, appointmentDate = ?, appointmentTime = ?, duration = ?, appointmentType = ?, paymentStatus = ?, comments = ? WHERE appointmentID = ?")) {
				preparedStatement.setString(1, appointment.getPatientID());
				preparedStatement.setString(2, appointment.getDoctorID());
				preparedStatement.setDate(3, Date.valueOf(appointment.getAppointmentDate()));
				preparedStatement.setTime(4, appointment.getAppointmentTime());
				preparedStatement.setTime(5, appointment.getDuration());
				preparedStatement.setString(6, appointment.getAppointmentType());
				preparedStatement.setString(7, appointment.getPaymentStatus());
				preparedStatement.setString(8, appointment.getComments());
				preparedStatement.setString(9, appointment.getAppointmentID());

				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			showAlert("Error", "Failed to update appointment data in the database.");
		}
	}

}
