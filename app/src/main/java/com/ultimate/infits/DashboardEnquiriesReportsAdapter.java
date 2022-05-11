package com.ultimate.infits;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class DashboardEnquiriesReportsAdapter extends RecyclerView.Adapter<DashboardEnquiriesReportsAdapter.DashboardEnquiriesReportsViewHolder> {
    Context ct;
    List<Enquiries> list2;
    DashboardEnquiriesReportsAdapter(Context ct, List<Enquiries> lst){
        this.ct = ct;
        this.list2=lst;
    }

    @NonNull
    @Override
    public DashboardEnquiriesReportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.dashboard_enquiries_reports,parent,false);
        Button enq_message= view.findViewById(R.id.enquiry_message);
        enq_message.setOnClickListener(this::return_message_Area);
        return new DashboardEnquiriesReportsViewHolder(view);
    }

        public void return_message_Area(View v) {
            Navigation.findNavController(v).navigate(R.id.action_dashboardFragment2_to_chattingArea3);
        }
    @Override
    public void onBindViewHolder(@NonNull DashboardEnquiriesReportsViewHolder holder, int position) {
        Enquiries pos=list2.get(position);

        String img=pos.getConsultation_patient_image();
        String n=pos.getConsultation_patient_name();
        String type=pos.getConsultation_type();

        File imgFile = new File(img);

        if(imgFile.exists()){

            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            holder.pimg.setImageBitmap(myBitmap);

        }
        holder.ptype.setText(type);
        // holder.pimg.setImageDrawable(img);
        holder.pname.setText(n);

    }

    @Override
    public int getItemCount() {
        return list2.size();
    }

    class DashboardEnquiriesReportsViewHolder extends RecyclerView.ViewHolder{
        ImageView pimg;
        TextView pname,ptype;
        public DashboardEnquiriesReportsViewHolder(@NonNull View itemView) {
            super(itemView);
            ptype= itemView.findViewById(R.id.enquiry_consultation_type);
            pimg=itemView.findViewById(R.id.enquiry_profile_photo);
            pname=itemView.findViewById(R.id.enquiry_profile_name);

        }
    }

}
