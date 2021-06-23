package com.example.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.events.frags.HomeFragment;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.events.event.Event;
import com.example.events.frags.AddEventFrag;
import com.example.events.frags.ShowListEventFrag;
import com.example.events.helper.DatabaseHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements  HomeFragment.OnNavListener{
    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHandler(this);

        HomeFragment home = HomeFragment.newInstance();
        home.setNavListener(this);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frag_main_content, home);
        transaction.commit();

    }
    public void onSelect(HomeFragment.SelectedView selected) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        Fragment frag;
        switch (selected) {
            case ADDEVENT:
                frag = AddEventFrag.newInstance();
                break;
            case SHOWEVENTS:
                frag = ShowListEventFrag.newInstance(db.getAllEvents());
                break;

            default:
                throw new RuntimeException("Not supported fragment");
        }

        transaction.replace(R.id.frag_main_content, frag);

        transaction.setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.slide_out_right);

        transaction.addToBackStack(null);

        transaction.commit();
    }
    @FunctionalInterface
    public interface OnCreateEvent {
        void onCreateE();
    }

    private MainActivity.OnCreateEvent DatabaseHandler;


}