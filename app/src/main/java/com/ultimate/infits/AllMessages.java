package com.ultimate.infits;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AllMessages#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AllMessages extends Fragment {

    RecyclerView recyclerView;
    ChatLogAdapter cd;
    String[] msg = {"Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit"};
    public static String[] unreadChat = {"Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit","Lorem ipsum dolor sit amet, consecteturelit"};
    RadioButton allChats,unRead;
    TextView unread_txt;
    List<ChatLogList> l1=new ArrayList<>();
    List<ChatLogList> l2=new ArrayList<>();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AllMessages() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllMessages.
     */
    // TODO: Rename and change types and number of parameters
    public static AllMessages newInstance(String param1, String param2) {
        AllMessages fragment = new AllMessages();
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
        View view = inflater.inflate(R.layout.fragment_all_messages, container, false);
        recyclerView = view.findViewById(R.id.chat_log);
        allChats = view.findViewById(R.id.all_chat_btn);
        unRead = view.findViewById(R.id.unread_btn);
        unread_txt = view.findViewById(R.id.unread);
        for(int i=0;i<msg.length;i++)
        {
            ChatLogList obj=new ChatLogList("a","ronald","hi","14:00","r");
            l1.add(obj);
        }
        cd = new ChatLogAdapter(view.getContext(),l1);
        recyclerView.setAdapter(cd);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


        allChats.setOnClickListener(v->{
            for(int i=0;i<msg.length;i++)
            {
                ChatLogList obj=new ChatLogList("a","ronald","hi","14:00","r");
                l1.add(obj);
            }
            cd = new ChatLogAdapter(view.getContext(),l1);
            recyclerView.setAdapter(cd);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        });

        unRead.setOnClickListener(v ->{
            for(int i=0;i<msg.length;i++)
            {
                ChatLogList obj=new ChatLogList("a","ronald","hi","14:00","u");
                l2.add(obj);
            }
            cd = new ChatLogAdapter(view.getContext(),l2);
            recyclerView.setAdapter(cd);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        });

        return view;
    }
}