package com.ultimate.infits;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class durationAdapter extends RecyclerView.Adapter<durationAdapter.durationViewHolder> {

    Context ct;
    List<durationList> list3;
    private Selecteditem selectedItem;
    TextView list_duration;

    durationAdapter(Context ct, List<durationList> lst,Selecteditem selecteditem){
        this.ct = ct;
        this.list3=lst;
        this.selectedItem=selecteditem;
    }

    @NonNull
    @Override
    public durationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.duration_appointment_recyclerview,parent,false);
        return new durationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull durationViewHolder holder, int position) {
        durationList pos=list3.get(position);

        int tm1=pos.getTime();
        list_duration.setText(tm1+"m");

        list_duration.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(ct.getApplicationContext(), "Selected duration: "+tm1+" minutes",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list3.size();
    }

    public interface Selecteditem{
        void selecteditem(int n);
    }

    class durationViewHolder extends RecyclerView.ViewHolder{
        LinearLayout t;

        public durationViewHolder(@NonNull View itemView) {
            super(itemView);
            list_duration = itemView.findViewById(R.id.appt_time);
            list_duration.setBackgroundColor(Color.parseColor("#FFFFFF"));
            t=itemView.findViewById(R.id.duration_layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem.selecteditem(list3.get(getAdapterPosition()).getTime());
                }
            });

        }
    }}
