package com.ultimate.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.DashBoardViewHolder> {

    Context con;


    DashBoardAdapter(Context con){
        this.con = con;
   }
    @NonNull
    @Override
    public DashBoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(con);
        View view = layoutInflater.inflate(R.layout.consultation_layout,parent,false);
        return new DashBoardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashBoardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class DashBoardViewHolder extends RecyclerView.ViewHolder{
        public DashBoardViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
