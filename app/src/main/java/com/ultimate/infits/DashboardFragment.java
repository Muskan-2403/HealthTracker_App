package com.ultimate.infits;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment implements UpcomingConsultationAdapter.Selecteditem {

    TextView name;
    DataFromDatabase dataFromDatabase;
    String url = "http://192.168.158.1/upcomingConsultations.php";
    RequestQueue queue;
    RecyclerView recyclerView1, recyclerView2, recyclerview3;
    String consultation_date[]={"Dec 07", "Dec 07","Dec 07","Dec 07"};
    String consultation_time[]={"05:00pm","05:00pm","05:00pm","05:00pm"};
    String consultation_patient[]={"Michael Simpson","Michael Simpson","Michael Simpson","Michael Simpson"};
    String consultation_patient_image[]={"app/src/main/res/drawable/doctor_blue_border.png"
    ,"app/src/main/res/drawable/doctor_blue_border.png", "app/src/main/res/drawable/doctor_blue_border.png",
    "app/src/main/res/drawable/doctor_blue_border.png","app/src/main/res/drawable/doctor_blue_border.png"
            ,"app/src/main/res/drawable/doctor_blue_border.png", "app/src/main/res/drawable/doctor_blue_border.png",
            "app/src/main/res/drawable/doctor_blue_border.png"};
    String consultation_type[]={"Video consultation","Video consultation","Audio consultation","Video consultation"};

    List<UpcomingConsultations> obj3= new ArrayList<>();
    List<DashboardMessages> obj1=new ArrayList<>();
    List<Dashboard_profile_pics> obj2=new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DashboardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashboardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DashboardFragment newInstance(String param1, String param2) {
        DashboardFragment fragment = new DashboardFragment();
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
        View v= inflater.inflate(R.layout.fragment_dashboard,container,false);
        recyclerView1= v.findViewById(R.id.upcoming_consultation_recycler);
        name = v.findViewById(R.id.name);
        ImageView consultaton_next=v.findViewById(R.id.upcoming_consultation_next);
        ImageView patients_next= v.findViewById(R.id.patients_profile_next);
        ImageView messages_next= v.findViewById(R.id.messages_next);

        name.setText(dataFromDatabase.name);
        consultaton_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.calender2);
            }
        });
        patients_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.clientList4);
            }
        });
        messages_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_dashboardFragment2_to_allMessages2);
            }
        });

        queue = Volley.newRequestQueue(getContext());
        Log.d("Dashboard","before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
            if (!response.equals("failure")){
                Log.d("dashboard","success");
                Log.d("response",response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String dateandtime = object.getString("dateandtime");
                        String mobile = object.getString("mobile");
                        String date = dateandtime.substring(0,10);
                        String time = dateandtime.substring(11,16);
                        UpcomingConsultations obj=new UpcomingConsultations(date,time,consultation_patient_image[i],
                                object.getString("clientID"),mobile);
                        Log.d("Dashboard UC",date+" "+time+" "+object.getString("clientID"));
                        obj3.add(obj);

                    }
                    UpcomingConsultationAdapter adap= new UpcomingConsultationAdapter(getContext(),obj3, (UpcomingConsultationAdapter.Selecteditem) this);
                    recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
                    //recyclerView1.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
                    recyclerView1.setAdapter(adap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getContext(), "Dashboard success", Toast.LENGTH_SHORT).show();
            }
            else if (response.equals("failure")){
                Log.d("Dashboard","failure");
                Toast.makeText(getContext(), "Dashboard failed", Toast.LENGTH_SHORT).show();
            }
        },error -> {
            Toast.makeText(getContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();})
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                String userid = dataFromDatabase.dietitianuserID;
                Log.d("dashboard","dietitianid = "+userid);
                data.put("userID", userid);
                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
        Log.d("Dashboard","at end");


        recyclerView2=v.findViewById(R.id.enquiries_reports_recycler);
        for(int i=0;i<consultation_patient.length;i++)
        {
            DashboardMessages object=new DashboardMessages(consultation_patient_image[i],consultation_patient[i],consultation_type[i]);
            obj1.add(object);
        }
        DashboardMessagesAdapter dfadap=new DashboardMessagesAdapter(getContext(),obj1);
        recyclerView2.setAdapter(dfadap);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView2.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));

        recyclerview3= v.findViewById(R.id.add_profile_recycler);
        for(int i=0;i<consultation_patient_image.length;i++)
        {
            Dashboard_profile_pics object= new Dashboard_profile_pics(consultation_patient_image[i]);
            obj2.add(object);
        }
        Dashboard_profile_adapter padap= new Dashboard_profile_adapter(getContext(),obj2);
        recyclerview3.setAdapter(padap);
        recyclerview3.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
       // recyclerview3.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        return v;
    }


    @Override
    public void selecteditem(String client_n, String time_n) {
        AlertDialog.Builder ad = new AlertDialog.Builder(getChildFragmentManager().getPrimaryNavigationFragment().requireContext());
        ad.setTitle("Info!");
        ad.setMessage("Status of the appointment with " + client_n + " at time "+ time_n);
        ad.setPositiveButton("Completed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                //write code to update the appointment status


                Toast.makeText(getContext(), "Appointment status updated", Toast.LENGTH_SHORT).show();
            }

        });
        ad.setNegativeButton("Pending", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int arg1) {
                dialog.cancel();
            }
        });
        AlertDialog dialog = ad.create();
        dialog.show();
    }
}