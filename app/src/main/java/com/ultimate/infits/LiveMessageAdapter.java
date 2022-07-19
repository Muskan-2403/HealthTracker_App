package com.ultimate.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LiveMessageAdapter extends RecyclerView.Adapter<LiveMessageAdapter.LiveMessageViewHolder> {

    Context context;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<String> userName = new ArrayList<>();
    LiveMessageAdapter(Context context, ArrayList<String> message , ArrayList<String> userName){
        this.context = context;
        this.message = message;
        this.userName = userName;
    }

    @NonNull
    @Override
    public LiveMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.live_chat,parent,false);
        return new LiveMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiveMessageViewHolder holder, int position) {
            holder.user.setText(userName.get(0));
            holder.message.setText(message.get(position));
    }

    @Override
    public int getItemCount() {
        return message.size();
    }

    public class LiveMessageViewHolder extends RecyclerView.ViewHolder{
        TextView user;
        TextView message;
        public LiveMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.name);
            message = itemView.findViewById(R.id.text);
        }
    }
}