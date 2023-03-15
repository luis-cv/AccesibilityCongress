package mx.uv.fei.logic;

import mx.uv.fei.dataaccess.DataBaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class EventDAO {
    public static ArrayList<Event> consultEvents() throws SQLException{
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
