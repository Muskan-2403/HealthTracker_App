package com.ultimate.infits;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ClientSearchAdapter extends RecyclerView.Adapter<ClientSearchAdapter.ClientSearchHolder> {

    Context con;

    ClientSearchAdapter(Context con){
        this.con = con;

    }

    @NonNull
    @Override
    public ClientSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.client_list_search_layout,parent,false);
        return new ClientSearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientSearchHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ClientSearchHolder extends RecyclerView.ViewHolder{
        TextView textView,unread;
        public ClientSearchHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.msg);
            unread = itemView.findViewById(R.id.unread);
        }
    }
}
