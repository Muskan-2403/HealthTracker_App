package com.ultimate.infits;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Notification extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        RecyclerView recyclerView = findViewById(R.id.notification_list);
        NotificationListAdapter nd = new NotificationListAdapter(this);
        recyclerView.setAdapter(nd);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}