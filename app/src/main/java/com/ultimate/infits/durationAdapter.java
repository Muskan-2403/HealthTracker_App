package com.ultimate.infits;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
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

        int t=pos.getTime();
        list_duration.setText(t+"m");

    }

    @Override
    public int getItemCount() {
        return list3.size();
    }

    public interface Selecteditem{
        void selecteditem(int n);
    }

    class durationViewHolder extends RecyclerView.ViewHolder{

        public durationViewHolder(@NonNull View itemView) {
            super(itemView);
            list_duration = itemView.findViewById(R.id.appt_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItem.selecteditem(list3.get(getAdapterPosition()).getTime());

                }
            });

        }
    }}
