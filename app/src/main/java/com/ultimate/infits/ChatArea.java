package com.ultimate.infits;

import androidx.annotation.Nullable;
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
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ultimate.infits.databinding.ActivityChatAreaBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatArea extends AppCompatActivity {


    public ChatArea(){

    }
    public ChatArea(ActivityChatAreaBinding binding) {
        this.binding = binding;
    }
    private ActivityChatAreaBinding binding;
    public static String chat_area_client_name;
    TextView name, message;
    ImageView profile_pic;
    String url = "http://192.168.134.1/messages.php";
    String url2 = "http://192.168.134.1/messages2.php";
    String url3 = "http://192.168.134.1/messagesSend.php";
    DataFromDatabase dataFromDatabase;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityChatAreaBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_chat_area);
        setContentView(binding.getRoot());


        chat_area_client_name = getIntent().getExtras().getString("client_name");
        name = findViewById(R.id.chat_area_client_name);
        name.setText(chat_area_client_name);
        profile_pic = findViewById(R.id.chat_area_profile_pic);
        message = findViewById(R.id.typed_message);
        Button send = findViewById(R.id.send_message_btn);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        ImageView i12 = findViewById(R.id.attach_file);
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog box
            }
        });

        getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainerMessages, new Messages_Recycler()).commit();

        //chatMessages=new ArrayList<>();


    }
    private void init(){

    }
    //read data from database
    private final void setMessages(){
        queue = Volley.newRequestQueue(getApplicationContext());
        Log.d("ChatArea","before");
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
            if (!response.equals("failure")){
                Log.d("ChatArea","success");
                Log.d("response ChatArea",response);

                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i=0;i<jsonArray.length();i++){
                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                      String message = jsonObject.getString("message");
                      String messageby = jsonObject.getString("messageBy");
                      String time = jsonObject.getString("time");
                      String readUnread = jsonObject.getString("read/unread");
                      if(messageby.equals("client")){
                            //insert message and time to client side
                      }else if(messageby.equals("dietitian")){
                          //insert message and time to dietitian side
                      }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else if (response.equals("failure")){
                Log.d("ChatArea","failure");
                Toast.makeText(getApplicationContext(), "ChatArea failed", Toast.LENGTH_SHORT).show();
            }
        },error -> {
            Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();})
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("duserID", dataFromDatabase.clientuserID);
                data.put("cuserID",chat_area_client_name);

                return data;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
        Log.d("ChatArea","at end");


        queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest2 = new StringRequest(Request.Method.POST,url2, response -> {
            if (response.equals("success")){
                Log.d("ChatArea2","success");
                Log.d("response ChatArea2",response);
            }
            else if (response.equals("failure")){
                Log.d("ChatArea2","failure");
            }
        },error -> {
            Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();})
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("duserID", dataFromDatabase.clientuserID);
                data.put("cuserID",dataFromDatabase.clientuserID);
                return data;
            }
        };
        RequestQueue requestQueue2 = Volley.newRequestQueue(getApplicationContext());
        requestQueue2.add(stringRequest2);
    }
    private final void sendMessage(){
        //insert to db
        message.setText(null);
        String message ="";


        queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest3 = new StringRequest(Request.Method.POST,url3, response -> {
            if (response.equals("success")){
                Log.d("ChatArea3","success");
                Log.d("response ChatArea3",response);
            }
            else if (response.equals("failure")){
                Log.d("ChatArea3","failure");
                Toast.makeText(getApplicationContext(),"unable to send message!! try again",Toast.LENGTH_SHORT).show();
            }
        },error -> {
            Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();})
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();
                data.put("duserID", dataFromDatabase.clientuserID);
                data.put("cuserID",chat_area_client_name);
                data.put("message",message);
                return data;
            }
        };
        RequestQueue requestQueue3 = Volley.newRequestQueue(getApplicationContext());
        requestQueue3.add(stringRequest3);
    }
}