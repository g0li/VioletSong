package forevtechnologies.alegriauiux.model;

import android.net.Uri;

/**
 * Created by thisi on 25-06-2017.
 */

public class Person {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    Uri photo;

    public Uri getPhoto() {
        return photo;
    }

    public void setPhoto(Uri photo) {
        this.photo = photo;
    }

    String name,email;
}
