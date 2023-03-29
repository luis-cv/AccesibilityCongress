package mx.uv.fei.logic;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEvent {
    int addEvent(Event event) throws SQLException;

    ArrayList<Event> getAllEvents() throws SQLException;
}
