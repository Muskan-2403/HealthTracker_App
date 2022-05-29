package com.ultimate.infits;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ChatLogAdapter extends RecyclerView.Adapter<ChatLogAdapter.ChatLogHolder> {

    Context con;
    List<ChatLogList> l;

    ChatLogAdapter(Context con,List<ChatLogList> l){
        this.con = con;
        this.l=l;
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
        if (l.get(position).getRead() == "u"){
            holder.unread.setVisibility(View.VISIBLE);
        }
        holder.msg.setText(l.get(position).getClient_msg());
            holder.msg_time.setText(l.get(position).getClient_time());
           // holder.profile_pic.setImageBitmap(l.get(position).getProfile_pic());
        holder.name.setText(l.get(position).getClient_name());
            holder.chat_log_view.setOnClickListener(v ->{
                //save to database the message is read
                holder.unread.setVisibility(View.GONE);
                Intent i=new Intent(con.getApplicationContext(), ChatArea.class);
                i.putExtra("client_name",l.get(position).getClient_name());
                con.startActivity(i);
            });
    }

    @Override
    public int getItemCount() {
        return l.size();
    }



    public class ChatLogHolder extends RecyclerView.ViewHolder{
        TextView msg,unread,msg_time,name;
        LinearLayout chat_log_view;
        ImageView profile_pic;
        public ChatLogHolder(@NonNull View itemView) {
            super(itemView);
            msg = itemView.findViewById(R.id.chat_log_msg);
            unread = itemView.findViewById(R.id.unread);
            msg_time=itemView.findViewById(R.id.chat_log_time);
            name=itemView.findViewById(R.id.chat_log_client_name);
            profile_pic=itemView.findViewById(R.id.chat_log_profile_pic);
            chat_log_view = itemView.findViewById(R.id.chat_log_view);
        }
    }
}
