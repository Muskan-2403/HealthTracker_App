package com.ultimate.infits;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class ClientListAdapter extends RecyclerView.Adapter<ClientListAdapter.ClientListHolder>{
    Context ct;
    boolean status;
    ClientListAdapter(Context ct,boolean status){
        this.ct = ct;
        this.status = status;
    }

    @NonNull
    @Override
    public ClientListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(ct);
        View view = layoutInflater.inflate(R.layout.client_list_layout,parent,false);
        return new ClientListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientListHolder holder, int position) {
        if (status){
            holder.status_btn.setText("Online");
        }
        if (position == 2 || position == 7){
            Drawable buttonDrawable = holder.status_btn.getBackground();
            buttonDrawable = DrawableCompat.wrap(buttonDrawable);
            DrawableCompat.setTint(buttonDrawable, Color.RED);
            holder.status_btn.setText("Offline");
            holder.status_btn.setBackground(buttonDrawable);
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ClientListHolder extends RecyclerView.ViewHolder{

        Button status_btn;

        public ClientListHolder(@NonNull View itemView) {
            super(itemView);
            status_btn = itemView.findViewById(R.id.status);
        }
    }
}
