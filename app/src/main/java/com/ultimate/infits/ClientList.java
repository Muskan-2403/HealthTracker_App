
package com.ultimate.infits;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientList#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientList extends Fragment {

    RecyclerView clientList;
    RadioButton active,offline;
    ClientListAdapter cd;
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
        View view = inflater.inflate(R.layout.fragment_client_list,container,false);
        clientList = view.findViewById(R.id.client_list);
        active = view.findViewById(R.id.active_btn);
        offline = view.findViewById(R.id.pending_btn);
        cd = new ClientListAdapter(getContext(),true);
        clientList.setAdapter(cd);
        clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        active.setOnClickListener(v->{
            cd = new ClientListAdapter(getContext(),true);
            clientList.setAdapter(cd);
            clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        offline.setOnClickListener(v->{
            cd = new ClientListAdapter(getContext(),false);
            clientList.setAdapter(cd);
            clientList.setLayoutManager(new LinearLayoutManager(getContext()));
        });
        return view;
    }
}