package com.example.events.frags;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.events.MainActivity;
import com.example.events.R;
import com.example.events.event.Event;
import com.example.events.helper.DatabaseHandler;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class AddEventFrag extends Fragment implements MainActivity.OnCreateEvent {
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
            Toast.makeText(getActivity(), "Creation of event named "+ eventName.getText().toString(), Toast.LENGTH_LONG).show();
            onCreateE();
        });

        return v;
    }


    @Override
    public void onCreateE() {
        DatabaseHandler db = new DatabaseHandler(this.getContext());
        String EventName = eventName.getText().toString();
        LocalDate EventDate = LocalDate.of(eventDatePicker.getYear(),eventDatePicker.getMonth()+1,eventDatePicker.getDayOfMonth());
        db.addEvent(new Event(EventName,EventDate));
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
