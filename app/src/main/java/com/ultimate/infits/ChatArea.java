package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ultimate.infits.databinding.ActivityChatAreaBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatArea extends AppCompatActivity {


    public ChatArea(){

    }
    public ChatArea(ActivityChatAreaBinding binding) {
        this.binding = binding;
    }
    private ActivityChatAreaBinding binding;
    String chat_area_client_name;
    TextView name;
    ImageView profile_pic;
   //private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding= ActivityChatAreaBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_chat_area);
        setContentView(binding.getRoot());
         List<ChatMessage> cMessages = new ArrayList<>();
         ChatMessageAdapter chatMessageAdapter;

        chat_area_client_name=getIntent().getExtras().getString("client_name");
        name=findViewById(R.id.chat_area_client_name);
        name.setText(chat_area_client_name);
        profile_pic=findViewById(R.id.chat_area_profile_pic);

        // Preference preferenceManager = new Preference(getApplicationContext());
        //chatMessages=new ArrayList<>();


        RecyclerView r1=findViewById(R.id.chat_area_message_recycler);
       // r1.setAdapter(chatMessageAdapter);
        //setMessages();
        for(int i=0;i<3;i++) {
            ChatMessage ch = new ChatMessage(chat_area_client_name,DataFromDatabase.dietitianuserID,"hello","14:00");
           /* ch.senderId = chat_area_client_name;
            ch.receiverId = DataFromDatabase.dietitianuserID;
            ch.message ="hi";
            ch.time="14:00";*/
            cMessages.add(ch);
        }
        for(int i=0;i<3;i++) {
            ChatMessage ch = new ChatMessage(DataFromDatabase.dietitianuserID,chat_area_client_name,"hi","14:00");
           /* ch.senderId = ;
            ch.receiverId = ;
            ch.message =;
            ch.time=;*/
            cMessages.add(ch);
        }
       // chatMessageAdapter.notifyItemInserted(chatMessages.size());
        chatMessageAdapter= new ChatMessageAdapter(cMessages,chat_area_client_name) ;//add constants.java from video 8
        r1.smoothScrollToPosition(cMessages.size()-1);
        r1.setAdapter(chatMessageAdapter);
        r1.setVisibility(View.VISIBLE);
        binding.chatAreaLoadingStatus.setVisibility(View.GONE);

    }
   // private void init(){ }
    //read data from database
    private final void setMessages(){
           }
}