package com.example.events.frags;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.events.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    Button btnAddEvent, btnShowList ;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btnAddEvent = v.findViewById(R.id.add_event);
        btnShowList = v.findViewById(R.id.event_list);


        btnAddEvent.setOnClickListener(this);
        btnShowList.setOnClickListener(this);


        return v;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add_event) {
            sendNavEvent(SelectedView.ADDEVENT);
        }
        else if (v.getId() == R.id.event_list) {
            sendNavEvent(SelectedView.SHOWEVENTS);
        }
        else {
            throw  new RuntimeException("Event clic not supported");
        }
    }

    private void sendNavEvent(SelectedView selected) {
        if(navListener != null) {
            navListener.onSelect(selected);
        }
    }

    //region Event
    public enum SelectedView {
        ADDEVENT,
        SHOWEVENTS

    }

    @FunctionalInterface
    public interface OnNavListener {
        void onSelect(SelectedView selected);
    }

    private OnNavListener navListener;

    public void setNavListener(OnNavListener navListener) {
        this.navListener = navListener;
    }
    //endregion
}