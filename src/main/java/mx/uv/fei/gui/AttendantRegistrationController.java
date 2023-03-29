package mx.uv.fei.gui;

import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import mx.uv.fei.logic.Attendant;
import mx.uv.fei.logic.Event;
import mx.uv.fei.logic.EventDAO;
import mx.uv.fei.logic.AttendantDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AttendantRegistrationController {
    @FXML
    private Label labelAvailableEvents;
    @FXML
    private Label labelAttendantName;
    @FXML
    private Label labelAttendantLastName;
    @FXML
    private Label labelAttendantEmail;
    @FXML
    private Label labelEventID;
    @FXML
    private TableView<Event> tableViewEvents;
    @FXML
    private TableColumn<Event, String> tableColumnEventID;
    @FXML
    private TableColumn<Event,String> tableColumnEventName;
    @FXML
    private TableColumn<Event, String> tableColumnLecturerName;
    @FXML
    private TableColumn<Event, Integer> tableColumnEventDuration;
    @FXML
    private TableColumn<Event, String> tableColumnEventPlace;
    @FXML
    private TableColumn<Event, String> tableColumnEventDate;
    @FXML
    private TableColumn<Event, String> tableColumnEventTime;
    @FXML
    private TableColumn<Event, String> tableColumnEventType;
    @FXML
    private TextField textFieldAttendantName;
    @FXML
    private TextField textFieldAttendantLastName;
    @FXML
    private TextField textFieldAttendantEmail;
    @FXML
    private TextField textFieldEventID;
    @FXML
    private Button buttonCancelRegisterAssist;
    @FXML
    private Button buttonRegisterAssist;

    private void fillTable() throws SQLException{
        EventDAO eventDAO = new EventDAO();
        ObservableList<Event> listEvents = FXCollections.observableArrayList();
        ArrayList<Event> eventsDB = eventDAO.getAllEvents();

        listEvents.addAll(eventsDB);
        tableViewEvents.setItems(listEvents);
    }

    public void initialize() throws SQLException {
        fillTable();
    }

    public void clearForm() {
        textFieldAttendantName.clear();
        textFieldAttendantLastName.clear();
        textFieldAttendantEmail.clear();
        textFieldEventID.clear();
    }

    public boolean emptyForm(){
        return textFieldAttendantName.getText().isBlank() || textFieldAttendantLastName.getText().isBlank() || textFieldAttendantEmail.getText().isBlank() || textFieldEventID.getText().isBlank();
    }

    public void showAlert(String formStatus){
        Alert alertWindow;
        switch (formStatus) {
            case "correct" -> {
                alertWindow = new Alert(Alert.AlertType.CONFIRMATION);
                alertWindow.setTitle("Confirmación");
                alertWindow.setHeaderText("Asistencia registrada"); alertWindow.showAndWait();
            } case "empty" -> {
                alertWindow = new Alert(Alert.AlertType.INFORMATION);
                alertWindow.setTitle("Atención");
                alertWindow.setHeaderText("Se deben llenar todos los campos");
                alertWindow.showAndWait();
            }
            case "error" -> {
                alertWindow = new Alert(Alert.AlertType.ERROR);
                alertWindow.setTitle("Error");
                alertWindow.setHeaderText("Error al registrar la asistencia");
                alertWindow.showAndWait();
            }
        }
    }

    @FXML
    public void registerAssist() {
        String formFlag;

        if (emptyForm()) {
            formFlag = "empty";
            showAlert(formFlag);
        } else {
            Attendant attendant = new Attendant();
            attendant.setName(textFieldAttendantLastName.getText());
            attendant.setLastname(textFieldAttendantLastName.getText());
            attendant.setEmail(textFieldAttendantEmail.getText());
            attendant.setEventAssist(textFieldEventID.getText());

            AttendantDAO attendantDAO = new AttendantDAO();

            try {
                if (attendantDAO.addAttendant(attendant) > 0) {
                    formFlag = "correct";
                    showAlert(formFlag);
                    clearForm();
                } else {
                    formFlag = "error";
                    showAlert(formFlag);
                }
            } catch (SQLException exception) {
                formFlag = "error";
                showAlert(formFlag);
            }
        }
    }

    @FXML
    public void cancelRegisterAssist() {
        Stage stage = (Stage) buttonCancelRegisterAssist.getScene().getWindow();
        clearForm();
        stage.close();
    }

}
