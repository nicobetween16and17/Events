package com.example.events.frags;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.events.R;
import com.example.events.event.Event;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class AddEventFrag extends Fragment {
    private EditText eventName;
    private DatePicker eventDatePicker;
    private Button createEvent;
    public static AddEventFrag newInstance(){
        AddEventFrag frag = new AddEventFrag();
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){
        View v = inflater.inflate(R.layout.frag_add_btn_event,container,false);
        eventDatePicker=v.findViewById(R.id.datepicker);
        eventName=v.findViewById(R.id.name);
        createEvent=v.findViewById(R.id.createEvent);
        createEvent.setOnClickListener(view ->{
            createEvent();
        });

        return v;
    }
    public void createEvent(){
        int year=eventDatePicker.getYear();
        int month=eventDatePicker.getMonth();
        int day=eventDatePicker.getDayOfMonth();
        Event event = new Event(eventName.getText().toString(),new Date(year,month,day));

    }


}
