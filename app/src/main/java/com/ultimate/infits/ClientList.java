
package com.ultimate.infits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientList extends Fragment {
    //implements ClientListAdapter.Selecteditem

    RecyclerView clientList;
    RadioButton active,offline;
    //ClientListAdapter cd;
    String client_list_image[]={"@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic",
            "@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic","@drawable/doctor_pic"};
    String client_list_client_name[]={"Ronald richard","Ronald richard","Ronald richard","Ronald richard","Ronald richard",
            "Ronald richard","Ronald richard","Ronald richard","Ronald richard","Ronald richard"};
    String client_list_plan[]={"Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan","Muscle Plan",
            "Muscle Plan","Muscle Plan"};
    String client_list_start_date[]={"10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022",
            "10 Mar 2022","10 Mar 2022","10 Mar 2022","10 Mar 2022"};
    String client_list_end_date[]={"10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023",
            "10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023","10 Mar 2023"};

    List<List_Clients> client_list1=new ArrayList<>();
    List<List_Clients> client_list2=new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientList() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientList.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientList newInstance(String param1, String param2) {
        ClientList fragment = new ClientList();
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
        View view = inflater.inflate(R.layout.fragment_client_list, container, false);
        clientList = view.findViewById(R.id.client_list);
        active = view.findViewById(R.id.active_btn);
        offline = view.findViewById(R.id.pending_btn);
        client_list1.clear();
        for (int i = 0; i < client_list_image.length; i++) {
            List_Clients obj = new List_Clients(client_list_plan[i], client_list_client_name[i], client_list_image[i],
                    client_list_start_date[i], client_list_end_date[i], true);
            client_list1.add(obj);
        }
        ClientListAdapter cd = new ClientListAdapter(getContext(),client_list1);
        clientList.setAdapter(cd);
        clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client_list1.clear();
                for (int i = 0; i < client_list_image.length; i++) {
                    List_Clients obj = new List_Clients(client_list_plan[i], client_list_client_name[i], client_list_image[i],
                            client_list_start_date[i], client_list_end_date[i], true);
                    client_list1.add(obj);
                }
                  ClientListAdapter cd1 = new ClientListAdapter(getContext(),client_list1);
                // cd = new ClientListAdapter(getContext(),true);
                clientList.setAdapter(cd1);
                 clientList.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        offline.setOnClickListener(v -> {
            client_list2.clear();
            for (int i = 0; i < client_list_image.length; i++) {
                List_Clients obj = new List_Clients(client_list_plan[i], client_list_client_name[i], client_list_image[i],
                        client_list_start_date[i], client_list_end_date[i], false);
                client_list2.add(obj);
            }
             ClientListAdapter cd2 = new ClientListAdapter(getContext(),client_list2);
             clientList.setAdapter(cd2);
            clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        });
       // ClientListAdapter cd = new ClientListAdapter(getContext(), client_list1, (ClientListAdapter.Selecteditem) this);
       // clientList.setAdapter(cd);
       // clientList.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }


}