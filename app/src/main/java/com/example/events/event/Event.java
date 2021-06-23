package com.example.events.event;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event implements Parcelable {
    private int ID;
    private String name;
    private String date;

    public Event() {
    }

    public Event(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Event(int ID, String name, String date) {
        this.ID = ID;
        this.name = name;
        this.date = date;
    }

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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(name);
        dest.writeString(date);
    }
    Event(Parcel parcel) {
        this.ID = parcel.readInt();
        this.name = parcel.readString();
        this.date = parcel.readString();

    }
    public static final Parcelable.Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
