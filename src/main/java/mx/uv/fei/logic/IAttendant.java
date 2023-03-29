package mx.uv.fei.logic;

import java.sql.SQLException;

public interface IAttendant {
    int addAttendant(Attendant attendant) throws SQLException;
}
