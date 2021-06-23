package com.example.events.frags;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.events.R;
import com.example.events.event.Event;

import java.util.ArrayList;

public class ShowListEventFrag extends Fragment {
    private ListView LVevent;
    private ArrayList<Event> EventsList;
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
        EventsList = getArguments().getParcelableArrayList("eventsList");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View v = inflater.inflate(R.layout.fragment_list_event,container,false);
        LVevent = v.findViewById(R.id.event_list);

        return v;
    }
}
