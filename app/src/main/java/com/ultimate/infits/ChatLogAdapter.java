package com.ultimate.infits;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.infits.databinding.ChatareaclientmessageBinding;

public class ChatLogAdapter extends RecyclerView.Adapter<ChatLogAdapter.ChatLogHolder> {

    Context con;
    String[] arrayList;

    ChatLogAdapter(Context con,String[] arrayList){
        this.con = con;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ChatLogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View view = inflater.inflate(R.layout.chat_log_layout,parent,false);
        return new ChatLogHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatLogHolder holder, int position) {
        if (arrayList == AllMessages.unreadChat){
            holder.unread.setVisibility(View.VISIBLE);
        }
        holder.textView.setText(arrayList[position]);
            if (position == 3|| position == 1 && arrayList != AllMessages.unreadChat){
                holder.unread.setVisibility(View.VISIBLE);
            }
            holder.chat_log_view.setOnClickListener(v ->{
                Intent i=new Intent(con.getApplicationContext(), ChatArea.class);
                i.putExtra("client_name","ronald richard");
                con.startActivity(i);
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.length;
    }



    public class ChatLogHolder extends RecyclerView.ViewHolder{
        TextView textView,unread;
        LinearLayout chat_log_view;
        public ChatLogHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.msg);
            unread = itemView.findViewById(R.id.unread);
            chat_log_view = itemView.findViewById(R.id.chat_log_view);
        }
    }
}
