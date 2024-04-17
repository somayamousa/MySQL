package application;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

public class BillApp extends Application {

	private ObservableList<BillingData> tableData = FXCollections.observableArrayList();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws MalformedURLException {

		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
					"1210712");
			Statement st = con.createStatement();
			ResultSet sr = st.executeQuery("SELECT * FROM billing ");
		} catch (Exception ex) {
			System.out.println(ex);
		}

		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setHgap(10);
		gridPane.setVgap(10);

		TextField billingIdTextField = new TextField();
		DatePicker serviceDatePicker = new DatePicker();
		TextField serviceDescriptionTextField = new TextField();
		TextField billedAmountTextField = new TextField();

		String imagePath = "Bill.png";
		File file = new File(imagePath);
		String imageUrl = file.toURI().toURL().toString();
		Image image = new Image(imageUrl);

		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(200);
		imageView.setFitHeight(200);

		Button searchButton = new Button("Search");
		Button addButton = new Button("Add");
		Button deleteButton = new Button("Delete");
		Button updateButton = new Button("Update");

		serviceDatePicker.setPromptText("Select Service Date");

		double buttonWidth = 100;
		double buttonHeight = 50;

		searchButton.setPrefSize(buttonWidth, buttonHeight);
		addButton.setPrefSize(buttonWidth, buttonHeight);
		deleteButton.setPrefSize(buttonWidth, buttonHeight);
		updateButton.setPrefSize(buttonWidth, buttonHeight);

		gridPane.addRow(0, new Label("Billing ID:"), billingIdTextField);
		gridPane.addRow(1, new Label("Service Date:"), serviceDatePicker);
		gridPane.addRow(2, new Label("Service Description:"), serviceDescriptionTextField);
		gridPane.addRow(3, new Label("Billed Amount:"), billedAmountTextField);
		gridPane.add(imageView, 1, 8);
		GridPane.setColumnSpan(imageView, 2);
		HBox buttonsHBox = new HBox(searchButton, addButton, deleteButton, updateButton);

		TableView<BillingData> tableView = new TableView<>();
		TableColumn<BillingData, String> billingIdColumn = new TableColumn<>("Billing ID");
		billingIdColumn.setCellValueFactory(new PropertyValueFactory<>("billingId"));

		TableColumn<BillingData, LocalDate> serviceDateColumn = new TableColumn<>("Service Date");
		serviceDateColumn.setCellValueFactory(new PropertyValueFactory<>("serviceDate"));

		TableColumn<BillingData, String> serviceDescriptionColumn = new TableColumn<>("Service Description");
		serviceDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("serviceDescription"));

		TableColumn<BillingData, String> billedAmountColumn = new TableColumn<>("Billed Amount");
		billedAmountColumn.setCellValueFactory(new PropertyValueFactory<>("billedAmount"));

		fillTableViewFromSQL(tableView);

		tableView.getColumns().addAll(billingIdColumn, serviceDateColumn, serviceDescriptionColumn, billedAmountColumn);

		double columnWidth = 200;
		billingIdColumn.setMinWidth(columnWidth);
		billingIdColumn.setMaxWidth(columnWidth);

		serviceDateColumn.setMinWidth(columnWidth);
		serviceDateColumn.setMaxWidth(columnWidth);

		serviceDescriptionColumn.setMinWidth(columnWidth);
		serviceDescriptionColumn.setMaxWidth(columnWidth);

		billedAmountColumn.setMinWidth(columnWidth);
		billedAmountColumn.setMaxWidth(columnWidth);

		HBox mainHBox = new HBox(gridPane, tableView);
		mainHBox.setSpacing(40);
		mainHBox.setPadding(new Insets(20, 0, 0, 0));
		buttonsHBox.setSpacing(40);
		buttonsHBox.setAlignment(Pos.BOTTOM_CENTER);

		VBox rootVBox = new VBox(mainHBox, buttonsHBox);
		rootVBox.setSpacing(80);
		rootVBox.setPadding(new Insets(0, 0, 10, 0));

		Scene scene = new Scene(rootVBox, 1300, 700);
		primaryStage.setScene(scene);

		primaryStage.setTitle("Billing System");

		rootVBox.setStyle("-fx-background-color: #90EE90;");

		rootVBox.setStyle("-fx-background-color: #90EE90;");
		String buttonColor = "#FFFFCC";

		String buttonShape = "-fx-background-radius: 30px; -fx-border-color: #FFFFCC; -fx-border-radius: 30px; -fx-border-width: 1px;";

		searchButton.setStyle("-fx-background-color: " + buttonColor + "; " + buttonShape);
		addButton.setStyle("-fx-background-color: " + buttonColor + "; " + buttonShape);
		deleteButton.setStyle("-fx-background-color: " + buttonColor + "; " + buttonShape);
		updateButton.setStyle("-fx-background-color: " + buttonColor + "; " + buttonShape);

		addButton.setOnAction(event -> {
			try {
				String billingId = billingIdTextField.getText();
				LocalDate serviceDate = serviceDatePicker.getValue();
				String serviceDescription = serviceDescriptionTextField.getText();
				String billedAmount = billedAmountTextField.getText();

				BillingData newBillingData = new BillingData(billingId, serviceDate, serviceDescription, billedAmount);
				insertDataIntoDatabase(newBillingData);

				tableData.add(newBillingData);
				tableView.setItems(tableData);

				billingIdTextField.clear();
				serviceDatePicker.setValue(null);
				serviceDescriptionTextField.clear();
				billedAmountTextField.clear();
			} catch (IllegalArgumentException e) {
				showAlert("Error", "Invalid data format.");
			}
		});

		deleteButton.setOnAction(event -> {
			BillingData selectedBillingData = tableView.getSelectionModel().getSelectedItem();

			if (selectedBillingData != null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Delete");
				alert.setHeaderText("Delete Confirmation");
				alert.setContentText("Are you sure you want to delete the selected billing data?");

				Optional<ButtonType> result = alert.showAndWait();
				if (result.isPresent() && result.get() == ButtonType.OK) {
					// Remove from the local data model
					tableData.remove(selectedBillingData);

					// Remove from the SQL database
					deleteDataFromDatabase(selectedBillingData);

					showConfirmationDialog("Billing Data Deleted", "Selected billing data has been deleted.");
				}
			} else {
				showInformationDialog("No Selection", "Please select a billing data to delete.");
			}
		});

		updateButton.setOnAction(event -> {
			// Check if a row is selected
			BillingData selectedBillingData = tableView.getSelectionModel().getSelectedItem();

			if (selectedBillingData != null) {
				// Create a dialog to prompt the user for updated data
				Dialog<BillingData> dialog = new Dialog<>();
				dialog.setTitle("Update Billing Data");
				dialog.setHeaderText("Enter updated billing data:");

				// Set the button types (OK and Cancel)
				ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
				dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, ButtonType.CANCEL);

				// Create TextFields and DatePicker for updated data
				TextField updatedBillingIdTextField = new TextField(selectedBillingData.getBillingId());
				DatePicker updatedServiceDatePicker = new DatePicker(selectedBillingData.getServiceDate());
				TextField updatedServiceDescriptionTextField = new TextField(
						selectedBillingData.getServiceDescription());
				TextField updatedBilledAmountTextField = new TextField(selectedBillingData.getBilledAmount());

				GridPane grid = new GridPane();
				grid.setHgap(10); // Horizontal gap
				grid.setVgap(10);
				grid.add(new Label("Billing ID:"), 0, 0);
				grid.add(updatedBillingIdTextField, 1, 0);
				grid.add(new Label("Service Date:"), 0, 1);
				grid.add(updatedServiceDatePicker, 1, 1);
				grid.add(new Label("Service Description:"), 0, 2);
				grid.add(updatedServiceDescriptionTextField, 1, 2);
				grid.add(new Label("Billed Amount:"), 0, 3);
				grid.add(updatedBilledAmountTextField, 1, 3);
				dialog.getDialogPane().setContent(grid);

				dialog.setResultConverter(dialogButton -> {
					if (dialogButton == updateButtonType) {
						try {
							String updatedBillingId = updatedBillingIdTextField.getText();
							LocalDate updatedServiceDate = updatedServiceDatePicker.getValue();
							String updatedServiceDescription = updatedServiceDescriptionTextField.getText();
							String updatedBilledAmount = updatedBilledAmountTextField.getText();

							return new BillingData(updatedBillingId, updatedServiceDate, updatedServiceDescription,
									updatedBilledAmount);
						} catch (DateTimeParseException e) {
							showAlert("Error", "Invalid date format.");
						}
					}
					return null;
				});

				// Show the dialog and wait for the user's input
				Optional<BillingData> result = dialog.showAndWait();

				result.ifPresent(updatedData -> {
					// Update the selected BillingData with the new values
					selectedBillingData.setBillingId(updatedData.getBillingId());
					selectedBillingData.setServiceDate(updatedData.getServiceDate());
					selectedBillingData.setServiceDescription(updatedData.getServiceDescription());
					selectedBillingData.setBilledAmount(updatedData.getBilledAmount());

					// Update the data in the database
					updateBillingDataInDatabase(selectedBillingData);

					// Refresh the TableView
					tableView.refresh();

					// Show a confirmation message
					showConfirmationDialog("Update Successful", "Billing data has been updated.");
				});
			} else {
				// Show a warning if no row is selected
				showAlert("No Selection", "Please select a billing data to update.");
			}
		});

		searchButton.setOnAction(event -> {
			// Create a TextInputDialog to prompt the user for Billing ID
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Search by Billing ID");
			dialog.setHeaderText(null);
			dialog.setContentText("Please enter Billing ID:");

			// Show the dialog and wait for the user's input
			Optional<String> result = dialog.showAndWait();

			// Check if the user entered a value
			result.ifPresent(searchTerm -> {
				// Search for the BillingData with matching Billing ID
				boolean found = false;

				for (BillingData billingData : tableData) {
					if (billingData.matchesBillingId(searchTerm)) {
						found = true;
						break;
					}
				}

				if (found) {
					// Display a message if Billing ID is found
					showConfirmationDialog("Success", "The Billing ID is found.");
				} else {
					// Display a message if Billing ID is not found
					showAlert("Billing ID not found", "The provided Billing ID was not found.");
				}
			});
		});

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void fillTableViewFromSQL(TableView<BillingData> table) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712");
				Statement statement = con.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM billing ")) {

			while (resultSet.next()) {
				int pillingId = resultSet.getInt("pillingId");
				LocalDate serviceDate = resultSet.getDate("servicedate").toLocalDate();
				String serviceDescription = resultSet.getString("servicedescription");
				double billedAmount = resultSet.getDouble("Billedamount");

				BillingData billingData = new BillingData(String.valueOf(pillingId), serviceDate, serviceDescription,
						String.valueOf(billedAmount));
				tableData.add(billingData);
			}

			table.setItems(tableData);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void insertDataIntoDatabase(BillingData billingData) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712")) {
			String sql = "INSERT INTO billing (pillingId, servicedate, servicedescription, Billedamount) VALUES (?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, billingData.getBillingId());
				preparedStatement.setDate(2, Date.valueOf(billingData.getServiceDate()));
				preparedStatement.setString(3, billingData.getServiceDescription());
				preparedStatement.setDouble(4, Double.parseDouble(billingData.getBilledAmount()));
				preparedStatement.executeUpdate();
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
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

	private void showInformationDialog(String title, String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private void deleteDataFromDatabase(BillingData billingData) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712")) {
			// Prepare the SQL statement
			String sql = "DELETE FROM billing WHERE pillingId = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				// Set the parameter for the WHERE clause
				preparedStatement.setInt(1, Integer.parseInt(billingData.getBillingId()));

				// Execute the update
				preparedStatement.executeUpdate();
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
		}
	}

	private void updateBillingDataInDatabase(BillingData billingData) {
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/clinicmanagementsystem", "root",
				"1210712")) {
			String sql = "UPDATE billing SET pillingId = ?, servicedate = ?, servicedescription = ?, Billedamount = ? WHERE pillingId = ?";
			try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
				preparedStatement.setString(1, billingData.getBillingId());
				preparedStatement.setDate(2, Date.valueOf(billingData.getServiceDate()));
				preparedStatement.setString(3, billingData.getServiceDescription());
				preparedStatement.setDouble(4, Double.parseDouble(billingData.getBilledAmount()));
				preparedStatement.setString(5, billingData.getBillingId());

				preparedStatement.executeUpdate();
			}
		} catch (SQLException | NumberFormatException e) {
			e.printStackTrace();
			showAlert("Error", "Failed to update billing data in the database.");
		}
	}

}
