package com.example.events.frags;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.events.R;
import com.example.events.event.Event;

import java.util.ArrayList;

public class ShowListEventFrag extends Fragment {
    private ListView LVevent;
    private ArrayList<Event> EventsList;
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
        initializeViewDate();
        return v;
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
