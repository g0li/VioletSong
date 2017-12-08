package forevtechnologies.alegriauiux.models;

import java.net.URI;

public class AthleticModel {
    private String location;
    private Events events;
    private int day;

    public AthleticModel(String location,Events events, int day) {
        this.location=location;
        this.events=events;
        this.day = day;
    }

    public String getLocation() {
        return location;
    }

    public Events getEvents() {
        return events;
    }

    public int getScore() {
        return day;
    }
}
