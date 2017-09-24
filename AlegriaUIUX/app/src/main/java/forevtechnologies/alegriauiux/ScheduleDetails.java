package forevtechnologies.alegriauiux;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created by thisi on 29-06-2017.
 */

public class ScheduleDetails {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public ScheduleDetails(String name, String date, String day, String time, String color, int icon) {
        this.name = name;
        this.date = date;
        this.day = day;
        this.time = time;
        this.color = color;
        this.icon = icon;
    }

    String name,date,day,time,color;
    int icon;
}
