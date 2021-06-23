package com.example.events.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.events.MainActivity;
import com.example.events.event.Event;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="EventManager";
    private static final String TABLE_EVENT="events";
    private static final String KEY_ID="id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);

        // Create tables again
        onCreate(db);
    }
    public void addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName()); // Contact Name
        values.put(KEY_DATE, event.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE)); // Contact Phone

        // Inserting Row
        db.insert(TABLE_EVENT, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    Event getEvent(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EVENT, new String[] { KEY_ID,
                        KEY_NAME, KEY_DATE }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event= new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), (LocalDate.parse(cursor.getString(2), DateTimeFormatter.ISO_LOCAL_DATE)));
        return event;
    }


    public ArrayList<Event> getAllEvents() {
        ArrayList<Event> eventList = new ArrayList<Event>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_EVENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setName(cursor.getString(1));
                event.setDate((LocalDate.parse(cursor.getString(2),DateTimeFormatter.ISO_LOCAL_DATE)));

                eventList.add(event);
            } while (cursor.moveToNext());
        }


        return eventList;
    }
    public int updateEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, event.getName());
        values.put(KEY_DATE, event.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

        // updating row
        return db.update(TABLE_EVENT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
    }
    public void deleteEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENT, KEY_ID + " = ?",
                new String[] { String.valueOf(event.getID()) });
        db.close();
    }
    public int getEventCount() {
        String countQuery = "SELECT  * FROM " + TABLE_EVENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}


