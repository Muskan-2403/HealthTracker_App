package com.ultimate.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    Context con;

    EventListAdapter(Context con){
        this.con = con;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(con);
        View view =  layoutInflater.inflate(R.layout.events_in_profile_layout,parent,false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class EventListViewHolder extends RecyclerView.ViewHolder{
        public EventListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
