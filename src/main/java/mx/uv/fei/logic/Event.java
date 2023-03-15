package mx.uv.fei.logic;

import mx.uv.fei.dataaccess.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class Event {
    private int idEvent;
    private String eventName;
    private String lecturerName;
    private int duration;
    private String place;
    private String date;
    private String time;
    private String eventType;

    public Event() {}
    public Event(String eventName, String lecturerName, int duration, String place, String date, String time, String eventType) {
        this.eventName = eventName;
        this.lecturerName = lecturerName;
        this.duration = duration;
        this.place = place;
        this.date = date;
        this.time = time;
        this.eventType = eventType;
    }

    public Event(int idEvent, String eventName, String lecturerName, int duration, String place, String eventDate, String eventTime, String eventType) {

    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int registerEvent() throws SQLException {
        int result;
        String sqlQuery = "INSERT INTO Events (EventName, LecturerName, Duration, Place, EventDate, EventTime, EventType) VALUES (?,?,?,?,?,?,?)";

        DataBaseManager dataBaseManagerManager = new DataBaseManager();
        Connection connection = dataBaseManagerManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1,eventName);
        preparedStatement.setString(2,lecturerName);
        preparedStatement.setInt(3,duration);
        preparedStatement.setString(4,place);
        preparedStatement.setString(5,date);
        preparedStatement.setString(6,time);
        preparedStatement.setString(7,eventType);

        result = preparedStatement.executeUpdate();
        connection.close();

        return result;
    }
}