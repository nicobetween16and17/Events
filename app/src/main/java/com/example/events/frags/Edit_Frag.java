package com.example.events.frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.events.R;
import com.example.events.event.Event;
import com.example.events.helper.DatabaseHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Edit_Frag extends Fragment {
    EditText name,desc;
    DatePicker datePicker;
    Button buttonSave;
    DatabaseHandler dbh;
    Event event1;
    public Edit_Frag() {

    }
    public static Edit_Frag newInstance(Event event1) {
        Edit_Frag fragment = new Edit_Frag();
        Bundle args = new Bundle();
        args.putParcelable("event_element", event1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            event1 = getArguments().getParcelable("event_element");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frag_modification_event, container, false);
        name = v.findViewById(R.id.tv_name_modif);
        name.setText(event1.getName());
        datePicker=v.findViewById(R.id.datepicker_modif);
        datePicker.updateDate(event1.getDate().getYear(),event1.getDate().getMonthValue(),event1.getDate().getDayOfMonth());
        buttonSave = v.findViewById(R.id.button_save);
        dbh = new DatabaseHandler(getContext());
        buttonSave.setOnClickListener(view ->{
            event1.setName(name.getText().toString());
            LocalDate newDate = LocalDate.of(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
            event1.setDate(newDate);
            dbh.updateEvent(event1);
            getActivity().getSupportFragmentManager().popBackStack();
        });

        return v;
    }

}