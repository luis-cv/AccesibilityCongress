package mx.uv.fei.logic;

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

    public int getIdEvent() {
        return idEvent;
    }

    public String getEventName() {
        return  eventName;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public int getDuration() {
        return duration;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getEventType() {
        return eventType;
    }
}