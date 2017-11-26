package forevtechnologies.alegriauiux.models;

/**
 * Created by thisi on 05-09-2017.
 */

public enum Location {
    AL("Artificial Lawn"),
    QD("Quad"),
    MPT("Multi-purpose turf"),
    CL3("P-202(L-III)");

    public String getLocation() {
        return location;
    }

    private final String location;

    Location(String location) {
        this.location = location;
    }
}
