package com.ultimate.infits;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class LiveAct extends AppCompatActivity {

    String username = "";

    WebView webView;

    boolean isPeerConnected = true;

    boolean isAudio = true;

    boolean isVideo = true;

    LinearLayout callControlLayout;

    TextView count;

    ImageView toggleAudioBtn,toggleVideoBtn,sendText;
    ImageButton close;

    ArrayList<String> userName = new ArrayList<>();

    EditText chatBox;

    RecyclerView recyclerView;

    Socket sock;
    private String room;
    private boolean user;

    {
        try {
            sock = IO.socket("http://192.168.10.91:8000");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_LONG).show();
                    sock.emit("join-room",room,username);
                }
            });
        }
    };

    ArrayList<String> messagesList = new ArrayList<>();

    private final Emitter.Listener onReceived = args -> runOnUiThread(() -> {
        try{
            JSONObject res = (JSONObject)args[0];
            String sender = res.getString("name");
            String message = res.getString("message");
            userName.add(sender);
            messagesList.add(message);
            recyclerView.setAdapter(new LiveMessageAdapter(getApplicationContext(),messagesList,userName));
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }catch (JSONException jsonException){
            Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);
        recyclerView = findViewById(R.id.live_chat);
        sendText = findViewById(R.id.send_live_text);
        chatBox = findViewById(R.id.live_chat_box);

        close = findViewById(R.id.close);

//        toggleAudioBtn = findViewById(R.id.toggleAudioBtn);

//        toggleVideoBtn = findViewById(R.id.toggleVideoBtn);

        webView = findViewById(R.id.webView);

        count = findViewById(R.id.view_count);

        Intent get = getIntent();

//        username = get.getStringExtra("username");
        username = "Eden";
        room = get.getStringExtra("room");
        user = get.getBooleanExtra("user",false);
        sock.on(Socket.EVENT_CONNECT,onConnect);
        sock.connect();
        sock.emit("new-user",username);
        sock.on("chat-message",onReceived);

        sock.on("view-count", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        count.setText(args[0].toString());
                    }
                });
            }
        });

        sock.on("user-connected",userCon->{
                for (Object o : userCon) {
                    callJavaScriptFunction(String.format("javascript:startCall(\"%s\")", o.toString()));
                    Log.d("Azar", o.toString());
                }
        });

//        toggleAudioBtn.setOnClickListener (v->{
//            isAudio = !isAudio;
//            callJavaScriptFunction(String.format("javascript:toggleAudio(\"%s\")",isAudio));
//            if (isAudio){
////                toggleAudioBtn.setImageResource(R.drawable.ic_baseline_mic_24);
//            }
//            else {
////                toggleAudioBtn.setImageResource(R.drawable.ic_baseline_mic_off_24);
//            }
//        });
//
//        toggleVideoBtn.setOnClickListener(v-> {
//            isVideo = !isVideo;
//            callJavaScriptFunction(String.format("javascript:toggleVideo(\"%s\")",isVideo));
//            if (isVideo){
//                toggleVideoBtn.setImageResource(R.drawable.ic_baseline_videocam_24);
//            }
//            else{
////                toggleVideoBtn.setImageResource(R.drawable.ic_baseline_videocam_off_24);
//            }
//        });
        setUpWebView();
        sendText.setOnClickListener(v->{
            String message = chatBox.getText().toString();
            if (!message.equals("")){
                userName.add(DataFromDatabase.dietitianuserID);
                sock.emit("send-live-chat",room,message,username);
                messagesList.add(message);
                recyclerView.setAdapter(new LiveMessageAdapter(getApplicationContext(),messagesList,userName));
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                chatBox.setText("");
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(LiveAct.this);
//                dialog.requestWindowFeature(Window.);
                dialog.setCancelable(true);
                dialog.setContentView(R.layout.end_live_dialog);
                Button yes = dialog.findViewById(R.id.end_live);
                Button no = dialog.findViewById(R.id.cancel_live);
                dialog.show();
                System.out.println("Hi");
            yes.setOnClickListener(view->{
                String url = "http://192.168.10.91/infits/livesave.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
                    if (response.equals("success")){
                        Toast.makeText(getApplicationContext(), "save success", Toast.LENGTH_SHORT).show();
                    }
                    else if (response.equals("failure")){
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    }
                },error -> {
                    Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();}){
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> data = new HashMap<>();
//                        data.put("userID",dietitian_acc_userID);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
                finish();
            });
            no.setOnClickListener(view->{
                dialog.dismiss();
            });
//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                ViewGroup viewGroup = findViewById(android.R.id.content);
//                View dialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.end_live_dialog, viewGroup, false);
//                builder.setView(dialogView);
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
            }
        });
    }

    void setUpWebView(){
        WebView webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                request.grant(request.getResources());
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webView.addJavascriptInterface(new JavaScriptInterface(this),"Android");
        loadVideoCall();
    }
    private void loadVideoCall() {
        String filePath = "file:///android_asset/call.html";
        webView.loadUrl(filePath);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                initializePeer();
            }
        });
    }
    private void initializePeer() {
        callJavaScriptFunction(String.format("javascript:init('%s')",username));
    }

    private void onCallRequest(String value) {
        if (value == null) {
            return;
        }
        switchToControls();
    }

    private void switchToControls() {
        callControlLayout.setVisibility(View.VISIBLE);
    }

    void callJavaScriptFunction(String functionName){
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.evaluateJavascript(functionName,null);
            }
        });
    }

    public void onPeerConnected() {
        isPeerConnected =true;
//        sock.emit("join-room", room, username);
    }
    private String getUniqueID(){
        return UUID.randomUUID().toString();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onDestroy() {
        webView.loadUrl("about:blank");
        super.onDestroy();
    }
}