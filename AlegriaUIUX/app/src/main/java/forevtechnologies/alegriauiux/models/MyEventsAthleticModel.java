package forevtechnologies.alegriauiux.models;

public class MyEventsAthleticModel {
    private String location;
    private String events;
    private int day;

    public MyEventsAthleticModel(String location, String events, int day) {
        this.location=location;
        this.events=events;
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public String getEvents() {
        return events;
    }

    public int getScore() {
        return day;
    }
}
