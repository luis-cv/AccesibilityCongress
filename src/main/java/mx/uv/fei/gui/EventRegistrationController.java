package mx.uv.fei.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import mx.uv.fei.logic.Event;
import java.sql.SQLException;

public class EventRegistrationController {
    @FXML
    private Label labelEventName;
    @FXML
    private Label labelLecturerName;
    @FXML
    private Label labelEventType;
    @FXML
    private Label labelEventPlace;
    @FXML
    private Label labelEventDate;
    @FXML
    private Label labelEventStartTime;
    @FXML
    private Label labelEventDuration;
    @FXML
    private TextField textFieldEventName;
    @FXML
    private TextField textFieldLecturerName;
    private final ObservableList<String> listEventTypeOptions = FXCollections.observableArrayList("Taller", "Plática");
    @FXML
    private ComboBox<String> comboBoxEventType;
    @FXML
    private TextField textFieldEventPlace;
    private final ObservableList<String> listEventStartTimeOptions = FXCollections.observableArrayList("9:00", "10:30","11:30","12:30");
    @FXML
    private ComboBox<String> comboBoxEventStartTime;
    @FXML
    private TextField textFieldEventDuration;
    @FXML
    private DatePicker datePickerEventDate;
    @FXML
    private Button buttonRegisterEvent;
    @FXML
    private Button buttonCancelRegister;

    @FXML
    private void initialize() {
        comboBoxEventType.setItems(listEventTypeOptions);
        comboBoxEventStartTime.setItems(listEventStartTimeOptions);
    }

    public void clearForm(){
        textFieldEventName.clear();
        textFieldLecturerName.clear();
        textFieldEventDuration.clear();
        textFieldEventPlace.clear();
        datePickerEventDate.setValue(null);
        comboBoxEventStartTime.setValue(null);
        comboBoxEventType.setValue(null);
    }
    public boolean emptyForm() {
        return textFieldEventName.getText().isBlank() || textFieldLecturerName.getText().isBlank() || comboBoxEventType.getSelectionModel().isEmpty() || textFieldEventPlace.getText().isBlank() || comboBoxEventStartTime.getSelectionModel().isEmpty() || (datePickerEventDate.getValue()) == null || textFieldEventDuration.getText().isBlank();
    }
    
    public void showAlert(int formStatus) {
        Alert alertWindow;
        switch (formStatus) {
            case 1 -> {
                alertWindow = new Alert(Alert.AlertType.CONFIRMATION);
                alertWindow.setTitle("Confirmación");
                alertWindow.setHeaderText("Evento registrado.");
                alertWindow.showAndWait();
            } case 2 -> {
                alertWindow = new Alert(Alert.AlertType.INFORMATION);
                alertWindow.setTitle("Atención");
                alertWindow.setHeaderText("Se deben llenar todos los campos");
                alertWindow.showAndWait();
            } case 3 -> {
                alertWindow = new Alert(Alert.AlertType.INFORMATION);
                alertWindow.setTitle("Error");
                alertWindow.setHeaderText("Error al registrar el evento");
                alertWindow.showAndWait();
            }
        }
    }

    @FXML
    public void registerEvent() {
        if (emptyForm()) {
            showAlert(2);
        } else {
            try {
                Event eventObject = new Event();
                eventObject.setEventName(textFieldEventName.getText());
                eventObject.setLecturerName(textFieldLecturerName.getText());
                eventObject.setDuration(Integer.parseInt(textFieldEventDuration.getText()));
                eventObject.setPlace(textFieldEventPlace.getText());
                eventObject.setDate(String.valueOf(datePickerEventDate.getValue()));
                eventObject.setTime(String.valueOf(comboBoxEventStartTime.getValue()));
                eventObject.setEventType(String.valueOf(comboBoxEventType.getValue()));
                eventObject.registerEvent();

                showAlert(1);
                clearForm();

            } catch (SQLException exception) {
                showAlert(3);
            }
        }
    }

    @FXML
    public void cancelRegister() {
        Stage stage = (Stage) buttonCancelRegister.getScene().getWindow();
        clearForm();
        stage.close();

    }
}