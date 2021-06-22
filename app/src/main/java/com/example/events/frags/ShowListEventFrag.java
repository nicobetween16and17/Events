package com.example.events.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.events.R;

public class ShowListEventFrag extends Fragment {
    public static AddEventFrag newInstance(){
        AddEventFrag frag = new AddEventFrag();
        return frag;
    }
    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance){
        View v = inflater.inflate(R.layout.frag_add_btn_event,container,false);


        return v;
    }
}
