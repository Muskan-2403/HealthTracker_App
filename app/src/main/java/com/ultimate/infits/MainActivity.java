package com.ultimate.infits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout draw;
    NavigationView navigationView;
    LinearLayout nav_account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        draw = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,draw,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        draw.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, new ClientList()).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, new ClientList()).commit();
            draw.open();
        }
        nav_account= (LinearLayout) findViewById(R.id.nav_account);
        nav_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.closeDrawer(GravityCompat.START);
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new Profile()).commit();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (draw.isDrawerOpen(GravityCompat.START)){
            draw.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.clientList:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new ClientList()).commit();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.dashboard:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new DashboardFragment()).commit();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.message:
                startActivity(new Intent(this,MessageActivity.class));
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                startActivity(new Intent(this,Settings.class));
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.Appointment:
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new Calender()).commit();
                draw.closeDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}