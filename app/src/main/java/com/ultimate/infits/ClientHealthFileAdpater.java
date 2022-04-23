package com.ultimate.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class ClientHealthFileAdpater extends RecyclerView.Adapter<ClientHealthFileAdpater.ClientHealthFileViewHolder> {

    Context ct;

    ClientHealthFileAdpater(Context ct){
        this.ct = ct;
    }

    @NonNull
    @Override
    public ClientHealthFileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.health_report_files_layout,parent,false);
        return new ClientHealthFileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientHealthFileViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class ClientHealthFileViewHolder extends RecyclerView.ViewHolder{

        public ClientHealthFileViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
