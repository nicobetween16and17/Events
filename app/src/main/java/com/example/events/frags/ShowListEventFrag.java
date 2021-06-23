package com.example.events.frags;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.events.R;
import com.example.events.event.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class ShowListEventFrag extends Fragment {
    private ListView LVevent;
    private ArrayList<Event> EventsList;
    private Spinner spinner;
    private Button button;
    public ShowListEventFrag(){

    }
    public static ShowListEventFrag newInstance(ArrayList<Event> events){
        ShowListEventFrag frag = new ShowListEventFrag();
        Bundle args = new Bundle();
        args.putParcelableArrayList("eventsList", events);
        frag.setArguments(args);
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        if(getArguments()!=null){
            EventsList = getArguments().getParcelableArrayList("eventsList");
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View v = inflater.inflate(R.layout.fragment_list_event,container,false);
        LVevent = v.findViewById(R.id.event_list_view);
        String[]Trichoice=new String[]{"ID","NOM","DATE"};
        spinner = v.findViewById(R.id.spinnerTrie);
        ArrayAdapter<String> adapterTrie = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item,Trichoice);
        spinner.setAdapter(adapterTrie);
        button = v.findViewById(R.id.buttonTrie);
        button.setOnClickListener(view->{
            String selected = spinner.getSelectedItem().toString();
            switch(selected){
                case "ID":sortID();
                break;
                case"NOM":sortName();
                break;
                case"DATE":sortDate();
                break;
                default:sortID();
                break;
            }
            initializeViewDate();
        });
        initializeViewDate();
        return v;
    }
    private void sortID(){
        Collections.sort(EventsList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getID()-(o2.getID());
            }
        });
    }
    private void sortName(){
        Collections.sort(EventsList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

    }
    private void sortDate(){
        Collections.sort(EventsList, new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
    }
    private void initializeViewDate(){
        ArrayAdapter<Event> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                EventsList
        );
        LVevent.setAdapter(adapter);
        LVevent.setOnItemClickListener((parent,view,position,id)->{
            Log.d("TEST_LIST", "setOnItemClickListener");
            Event event1 = EventsList.get(position);
            if(event != null) {
                event.onClickItem(event1);
            }
        });
    }
    @FunctionalInterface
    public interface OnClickItemListener {
        void onClickItem(Event event1);
    }

    private OnClickItemListener event;

    public void setOnClickItemListener(OnClickItemListener event) {
        this.event = event;
    }
}
