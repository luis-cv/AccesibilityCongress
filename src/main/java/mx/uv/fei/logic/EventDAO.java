package mx.uv.fei.logic;

import mx.uv.fei.dataaccess.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class EventDAO implements IEvent {

    public int addEvent(Event event) throws SQLException{
        int result;
        String sqlQuery = "INSERT INTO Events (EventName, LecturerName, Duration, Place, EventDate, EventTime, EventType) VALUES (?,?,?,?,?,?,?)";

        DataBaseManager dataBaseManagerManager = new DataBaseManager();
        Connection connection = dataBaseManagerManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        preparedStatement.setString(1,event.getEventName());
        preparedStatement.setString(2, event.getLecturerName());
        preparedStatement.setInt(3,event.getDuration());
        preparedStatement.setString(4,event.getPlace());
        preparedStatement.setString(5,event.getDate());
        preparedStatement.setString(6, event.getTime());
        preparedStatement.setString(7, event.getEventType());

        result = preparedStatement.executeUpdate();
        dataBaseManagerManager.closeConnection();

        return result;
    }
    public ArrayList<Event> getAllEvents() throws SQLException{
        ArrayList<Event> listEventsDB;
        String sqlQuery = "SELECT * FROM Events;";

        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

        ResultSet results = preparedStatement.executeQuery();

        listEventsDB = new ArrayList<>();

        while (results.next()) {
            Event objectEvent = new Event();

            objectEvent.setIdEvent(results.getInt("IdEvent"));
            objectEvent.setEventName(results.getString("EventName"));
            objectEvent.setLecturerName(results.getString("LecturerName"));
            objectEvent.setDuration(results.getInt("Duration"));
            objectEvent.setPlace(results.getString("Place"));
            objectEvent.setDate(String.valueOf(results.getDate("EventDate")));
            objectEvent.setTime(String.valueOf(results.getTime("EventTime")));
            objectEvent.setEventType(results.getString("EventType"));

            listEventsDB.add(objectEvent);
        }

        return listEventsDB;
    }
}
