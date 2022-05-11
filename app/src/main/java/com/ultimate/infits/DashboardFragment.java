package com.ultimate.infits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DashboardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DashboardFragment extends Fragment {

    RecyclerView recyclerView1, recyclerView2, recyclerview3;
    String consultation_date[]={"Dec 07", "Dec 07","Dec 07","Dec 07"};
    String consultation_time[]={"05:00pm","05:00pm","05:00pm","05:00pm"};
    String consultation_patient[]={"Michael Simpson","Michael Simpson","Michael Simpson","Michael Simpson"};
    String consultation_patient_image[]={"app/src/main/res/drawable/doctor_blue_border.png"
    ,"app/src/main/res/drawable/doctor_blue_border.png", "app/src/main/res/drawable/doctor_blue_border.png",
    "app/src/main/res/drawable/doctor_blue_border.png"};
    String consultation_type[]={"Video consultation","Video consultation","Audio consultation","Video consultation"};

    List<UpcomingConsultations> obj= new ArrayList<>();
    List<Enquiries> obj1=new ArrayList<>();
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
        //finding listview
        for (int i=0;i<consultation_patient.length;i++)
        {
            UpcomingConsultations object=new UpcomingConsultations(consultation_date[i],consultation_time[i],consultation_patient_image[i],consultation_patient[i]);
            obj.add(object);
        }
        UpcomingConsultationAdapter adap= new UpcomingConsultationAdapter(getContext(),obj);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        //recyclerView1.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.HORIZONTAL));
        recyclerView1.setAdapter(adap);

        recyclerView2=v.findViewById(R.id.enquiries_reports_recycler);

        for(int i=0;i<consultation_patient.length;i++)
        {
            Enquiries object=new Enquiries(consultation_patient_image[i],consultation_patient[i],consultation_type[i]);
            obj1.add(object);
        }
        DashboardEnquiriesReportsAdapter dfadap=new DashboardEnquiriesReportsAdapter(getContext(),obj1);
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
}