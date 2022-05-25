package com.ultimate.infits;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekSetter extends AppCompatActivity implements durationAdapter.Selecteditem{
    TextView sun_date,mon_date,tue_date,wed_date,thur_date,fri_date,sat_date;
    LinearLayout l1,l2,l3,l4,l5,l6,l7;
    String selected_date;
    int specific_d=60;
    RequestQueue queue;
    DataFromDatabase dataFromDatabase;
    String url = "http://192.168.70.1/weeksetter.php";
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_setter);
        setUpUI();
        TextView[] dateArr = {sun_date,mon_date,tue_date,wed_date,thur_date,fri_date,sat_date};
        int[] duration={10,20,30,40,50,60};
        //"70m","80m","90m","100m","110m","120m"};
        //String[] duration1={"1h10m","1h20m","1h30m"};
        final String[] start_time = new String[1];
        final String[] end_time = new String[1];

        TextView display_month,display_time,display_date;
        String display_month1 = null,display_time1,display_date1;
        TimePicker tt;
        ImageView iv1;
        EditText title_aptment,loc_aptment,note_aptment;
        List<durationList> time1=new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd ");
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
            cal.add(Calendar.DAY_OF_YEAR, 0);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
            cal.add(Calendar.DAY_OF_YEAR, -1);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY){
            cal.add(Calendar.DAY_OF_YEAR, -2);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY){
            cal.add(Calendar.DAY_OF_YEAR, -3);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY){
            cal.add(Calendar.DAY_OF_YEAR, -4);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY){
            cal.add(Calendar.DAY_OF_YEAR, -5);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
            cal.add(Calendar.DAY_OF_YEAR, -6);
            days.add(sdf.format(cal.getTime()));
            for(int i = 0; i< 6; i++){
                cal.add(Calendar.DAY_OF_YEAR, 1);
                days.add(sdf.format(cal.getTime()));
            }
        }

        for (int a = 0 ; a < dateArr.length;a++) {
            dateArr[a].setText(days.get(a));
        }

        iv1= findViewById(R.id.calendar_back);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekSetter.super.onBackPressed();
            }
        });
        l1=findViewById(R.id.sunday);
        l2=findViewById(R.id.monday);
        l3=findViewById(R.id.tuesday);
        l4=findViewById(R.id.wednesday);
        l5=findViewById(R.id.thursday);
        l6=findViewById(R.id.friday);
        l7=findViewById(R.id.saturday);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+sun_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=sun_date.getText().toString();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+mon_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=mon_date.getText().toString();
            }
        });
        l3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l3.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+tue_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=tue_date.getText().toString();
            }
        });
        l4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l4.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+wed_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=wed_date.getText().toString();
            }
        });
        l5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l5.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+thur_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=thur_date.getText().toString();
            }
        });
        l6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l6.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+fri_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=fri_date.getText().toString();
            }
        });
        l7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l7.setBackgroundColor(Color.parseColor("#C6E0FF"));
                l1.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                l6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                Toast.makeText(getApplicationContext(),"Selected date= "+sat_date.getText(),Toast.LENGTH_SHORT).show();
                selected_date=sat_date.getText().toString();
            }
        });

        RecyclerView d=findViewById(R.id.duration_appointment_recycler);
        for(int k=0;k<duration.length;k++)
       {
            durationList obj=new durationList(duration[k]);
            time1.add(obj);
        }
        /*for(int k=0;k<duration1.length;k++)
        {
            durationList obj=new durationList(duration[k]);
            time1.add(obj);
        }*/
        durationAdapter adap1= new durationAdapter(getApplicationContext(),time1, (durationAdapter.Selecteditem) this);
        d.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        d.setAdapter(adap1);

        display_date=findViewById(R.id.new_appointment_date);
        display_month=findViewById(R.id.new_appointment_month);
        display_time=findViewById(R.id.new_appointment_time);
        tt=findViewById(R.id.timePicker1);


        tt.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                int h=tt.getHour();
                int m=tt.getMinute();
                String am_pm;
                if(h<12)
                    am_pm="am";
                else {
                    am_pm = "pm";
                    h=h-12;
                }
                start_time[0] =String.valueOf(h)+":"+String.valueOf(m)+" "+am_pm;
               /* DateFormat obj= new SimpleDateFormat("hh:mm");
                Date date = null;
                try {
                    date = obj.parse(start_time[0]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Calendar cal= Calendar.getInstance();
                cal.setTime(date);
                if(specific_d==60)
                    cal.add(Calendar.HOUR,1);
                else
                    cal.add(Calendar.MINUTE,specific_d);
                end_time[0]= String.valueOf(cal.getTime());
                String date12=start_time[0];
                SimpleDateFormat f2 = new SimpleDateFormat("HH:MM a");
                try {
                    Date date1 = f2.parse(end_time[0]);
                    date12 = obj.format(date1);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                display_time.setText(start_time[0]+"-"+date12);*/
            }
        });

        title_aptment=findViewById(R.id.new_appointment_title_edt);
         loc_aptment=findViewById(R.id.new_appointment_location_edt);
         note_aptment=findViewById(R.id.new_appointment_note_edt);
         Button appt_save=(Button) findViewById(R.id.new_appointment_save);
         appt_save.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String apt_title=title_aptment.getEditableText().toString();
                 String apt_location=loc_aptment.getEditableText().toString();
                 String apt_note=note_aptment.getEditableText().toString();
                 if(apt_title.equals("")||(apt_title.equals(" "))||
                         (apt_note.equals(""))|| (apt_note.equals(" "))||
                         (apt_location.equals(""))||(apt_location.equals(" "))){
                     Toast.makeText(getApplicationContext(),"Enter all details",Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     Log.d("weeksetter",selected_date+" "+specific_d+" "+
                             start_time[0]+" "+title_aptment.getText().toString()
                             +" "+loc_aptment.getText().toString()+" "+note_aptment.getText().toString());

                     queue = Volley.newRequestQueue(getApplicationContext());
                     Log.d("weeksetter","before");
                     StringRequest stringRequest = new StringRequest(Request.Method.POST,url, response -> {
                         if (!response.equals("failure")){
                             Log.d("weeksetter","success");
                             Log.d("response weeksetter",response);

                             try {

                             } catch (Exception e) {
                                 e.printStackTrace();
                             }

                         }
                         else if (response.equals("failure")){
                             Log.d("weeksetter","failure");
                             Toast.makeText(getApplicationContext(), "weeksetter failed", Toast.LENGTH_SHORT).show();
                         }
                     },error -> {
                         Toast.makeText(getApplicationContext(),error.toString().trim(),Toast.LENGTH_SHORT).show();})
                     {
                         @Nullable
                         @Override
                         protected Map<String, String> getParams() throws AuthFailureError {
                             Map<String, String> data = new HashMap<>();
                             Calendar c = Calendar.getInstance();
                             int year = c.get(Calendar.YEAR);
                             int month = c.get(Calendar.MONTH);
                             int time = tt.getHour();
                             if (start_time[0].substring(5,7)=="pm"){
                                 time += 12;
                             }
                            String dateandtime = year+"-"+month+"-"+selected_date+" "+time+":"+tt.getMinute()+":00";
                             Log.d("weeksetter dateandtime",dateandtime);
                            String status = "pending";
                            String duration = String.valueOf(specific_d);
                            String loc = loc_aptment.getText().toString();
                            String title =  title_aptment.getText().toString();
                            String note = note_aptment.getText().toString();
                            String notifyMe = "Y";
                             data.put("dietitianuserID", dataFromDatabase.dietitianuserID);
                             data.put("clientuserID", "Eden");
                             data.put("dateandtime",dateandtime);
                             data.put("status",status);
                             data.put("duration",duration+":00");
                             data.put("location",loc);
                             data.put("title",title);
                             data.put("note",note);
                             data.put("notifyme",notifyMe);
                             return data;
                         }
                     };
                     RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                     requestQueue.add(stringRequest);
                     Log.d("weeksetter","at end");
                 }
             }
         });

    }
    private void setUpUI(){
        sun_date = findViewById(R.id.sun_date);
        mon_date = findViewById(R.id.mon_date);
        tue_date = findViewById(R.id.tue_date);
        wed_date = findViewById(R.id.wed_date);
        thur_date = findViewById(R.id.thur_date);
        fri_date = findViewById(R.id.fri_date);
        sat_date = findViewById(R.id.sat_date);
    }
    @Override
    public void selecteditem(int n) {
        Toast.makeText(getApplicationContext(), String.valueOf(n)+" Minutes", Toast.LENGTH_SHORT).show();
        specific_d=n;
    }
}