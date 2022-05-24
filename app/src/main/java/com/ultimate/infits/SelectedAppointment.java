package com.ultimate.infits;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class SelectedAppointment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_appointment);
        ImageView calback= findViewById(R.id.calendar_back);
        calback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedAppointment.super.onBackPressed();
            }
        });
        getSupportFragmentManager().beginTransaction().replace(R.id.selected_client_appt,new selectedAppointment_details()).commit();
        TextView sel_client_appt_month=findViewById(R.id.selected_client_appt_month);
        TextView sel_client_appt_date=findViewById(R.id.selected_client_appt_date);
        TextView sel_client_appt_type=findViewById(R.id.selected_client_appt_type);
        Button d=(Button) findViewById(R.id.active_btn);
        Button n=(Button) findViewById(R.id.pending_btn);
        d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.selected_client_appt,new selectedAppointment_details()).commit();
            }
        });
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.selected_client_appt,new selectedAppointment_notes()).commit();
            }
        });
        ToggleButton btn=findViewById(R.id.toggleButtonNotify);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn.isChecked())
                Toast.makeText(getApplicationContext(),"Notification alert set",Toast.LENGTH_SHORT).show();
                //code to push notification
            }
        });
    }
}