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

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class AcceptRejectListAdapter extends RecyclerView.Adapter<AcceptRejectListAdapter.AcceptRejectListViewHolder> {
    Context ct;
    List<AcceptRejectList> list2;
    AcceptRejectListAdapter(Context ct, List<AcceptRejectList> lst){
        this.ct = ct;
        this.list2=lst;
    }

    @NonNull
    @Override
    public AcceptRejectListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.accept_reject_recyclerview,parent,false);
        return new AcceptRejectListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcceptRejectListViewHolder holder, int position) {
        AcceptRejectList pos=list2.get(position);

        String img=pos.getClient_image();
        String n=pos.getClient_name();
        String type=pos.getPlan_type();

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

    class AcceptRejectListViewHolder extends RecyclerView.ViewHolder{
        ImageView pimg;
        TextView pname,ptype;
        public AcceptRejectListViewHolder(@NonNull View itemView) {
            super(itemView);
            ptype= itemView.findViewById(R.id.accept_reject_plan_type);
            pimg=itemView.findViewById(R.id.accept_reject_profile_photo);
            pname=itemView.findViewById(R.id.accept_reject_profile_name);

        }
    }

}
