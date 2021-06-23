package com.example.events.event;

import android.os.Parcel;
import android.os.Parcelable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Event implements Parcelable {
    private int ID;
    private String name;
    private LocalDate date;

    public Event() {
    }

    public Event(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Event(int ID, String name, LocalDate date) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    @Override
    public String toString() {
        return name+ " "+date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(name);
        dest.writeString(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }
    Event(Parcel parcel) {
        this.ID = parcel.readInt();
        this.name = parcel.readString();
        this.date = LocalDate.parse(parcel.readString(), DateTimeFormatter.ISO_LOCAL_DATE);

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
