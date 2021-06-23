package com.example.events.frags;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.events.R;
import com.example.events.event.Event;
import com.example.events.helper.DatabaseHandler;

import java.io.InputStream;
import java.time.format.DateTimeFormatter;

public class EventDetailFragment extends Fragment {
    private TextView tvName, tvDate;
    private LinearLayout layoutDesc;
    private Button buttonEdit,buttonDelete;
    private Event event1;
    private DatabaseHandler dbh;

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
        dbh = new DatabaseHandler(getContext());
        // FindViewById
        tvName = v.findViewById(R.id.tv_name);
        layoutDesc = v.findViewById(R.id.linear_layout_event);
        tvDate = v.findViewById(R.id.tv_date);
        buttonEdit=v.findViewById(R.id.button_edit);
        buttonDelete=v.findViewById(R.id.button_delete);
        buttonEdit.setOnClickListener(view->{
            //TODO Resolve crash on edit
            openFragEdit(event1);
        });
        buttonDelete.setOnClickListener(view->{
            dbh.deleteEvent(event1);

            getActivity().getSupportFragmentManager().popBackStack();
            initializeViewData();
        });

        // Méthode d'initilisation
        initializeViewData();

        return v;
    }

    private void initializeViewData() {
        tvName.setText(event1.getName());
        layoutDesc.setVisibility(View.VISIBLE);
        tvDate.setText(event1.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE));

    }
    private void openFragEdit(Event event) {
        Edit_Frag eventdetailed= Edit_Frag.newInstance(event);

        FragmentManager fm = getActivity().getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();

        // Ajout d'une animation
        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right
        );
        // Plus d'info : https://developer.android.com/guide/fragments/animate

        transaction.replace(R.id.frag_main_content, eventdetailed);
        transaction.addToBackStack(null);

        transaction.commit();
    }

}
