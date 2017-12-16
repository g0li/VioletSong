package forevtechnologies.alegriauiux.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineModel implements Parcelable {

    public String mMessage;  //event name
    public String mDate;     //event date and time
    public OrderStatus mStatus; //icon status
    public String mLocation; //location of event
    public String mTime;        //Written time for event
    public int mPicture;     //id of drawable

    public TimeLineModel() {
        this.mMessage="";
        this.mDate="";
        this.mStatus=OrderStatus.ACTIVE;
        this.mLocation="";
        this.mTime="";
        this.mPicture=0;
    }

    public TimeLineModel(String mMessage, String mDate, String mLocation, OrderStatus mStatus) {
        this.mMessage = mMessage;
        this.mDate = mDate;
        this.mLocation=mLocation;
        this.mStatus = mStatus;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        this.mMessage = message;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        this.mDate = date;
    }

    public OrderStatus getStatus() {
        return mStatus;
    }

    public void setStatus(OrderStatus mStatus) {
        this.mStatus = mStatus;
    }
    public void setLocation(String mLocation){
        this.mLocation=mLocation;
    }

    public String getLocation(){
        return this.mLocation;
    }

    public void setPicture(int IDofPicture){
        this.mPicture=IDofPicture;
    }

    public int getPicture(){
        return this.mPicture;
    }

    public void setTime(String mTime){
        this.mTime=mTime;
    }

    public String getTime(){
        return this.mTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mMessage);
        dest.writeString(this.mDate);
        dest.writeInt(this.mStatus == null ? -1 : this.mStatus.ordinal());
    }

    protected TimeLineModel(Parcel in) {
        this.mMessage = in.readString();
        this.mDate = in.readString();
        int tmpMStatus = in.readInt();
        this.mStatus = tmpMStatus == -1 ? null : OrderStatus.values()[tmpMStatus];
    }

    public static final Creator<TimeLineModel> CREATOR = new Creator<TimeLineModel>() {
        @Override
        public TimeLineModel createFromParcel(Parcel source) {
            return new TimeLineModel(source);
        }

        @Override
        public TimeLineModel[] newArray(int size) {
            return new TimeLineModel[size];
        }
    };
}
