package com.ultimate.infits;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView name, message;
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
        message=findViewById(R.id.typed_message);
        Button send=findViewById(R.id.send_message_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        ImageView i12=findViewById(R.id.attach_file);
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog box
            }});
        // Preference preferenceManager = new Preference(getApplicationContext());
        //chatMessages=new ArrayList<>();


        RecyclerView r1=findViewById(R.id.chat_area_message_recycler);
       // r1.setAdapter(chatMessageAdapter);
        //setMessages();
        for(int i=0;i<3;i++) {
            ChatMessage ch = new ChatMessage(chat_area_client_name,DataFromDatabase.dietitianuserID,"hello","14:00","dietitian","U");
           /* ch.senderId = chat_area_client_name;
            ch.receiverId = DataFromDatabase.dietitianuserID;
            ch.message ="hi";
            ch.time="14:00";*/
            cMessages.add(ch);
        }
        for(int i=0;i<3;i++) {
            ChatMessage ch = new ChatMessage(DataFromDatabase.dietitianuserID,chat_area_client_name,"hi","14:00","client","R");
           /* ch.senderId = ;
            ch.receiverId = ;
            ch.message =;
            ch.time=;*/
            cMessages.add(ch);
        }
       // chatMessageAdapter.notifyItemInserted(chatMessages.size());
        chatMessageAdapter= new ChatMessageAdapter(cMessages,chat_area_client_name) ;//add constants.java from video 8
        r1.smoothScrollToPosition(cMessages.size()-1);
        r1.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        r1.setAdapter(chatMessageAdapter);
        r1.setVisibility(View.VISIBLE);
        binding.chatAreaLoadingStatus.setVisibility(View.GONE);

    }
   // private void init(){ }
    //read data from database
    private final void setMessages(){
           }
           private final void sendMessage(){
        //insert to db
               message.setText(null);
           }
}