package mx.uv.fei.logic;

public class Attendant {
    private String attendantName;
    private String attendantLastName;
    private String email;
    private String eventAssist;

    public Attendant(){}

    public void setName(String attendantName) {
        this.attendantName = attendantName;
    }

    public void setLastname(String attendantLastName) {
        this.attendantLastName = attendantLastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEventAssist(String eventAssist) {
        this.eventAssist = eventAssist;
    }

    public String getAttendantName() {
        return attendantName;
    }

    public String getLastname() {
        return attendantLastName;
    }

    public String getEmail() {
        return email;
    }

    public String getEventAssist() {
        return eventAssist;
    }
}
