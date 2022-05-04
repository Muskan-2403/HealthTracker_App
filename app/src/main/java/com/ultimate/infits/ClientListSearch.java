package com.ultimate.infits;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ClientListSearch extends AppCompatActivity {
RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_list_search);
        re = findViewById(R.id.search_list);
        ClientSearchAdapter cs = new ClientSearchAdapter(this);
        re.setAdapter(cs);
        re.setLayoutManager(new LinearLayoutManager(this));
    }
}