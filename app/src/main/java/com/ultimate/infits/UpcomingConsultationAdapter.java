package com.ultimate.infits;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class UpcomingConsultationAdapter extends RecyclerView.Adapter<UpcomingConsultationAdapter.UpcomingConsultationViewHolder> {

    Context ct;
   // private String date,time,image,name;
    private List<UpcomingConsultations> list1;
    UpcomingConsultationAdapter(Context ct, List<UpcomingConsultations> list1){
        this.ct = ct;
        this.list1=list1;
    }

    @NonNull
    @Override
    public UpcomingConsultationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view=inflater.inflate(R.layout.dashboard_upcoming_consultations_layout,parent,false);
         Button call=view.findViewById(R.id.joincall);
         call.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 //call.setBackgroundResource(R.drawable.overlay_corner);
                 // write logic to get phone number of client from the database of client and store it in call string
                 String call_no="900123****";
                 Intent i=new Intent();
                 i.setAction(Intent.ACTION_DIAL);
                 i.setData(Uri.parse("tel:"+call_no));
                 ct.startActivity(i);
             }
         });

        return new UpcomingConsultationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpcomingConsultationViewHolder holder, int position) {
        UpcomingConsultations pos=list1.get(position);

        String d=pos.getConsultation_date();
        String t=pos.getConsultation_time();
        String img=pos.getConsultation_patient_image();
        String n=pos.getConsultation_patient();

        File imgFile = new File(img);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.pimg.setImageBitmap(myBitmap);

        }
        holder.pdate.setText(d);
        holder.ptime.setText(t);
       // holder.pimg.setImageDrawable(img);
        holder.pname.setText(n);
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class UpcomingConsultationViewHolder extends RecyclerView.ViewHolder{

        TextView pdate,pname,ptime;
        ImageView pimg;
        public UpcomingConsultationViewHolder(@NonNull View itemView) {
            super(itemView);
            pdate= itemView.findViewById(R.id.consultation_date);
            ptime=itemView.findViewById(R.id.consultation_time);
            pimg=itemView.findViewById(R.id.consultation_profile_photo);
            pname=itemView.findViewById(R.id.consultation_profile_name);

        }
    }
}
