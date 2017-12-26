package forevtechnologies.alegriauiux.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by goli on 30/11/17.
 */

public class CartModel implements Parcelable {
    String name;

    public CartModel(String name){
        this.name=name;
    }

    protected CartModel(Parcel in) {
        name = in.readString();
    }

    public static final Creator<CartModel> CREATOR = new Creator<CartModel>() {
        @Override
        public CartModel createFromParcel(Parcel in) {
            return new CartModel(in);
        }

        @Override
        public CartModel[] newArray(int size) {
            return new CartModel[size];
        }
    };

    public String getName() {

        return this.name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
    }
}
