package com.example.events.frags;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.events.R;
import com.example.events.event.Event;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;

public class EventDetailFragment extends Fragment {
    private TextView tvName, tvDate;
    private LinearLayout layoutDesc;

    private Event event1;

    public EventDetailFragment() {
        // Required empty public constructor
    }

    public static EventDetailFragment newInstance(Event event1) {
        EventDetailFragment fragment = new EventDetailFragment();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, container, false);

        // FindViewById
        tvName = v.findViewById(R.id.tv_name);
        layoutDesc = v.findViewById(R.id.linear_layout_event);
        tvDate = v.findViewById(R.id.tv_date);

        // Méthode d'initilisation
        initializeViewData();

        return v;
    }

    private void initializeViewData() {
        tvName.setText(event1.getName());
        layoutDesc.setVisibility(View.VISIBLE);
        tvDate.setText(event1.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

    }

}
