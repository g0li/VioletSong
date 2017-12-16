package forevtechnologies.alegriauiux.models;
import forevtechnologies.alegriauiux.models.Events;

/**
 * Created by jojosexbomb69 on 16/12/17.
 */

public enum DayWiseEvents {
    TD("3D Printing"),
    WD("Web Designing"),
    AA("Arduino Advance"),
    IOT("IOT"),
    PY("Python"),
    DP("Drone Piloting"),
    KT("Kotlin"),
    FG("Firebase by Google"),
    TW("Theatre Workshop"),
    CG("Calligraphy"),
    ZM("Zumba"),
    SA("Stippling Art"),
    DJW("DJ Workshop"),
    SSA("Salsa"),
    RCB("Rubiks Cube"),
    TT("Tutting"),
    PH("Photography Workshop"),
    RS("Rifle Shooting");




    private final String event;

    DayWiseEvents(String event) {this.event=event;}

    public String getEvents(){return event;}
}
