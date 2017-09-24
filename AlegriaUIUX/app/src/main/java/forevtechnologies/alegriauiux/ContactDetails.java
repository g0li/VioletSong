package forevtechnologies.alegriauiux;

/**
 * Created by thisi on 13-05-2017.
 */

public class ContactDetails {
    private String Name;
    private String Event;
    private String Number;
    private String Team;

    public String getTeam() {
        return Team;
    }

    public void setTeam(String team) {
        Team = team;
    }

    public ContactDetails(){}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public ContactDetails(String name, String event, String number,String team) {
        Name = name;
        Event = event;
        Number = number;
        Team=team;
    }
}
