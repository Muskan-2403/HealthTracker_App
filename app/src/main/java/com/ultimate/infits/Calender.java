package com.ultimate.infits;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Calender#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Calender extends Fragment {

    RecyclerView event_list;
    String date_to_display_trackers;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Calender() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Calender.
     */
    // TODO: Rename and change types and number of parameters
    public static Calender newInstance(String param1, String param2) {
        Calender fragment = new Calender();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        event_list = view.findViewById(R.id.event_list);
        EventListAdapter ea = new EventListAdapter(getContext());
        event_list.setAdapter(ea);
        event_list.setLayoutManager(new LinearLayoutManager(getContext()));

        Date dateobj=new Date();
        date_to_display_trackers=new SimpleDateFormat("yyyy-MM-dd").format(dateobj);
        Button search_appointments= view.findViewById(R.id.appointment_done);
        CalendarView calendarView = view.findViewById(R.id.appointment_calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String curDay = String.valueOf(dayOfMonth);
                String curMonth = String.valueOf(month+1);
                String curYear = String.valueOf(year);
                date_to_display_trackers = curYear+"-"+curMonth+"-"+curDay;
                Toast.makeText(getContext(),"Selected date= "+date_to_display_trackers,Toast.LENGTH_SHORT).show();
            }
        });
        search_appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Searching appointments on "+date_to_display_trackers,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}