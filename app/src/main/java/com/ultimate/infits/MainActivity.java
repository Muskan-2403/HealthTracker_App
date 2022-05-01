package com.ultimate.infits;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;

import android.widget.RelativeLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout draw;
    NavigationView navigationView;
    RelativeLayout nav_account;
    public static List<String> stack_fragment= new ArrayList<>();
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
            stack_fragment.add("client_list");
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer, new ClientList()).commit();
            draw.open();
        }
        nav_account= (RelativeLayout) findViewById(R.id.nav_account);
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
            if(getSupportFragmentManager().getBackStackEntryCount()>0)
            {
                getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new BlankFragment()).commit();
                //if(stack_fragment.size()>0) {
                //    stack_fragment.remove(stack_fragment.get(stack_fragment.size() - 1));
                //    System.out.println(stack_fragment);
                //}
                getSupportFragmentManager().popBackStackImmediate();
            }
           else {
                AlertDialog.Builder ad = new AlertDialog.Builder(MainActivity.this);
                ad.setTitle("Warning!");
                ad.setMessage("Do you want to exit?");
                ad.setPositiveButton("Yes",  new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        MainActivity.super.onBackPressed();
                    }

                });
                ad.setNegativeButton("No",  new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1)
                    {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = ad.create();
                dialog.show();
            }
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.clientList:
                FragmentTransaction ft1= getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id.FrameContainer,new BlankFragment());
                ft1.add(R.id.FrameContainer,new ClientList());
                if( (stack_fragment.size()>0) && (stack_fragment.get(stack_fragment.size()-1)!="client_list"))
                {
                    ft1.addToBackStack("client_list");
                    stack_fragment.add("client_list");
                }
                ft1.commit();
               // getSupportFragmentManager().beginTransaction().replace(R.id.FrameContainer,new ClientList()).commit();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.dashboard:
                FragmentTransaction ft2= getSupportFragmentManager().beginTransaction();
                ft2.replace(R.id.FrameContainer,new BlankFragment());
                ft2.add(R.id.FrameContainer,new DashboardFragment());
                if( (stack_fragment.size()>0) && (stack_fragment.get(stack_fragment.size()-1)!="dashboard"))
                {
                    ft2.addToBackStack("dashboard");
                    stack_fragment.add("dashboard");
                }
                ft2.commit();
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.message:
                FragmentTransaction ft3= getSupportFragmentManager().beginTransaction();
                ft3.replace(R.id.FrameContainer,new BlankFragment());
                ft3.add(R.id.FrameContainer,new Messages());
                if( (stack_fragment.size()>0) && (stack_fragment.get(stack_fragment.size()-1)!="messages"))
                {
                    ft3.addToBackStack("messages");
                    stack_fragment.add("messages");
                }
                ft3.commit();
                //startActivity(new Intent(this,MessageActivity.class));
                draw.closeDrawer(GravityCompat.START);
                break;
            case R.id.settings:
                FragmentTransaction ft4= getSupportFragmentManager().beginTransaction();
                ft4.replace(R.id.FrameContainer,new BlankFragment());
                ft4.add(R.id.FrameContainer,new SettingMain());
                if( (stack_fragment.size()>0) && (stack_fragment.get(stack_fragment.size()-1)!="setting"))
                {
                    ft4.addToBackStack("setting");
                    stack_fragment.add("setting");
                }
                ft4.commit();
                //startActivity(new Intent(this,Settings.class));
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