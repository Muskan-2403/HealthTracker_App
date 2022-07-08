package com.ultimate.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link breakfast#newInstance} factory method to
 * create an instance of this fragment.
 */
public class breakfast extends Fragment {

    RecyclerView re;
    List<List_Food> food_list =new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public breakfast() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment breakfast.
     */
    // TODO: Rename and change types and number of parameters
    public static breakfast newInstance(String param1, String param2) {
        breakfast fragment = new breakfast();
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
        View view= inflater.inflate(R.layout.fragment_breakfast, container, false);
        re = view.findViewById(R.id.breakfast_list);
        List_Food obj = new List_Food("BreadTruffle","20 min",DataFromDatabase.profile,"1 serving");
        List_Food obj2 = new List_Food("BreadTruffle","20 min",DataFromDatabase.profile,"1 serving");
        food_list.add(obj);
        food_list.add(obj2);
        BreakfastAdapter da = new BreakfastAdapter(food_list,getContext());
        re.setAdapter(da);
        re.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}