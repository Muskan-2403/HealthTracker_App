package com.ultimate.infits;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Messages_Recycler#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Messages_Recycler extends Fragment {
    List<ChatMessage> cMessages = new ArrayList<>();
    ChatMessageAdapter chatMessageAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Messages_Recycler() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Messages_Recycler.
     */
    // TODO: Rename and change types and number of parameters
    public static Messages_Recycler newInstance(String param1, String param2) {
        Messages_Recycler fragment = new Messages_Recycler();
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
        View v= inflater.inflate(R.layout.fragment_messages__recycler, container, false);
        RecyclerView r1=v.findViewById(R.id.chat_area_message_recycler);
        ProgressBar p1=v.findViewById(R.id.chat_area_loading_status);
        // r1.setAdapter(chatMessageAdapter);
        //setMessages();
        for(int i=0;i<3;i++) {
            ChatMessage ch = new ChatMessage(ChatArea.chat_area_client_name,DataFromDatabase.dietitianuserID,"hello","14:00","dietitian","U");
           /* ch.senderId = chat_area_client_name;
            ch.receiverId = DataFromDatabase.dietitianuserID;
            ch.message ="hi";
            ch.time="14:00";*/
            cMessages.add(ch);
        }
        for(int i=0;i<3;i++) {
            ChatMessage ch = new ChatMessage(DataFromDatabase.dietitianuserID,ChatArea.chat_area_client_name,"hi","14:00","client","R");
           /* ch.senderId = ;
            ch.receiverId = ;
            ch.message =;
            ch.time=;*/
            cMessages.add(ch);
        }
        // chatMessageAdapter.notifyItemInserted(chatMessages.size());
        chatMessageAdapter= new ChatMessageAdapter(cMessages,ChatArea.chat_area_client_name) ;//add constants.java from video 8
        r1.smoothScrollToPosition(cMessages.size()-1);
        r1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        r1.setAdapter(chatMessageAdapter);
        r1.setVisibility(View.VISIBLE);
        p1.setVisibility(View.GONE);

    return v;
    }
}