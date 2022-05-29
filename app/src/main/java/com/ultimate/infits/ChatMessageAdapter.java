package com.ultimate.infits;

import android.content.Context;
import android.graphics.Bitmap;
import android.preference.Preference;
import android.view.View;
import android.view.ViewGroup;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ultimate.infits.databinding.ChatareaclientmessageBinding;
import com.ultimate.infits.databinding.ChatareadietitianmessageBinding;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        //extends RecyclerView.Adapter{

    private final List<ChatMessage> chatMessages;
    private final String senderID;
   // private final Bitmap receiverProfileImage;

    public static final int VIEW_TYPE_SENT=1;
    public static final int VIEW_TYPE_RECEIVE=2;
    public ChatMessageAdapter(List<ChatMessage> chatMessages, String senderID){// Bitmap receiverProfileImage) {
        this.chatMessages = chatMessages;
        this.senderID = senderID;
      //  this.receiverProfileImage = receiverProfileImage;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if(viewType==VIEW_TYPE_SENT){
           return new SentMessageViewHolder(
                   ChatareadietitianmessageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
           );
       }
       else{
           return new ReceivedMessageViewHolder(
                   ChatareaclientmessageBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false)
           );
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if(getItemViewType(position)== VIEW_TYPE_SENT) {
                ((SentMessageViewHolder) holder).setData(chatMessages.get(position));
            }
            else{
                ((ReceivedMessageViewHolder) holder).setData(chatMessages.get(position));
            }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatMessages.get(position).senderId.equals(senderID))
            return VIEW_TYPE_SENT;
        else
            return VIEW_TYPE_RECEIVE;
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder{
        private final ChatareadietitianmessageBinding binding1;
        SentMessageViewHolder(ChatareadietitianmessageBinding chatAreaDietitianMessageBinding){
            super(chatAreaDietitianMessageBinding.getRoot());
            binding1=chatAreaDietitianMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding1.dietitianMsg.setText(chatMessage.message);
            binding1.dietitianMsgTime.setText(chatMessage.time);
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder{
        private final ChatareaclientmessageBinding binding2;
        ReceivedMessageViewHolder(ChatareaclientmessageBinding chatAreaClientMessageBinding){
            super(chatAreaClientMessageBinding.getRoot());
            binding2=chatAreaClientMessageBinding;
        }

        void setData(ChatMessage chatMessage){
            binding2.clientMsg.setText(chatMessage.message);
            binding2.clientMsgTime.setText(chatMessage.time);
        }
    }


    /*Context ct;
    private AcceptRejectListAdapter.Selecteditem selectedItem;
    ImageView list_accept;
    DietitianChatMessageAdapter(Context ct) {
        this.ct = ct;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ct);
        View view = inflater.inflate(R.layout.accept_reject_recyclerview,parent,false);
        return new DietitianChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class DietitianChatMessageViewHolder extends RecyclerView.ViewHolder {
        TextView pname, ptype;

        public DietitianChatMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            ptype = itemView.findViewById(R.id.accept_reject_plan_type);
            pname = itemView.findViewById(R.id.accept_reject_profile_name);

        }
    }*/
}
