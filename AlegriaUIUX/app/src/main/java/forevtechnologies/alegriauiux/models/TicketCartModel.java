package forevtechnologies.alegriauiux.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by jojosexbomb69 on 6/12/17.
 */

public class TicketCartModel implements Parcelable {
    public String name;
    public int price;


    public TicketCartModel(String name, int price) {
        this.name = name;
        this.price = price;

    }

    protected TicketCartModel(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    public static final Creator<TicketCartModel> CREATOR = new Creator<TicketCartModel>() {
        @Override
        public TicketCartModel createFromParcel(Parcel in) {
            return new TicketCartModel(in);
        }

        @Override
        public TicketCartModel[] newArray(int size) {
            return new TicketCartModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(name);
        parcel.writeInt(price);
    }
}
