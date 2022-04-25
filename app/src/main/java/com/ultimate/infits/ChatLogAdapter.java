package com.ultimate.infits;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
            holder.itemView.setOnClickListener(v ->{
            MessageActivity messageActivity  = (MessageActivity) con;
            messageActivity.changeFrag();
            });
    }

    @Override
    public int getItemCount() {
        return arrayList.length;
    }



    public class ChatLogHolder extends RecyclerView.ViewHolder{
        TextView textView,unread;
        public ChatLogHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.msg);
            unread = itemView.findViewById(R.id.unread);
        }
    }
}
